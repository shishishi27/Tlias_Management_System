FROM ubuntu:22.04

LABEL authors="shishishi"

RUN apt update && apt install -y wget locales && \
    locale-gen en_US.UTF-8 && \
    apt clean && rm -rf /var/lib/apt/lists/*

RUN wget --no-check-certificate \
    https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz \
    -O /usr/local/jdk21.tar.gz && \
    tar -xzf /usr/local/jdk21.tar.gz -C /usr/local/ && \
    rm /usr/local/jdk21.tar.gz

ENV JAVA_HOME=/usr/local/jdk-21.0.9
ENV PATH=$JAVA_HOME/bin:$PATH

ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

RUN groupadd -r tlias && useradd -r -g tlias tlias

RUN mkdir -p /tlias && chown -R tlias:tlias /tlias
WORKDIR /tlias

COPY tlias-0.0.1-SNAPSHOT.jar tlias.jar

EXPOSE 8080

USER tlias

ENTRYPOINT ["java", "-jar", "tlias.jar"]
