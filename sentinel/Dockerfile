FROM openjdk:8
ENV SENTINEL_HOME /opt/sentinel-dashboard
RUN mkdir -p ${SENTINEL_HOME}
COPY sentinel-dashboard-1.6.3.jar ${SENTINEL_HOME}
WORKDIR ${SENTINEL_HOME}
RUN chmod -R +x ${SENTINEL_HOME}/*jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","sentinel-dashboard-1.6.3.jar"]