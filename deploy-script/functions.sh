function find_idle_service_name() {
  CUR_PROFILE=$(curl -s "http://${1}:${3}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "green"
  else
    echo "blue"
  fi
}