FROM eclipse-temurin:17-jre-focal

RUN mkdir -p /opt/spring
RUN addgroup --system app
RUN adduser --system --disabled-login --home /opt/spring --ingroup app app

COPY build/libs/fee-service-0.0.1.jar /opt/spring/service.jar

RUN chown -R app /opt/spring
RUN chgrp -R app /opt/spring

USER app

EXPOSE 6460

CMD ["sh", "-c","java -Xms1024m -Xmx1024m -jar /opt/spring/service.jar"]