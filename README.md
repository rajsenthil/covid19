# Build
mvn clean package && docker build -t com.home.test/covid19 .

# RUN

docker rm -f covid19 || true && docker run -d -p 8080:8080 -p 4848:4848 --name covid19 com.home.test/covid19 