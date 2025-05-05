## PODMAN POSTGRES

1.  podman run --rm --name pg-camel -e POSTGRES_USER=cameluser -e POSTGRES_PASSWORD=camelpass -e POSTGRES_DB=camel -p 5432:5432 postgres:16
2.   psql -h localhost -p 5432 -U cameluser -d camel
     Password for user cameluser: camelpass
3.   lista i db: \l
4. CREATE TABLE CAMEL_MESSAGEPROCESSED (
   processorName VARCHAR(255) NOT NULL,
   messageId VARCHAR(100) NOT NULL,
   createdAt TIMESTAMP NOT NULL,
   PRIMARY KEY (processorName, messageId)
   );

5. (crea la cartella /resources/input poi...) echo "Test idempotenza $(date)" > /home/dscrimie/Documents/camelFile/camelFile/src/main/resources/input/test.txt
6. La rotta riscrive il file in target/output




