FROM openjdk:7u131-jdk

RUN apt-get update && apt-get install -y --no-install-recommends \
                    build-essential \
                    libxml2-dev \
                    libxslt1-dev \
                    python \
                    python-dev \
                    python-setuptools \
                    zlib1g-dev

RUN easy_install pip

RUN pip install newspaper

WORKDIR /app
COPY . .

RUN ./build.sh

RUN ./feedsucker.sh LOOP START default_java









