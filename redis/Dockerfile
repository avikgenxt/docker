FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/redis-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/redis.war
EXPOSE 8087
CMD ["catalina.sh","run"]