FROM registry.cn-hangzhou.aliyuncs.com/zhangwei-repository/oracle-jdk1.8
ADD ./target/*.jar  /app.jar
RUN chmod +x /app.jar
CMD ["java","-jar","app.jar"]