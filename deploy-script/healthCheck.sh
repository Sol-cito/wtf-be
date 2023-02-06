# set properties by PROFILE variable
source ./properties.sh "${1}"

# import functions
source ./functions.sh

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