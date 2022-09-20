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

function find_current_port() {
  CUR_PROFILE=$(curl -s "http://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "${3}"
  else
    echo "${4}"
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

echo "> nohup java -jar -Dspring.profiles.active=${IDLE_PROFILE} ${JAR_NAME} &"

sudo nohup java -jar -Dspring.profiles.active="${IDLE_PROFILE}" "${JAR_NAME}" &

# switch NGINX
echo "> Port switch"
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/wtf-service-url.inc

echo "> Reload NGINX"
sudo systemctl restart nginx

# kill current process
CURRENT_PORT=$(find_current_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)
echo "> current port : ${CURRENT_PORT}"

CURRENT_PID=$(lsof -ti tcp:"${CURRENT_PORT}")
echo "> ${CURRENT_PID}"

if [ -z "${CURRENT_PID}" ]
then
  echo "> there is no currently running app on ${CURRENT_PID}"
else
  echo "> kill -15 ${CURRENT_PID}"
  kill -15 "${CURRENT_PID}"
  sleep 5
fi