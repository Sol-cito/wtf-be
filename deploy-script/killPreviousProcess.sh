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
fi

echo "> kill previous process success...."
sleep 10