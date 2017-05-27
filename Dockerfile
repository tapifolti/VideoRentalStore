# no glibc, non-server, ubuntu
#FROM isuper/java-oracle
# with glibc, non-server, alpine
#FROM frolvlad/alpine-oraclejdk8
# with glibc, server, alpine
# FROM martinseeler/oracle-server-jre
# FROM fiadliel/java8-jre

FROM martinseeler/oracle-server-jre

COPY target/VideoRentalStore-1.0-SNAPSHOT.jar /usr/src/app/
COPY config.yml /usr/src/app/

EXPOSe 8080 8081

CMD ["java", "-jar", "/usr/src/app/VideoRentalStore-1.0-SNAPSHOT.jar", "server", "/usr/src/app/config.yml"]

# Build
# docker build -t tapifolti/restdeploy .

# Run
# docker run -p 8080:8080 -p 8081:8081 --name restdeploy tapifolti/restdeploy 