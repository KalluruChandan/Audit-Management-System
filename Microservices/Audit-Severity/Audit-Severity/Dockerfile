FROM openjdk:11
EXPOSE 8083
ADD target/audit-severity.jar audit-severity.jar
ENTRYPOINT ["java","-jar","/audit-severity.jar"]