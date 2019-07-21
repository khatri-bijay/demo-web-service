 # demo-web-service
Demo web service that manages pet and vet and appointment

There are two apps inside
1. Spring Boot Rest API
2. React Based Client

Build and Run Process
1. Rest
    a) mvn clean install -U
    b) java -Dserver.port = 8080 -jar 'web-service-rest-1.0.0.jar' (8080 is default)

2. Client
    a) npm install 
    b) npm run build
    c) npm start (note: it's a webpack dev server on port 9000)
