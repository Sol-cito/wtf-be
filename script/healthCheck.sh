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

IDLE_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)
echo "> idle port : ${IDLE_PORT}"

for retry_count in {1..10}
do
  response=$(curl -s http://"$ADDRESS":"$IDLE_PORT"/health)
  up_count=$(echo "$response" | grep 'Hello WTF!' | wc -l)

  if [ "$up_count" -ge 1 ]
  then
    echo "> Health check success"
    break
  else
    echo "> Health check is not successful"
    echo "> Health check: ${response}"
  fi

  if [ "$retry_count" -eq 10 ]
  then
    echo "> Health check fail. "
    echo "> Exit without switching Nginx Port"
    exit 1
  fi

  echo "> Health check connection fail. Retry..."
  sleep 10
done

echo "> health check success...."
sleep 10