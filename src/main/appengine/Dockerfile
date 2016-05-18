FROM gcr.io/google_appengine/openjdk8
VOLUME /tmp
ADD seminaire-datastore-1.0-jar-with-dependencies.jar app.jar
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
