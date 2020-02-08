FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/dbService-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/dbService.war
EXPOSE 8086
CMD ["catalina.sh","run"]