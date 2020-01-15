FROM tomcat:alpine
USER root
RUN rm -rf /usr/local/tomcat/webapps/
COPY ./target/Usecase.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
