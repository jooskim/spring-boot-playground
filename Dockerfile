FROM photon:latest

ARG version=0.0.1-SNAPSHOT
ENV filename=demo-$version.jar

LABEL version=${version}

RUN tdnf install -y openjdk8
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY build/libs/demo-*.jar ./${filename}
COPY bootstrap.sh ./
RUN chmod +x ${filename} ./bootstrap.sh

ENV SPRING_PROFILES_ACTIVE=dev
EXPOSE 8080

ENTRYPOINT ["./bootstrap.sh"]
