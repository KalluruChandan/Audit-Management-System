FROM openjdk:11
EXPOSE 8082
COPY target/audit-benchmark.jar audit-benchmark.jar
ENTRYPOINT [ "java" , "-jar" , "audit-benchmark.jar" ]