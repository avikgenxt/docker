FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/discovery-server-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/discovery-server.war
EXPOSE 8761
CMD ["catalina.sh","run"]