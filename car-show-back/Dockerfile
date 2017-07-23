FROM java:8-jre

MAINTAINER mahjoub marzougui "mahjoub.marzougui@olbati.com"

ADD ./target/carShow.jar /app/

ADD ./wait.sh /app/

RUN apt-get update && apt-get install -y netcat

RUN chmod +x app/wait.sh

CMD ["/app/wait.sh"]