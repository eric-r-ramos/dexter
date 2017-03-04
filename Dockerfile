FROM openjdk:latest

RUN mkdir -p /home/dexter/
RUN useradd dexter -d /home/dexter/
RUN chown -R dexter. /home/dexter/

USER dexter

ADD target/dexter-0.1.0.jar /home/dexter/

WORKDIR /home/dexter/

ENTRYPOINT java -jar dexter-0.1.0.jar

EXPOSE 8080