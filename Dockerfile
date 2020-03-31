FROM glassfish:latest
COPY ./target/covid19.war ${DEPLOYMENT_DIR}
