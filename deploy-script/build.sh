# set properties by PROFILE variable
source ./properties.sh "${1}"

# import functions
source ./functions.sh

# check idle profile and port
IDLE_PROFILE=$(find_idle_service_name "${ADDRESS}" "${PORT}" "${BLUE_PORT}" "${GREEN_PORT}" "${HTTP_TYPE}")

IDLE_PORT=$(find_idle_port "${ADDRESS}" "${PORT}" "${BLUE_PORT}" "${GREEN_PORT}" "${HTTP_TYPE}")

echo "> idle profile : ${IDLE_PROFILE}"
echo "> idle port : ${IDLE_PORT}"

# check pid and kill process
echo "> check running app pid on ${IDLE_PORT}"
IDLE_PID=$(sudo lsof -ti tcp:"${IDLE_PORT}")

echo "> IDLE_PID : ${IDLE_PID}"
if [ -z "${IDLE_PID}" ]
then
  echo "> there is no currently running app on ${IDLE_PID}"
else
  echo "> kill -15 ${IDLE_PID}"
  sudo kill -15 "${IDLE_PID}"
  sleep 10
fi

# create .jar
JENKINS_WORKSPACE=/var/lib/jenkins/jobs/wtf-be-"${PROFILE}"/workspace
REPOSITORY=/home/sol/project/wtf-be-"${PROFILE}"/$IDLE_PROFILE

cd "${JENKINS_WORKSPACE}"
sudo chmod +x gradlew
sudo ./gradlew build

if [ $? -eq 0 ];then
  sudo cp "${JENKINS_WORKSPACE}"/build/libs/*SNAPSHOT.jar "${REPOSITORY}"
  echo "> copy snapshot jar"
else
  echo "> build fail"
  exit 9
fi

# Build
echo "> Build new application jar"
JAR_NAME=$(ls -tr "${REPOSITORY}"/*.jar | tail -n 1)

echo "> JAR Name: ${JAR_NAME}"

echo "> add execution permission to ${JAR_NAME}"

sudo chmod +x "${JAR_NAME}"

echo "> execute ${JAR_NAME}"

echo "> Run java jar in background"

sudo nohup java -jar -Dspring.profiles.active="${IDLE_PROFILE}" "${JAR_NAME}" &

echo "> Build success...."
sleep 10