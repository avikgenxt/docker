FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/movie-info-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/movie-info-service-0.0.1.war
EXPOSE 8082
CMD ["catalina.sh","run"]