FROM jboss/wildfly
COPY target/TimeTable-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/