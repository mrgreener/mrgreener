FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle install --no-daemon

FROM openjdk:11

EXPOSE 8080

ENV APPLICATION_USER ktor
RUN adduser --disabled-password --gecos '' $APPLICATION_USER

RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

USER $APPLICATION_USER
COPY --from=build /home/gradle/src/build/install/mrgreener/ /app/
WORKDIR /app

HEALTHCHECK --interval=5s --timeout=3s CMD curl --fail http://localhost:8080/api/v1/health || exit 1

ENTRYPOINT ["/app/bin/mrgreener"]