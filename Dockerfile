FROM maven:3.5-jdk-11 AS build
COPY src /usr/src/table/src
COPY pom.xml /usr/src/table
RUN mvn -f /usr/src/table/pom.xml clean package

FROM jboss/wildfly
COPY --from=build /usr/src/table/target/TimeTable-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
