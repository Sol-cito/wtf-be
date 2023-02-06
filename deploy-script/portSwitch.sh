# set properties by PROFILE variable
source ./properties.sh "${1}"

# import functions
source ./functions.sh

# switch NGINX
echo "> Port switch"
IDLE_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT $HTTP_TYPE)

if [ "${IDLE_PORT}" == "nothing" ]; then
    echo "> Port switch fail -> Running port is Nothing"
    exit 1
fi

echo "set \$service_url ${HTTP_TYPE}://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/wtf-be-"${PROFILE}"-service-url.inc

echo "> Reload NGINX"
sudo systemctl restart nginx
sleep 10

echo "> nginx switching port success...."
sleep 10