FROM openjdk:17
LABEL developer="Amanuel Zerfu acceptedamanuel@gmail.com"
EXPOSE 9999
ADD target/Theater-0.0.1.jar Theater-0.0.1.jar
ENTRYPOINT ["java","-jar","/Theater-0.0.1.jar"]
