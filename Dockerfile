FROM tomcat:latest
COPY target/fintech-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/fintech-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=docker -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/fintech-0.0.1-SNAPSHOT.war" ]
