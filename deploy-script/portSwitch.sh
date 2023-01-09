# set properties by PROFILE variable
source ./properties.sh "${1}"

# import functions
source ./functions.sh

# switch NGINX
echo "> Port switch"
IDLE_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/wtf-be-"${PROFILE}"-service-url.inc

echo "> Reload NGINX"
sudo systemctl restart nginx
sleep 10

echo "> nginx switching port success...."
sleep 10