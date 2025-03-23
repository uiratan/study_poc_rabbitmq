#!/bin/sh

QUEUE_NAME="myQueue"
RABBITMQ_HOST="rabbitmq"
USERNAME="guest"
PASSWORD="guest"

echo "Aguardando a criação da fila '$QUEUE_NAME'..."

until curl -u $USERNAME:$PASSWORD -s "http://$RABBITMQ_HOST:15672/api/queues/%2F/$QUEUE_NAME" | grep -q '"name":"'$QUEUE_NAME'"'; do
  echo "Fila ainda não existe. Aguardando..."
  sleep 5
done

echo "Fila '$QUEUE_NAME' detectada! Iniciando consumer..."
exec java -jar consumer.jar
