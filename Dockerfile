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

RUN pip install beautifulsoup \
                newspaper

WORKDIR /app
COPY . .

RUN ./build.sh

RUN curl https://raw.githubusercontent.com/moewe-io/stopwords/master/dist/hr/hr.txt \
    | tr , '\n' > /usr/local/lib/python2.7/dist-packages/newspaper/resources/text/stopwords-hr.txt

CMD java -jar Feedsucker.jar > run_output.txt









