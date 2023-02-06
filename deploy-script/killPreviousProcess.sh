# set properties by PROFILE variable
source ./properties.sh "${1}"

# kill previous process
echo "> Previous port : ${CURRENT_PORT}"

PREVIOUS_PID=$(sudo lsof -ti tcp:"${CURRENT_PORT}")
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