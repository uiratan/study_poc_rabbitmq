# Usar uma imagem base com Maven e Java 17
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código-fonte
COPY src ./src

# Compilar o projeto
RUN mvn package -DskipTests

# Usar uma imagem base com Java 17 para a execução
FROM openjdk:17-slim

# Copiar o JAR gerado
COPY --from=build /app/target/consumer-0.0.1-SNAPSHOT.jar consumer.jar

# Instalar curl para verificar a fila RabbitMQ
#RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copiar o script de espera da fila
#COPY wait-for-queue.sh /wait-for-queue.sh
#RUN chmod +x /wait-for-queue.sh

# Expor a porta da aplicação
EXPOSE 8080

# Comando para rodar o script primeiro e depois o consumer
#ENTRYPOINT ["/bin/sh", "/wait-for-queue.sh"]

## Expor a porta da aplicação
#EXPOSE 8080
#
## Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "consumer.jar"]