# set properties by PROFILE variable
source ./properties.sh "${1}"

# import functions
source ./functions.sh

# kill previous process
PREVIOUS_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT $HTTP_TYPE)
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