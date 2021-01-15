From openjdk:8
copy ./target/saha-0.0.1-SNAPSHOT.jar saha.jar
CMD ["java","-jar","saha.jar"]
export DOCKER_BUILDKIT=0
export COMPOSE_DOCKER_CLI_BUILD=0
