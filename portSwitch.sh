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


# switch NGINX
echo "> Port switch"
IDLE_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/wtf-service-url.inc

echo "> Reload NGINX"
sudo systemctl restart nginx
sleep 10

echo "> nginx switching port success...."
sleep 10