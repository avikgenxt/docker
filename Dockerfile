From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/apiGateWay-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/apiGateWay.war
EXPOSE 9092
CMD ["catalina.sh","run"]


From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/dbService-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/dbService.war
EXPOSE 8086
CMD ["catalina.sh","run"]


From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/discovery-server-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/discovery-server.war
EXPOSE 8761
CMD ["catalina.sh","run"]


From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/movie-info-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/movie-info-service-0.0.1.war
EXPOSE 8082
CMD ["catalina.sh","run"]


From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/redis-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/redis.war
EXPOSE 8087
CMD ["catalina.sh","run"]


#
# Redis Dockerfile
#
# https://github.com/dockerfile/redis
#

# Pull base image.
FROM dockerfile/ubuntu

# Install Redis.
RUN \
  cd /tmp && \
  wget http://download.redis.io/redis-stable.tar.gz && \
  tar xvzf redis-stable.tar.gz && \
  cd redis-stable && \
  make && \
  make install && \
  cp -f src/redis-sentinel /usr/local/bin && \
  mkdir -p /etc/redis && \
  cp -f *.conf /etc/redis && \
  rm -rf /tmp/redis-stable* && \
  sed -i 's/^\(bind .*\)$/# \1/' /etc/redis/redis.conf && \
  sed -i 's/^\(daemonize .*\)$/# \1/' /etc/redis/redis.conf && \
  sed -i 's/^\(dir .*\)$/# \1\ndir \/data/' /etc/redis/redis.conf && \
  sed -i 's/^\(logfile .*\)$/# \1/' /etc/redis/redis.conf

# Define mountable directories.
VOLUME ["/data"]

# Define working directory.
WORKDIR /data

# Define default command.
CMD ["redis-server", "/etc/redis/redis.conf"]

# Expose ports.
EXPOSE 6379