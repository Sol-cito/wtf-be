function find_idle_service_name() {
  CUR_PROFILE=$(curl -s "http://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "green"
  else
    echo "blue"
  fi
}

function find_idle_port() {
  CUR_PROFILE=$(curl -s "http://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "${4}"
  else
    echo "${3}"
  fi
}


# Variables
ADDRESS="localhost"

PORT=83 # NGINX local PORT

BLUE_PORT=8095
GREEN_PORT=8096

# check idle profile and port
IDLE_PROFILE=$(find_idle_service_name $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)

IDLE_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)

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
JENKINS_WORKSPACE=/var/lib/jenkins/jobs/wtf-be-dev/workspace
REPOSITORY=/home/sol/project/wtf-be-dev/$IDLE_PROFILE

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

# Deploy
echo "> Deploy new application jar"
JAR_NAME=$(ls -tr "${REPOSITORY}"/*.jar | tail -n 1)

echo "> JAR Name: ${JAR_NAME}"

echo "> add execution permission to ${JAR_NAME}"

sudo chmod +x "${JAR_NAME}"

echo "> execute ${JAR_NAME}"

echo "> Run java jar in background"

sudo nohup java -jar -Dspring.profiles.active="${IDLE_PROFILE}" "${JAR_NAME}" &

# switch NGINX
echo "> Port switch"
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/wtf-service-url.inc

echo "> Reload NGINX"
sudo systemctl restart nginx
sleep 10

# kill previous process
PREVIOUS_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)
echo "> Previous port : ${PREVIOUS_PORT}"

PREVIOUS_PID=$(sudo lsof -ti tcp:"${PREVIOUS_PORT}")
echo "> PREVIOUS_PID : ${PREVIOUS_PID}"

if [ -z "${PREVIOUS_PID}" ]
then
  echo "> there is no currently running app on ${PREVIOUS_PID}"
else
  echo "> kill -15 ${PREVIOUS_PID}"
  sudo kill -9 "${PREVIOUS_PID}"
  sleep 10
fi