# Deployment Service

### Instruction to run this app locally:

##### If you've already launched this app before: run 'git pull' and then go from step 6 to 9. If not - go from step 1.

1. download git repository for 'deployment-service':  
   1.1. open folder to clone repo  
   1.2. run 'git clone https://github.com/microservices-course-itmo/deployment-service.git'

2. switch to branch 'local-version':  
   2.1. open folder where your repo was cloned: 'cd deployment-service'  
   2.2. to switch to local-version branch run 'git checkout local-version'

3. download docker desktop:  
   3.1.1. follow istructions on this page for Mac: https://docs.docker.com/docker-for-mac/  
   3.1.2. follow istructions on this page for Windows: https://docs.docker.com/docker-for-windows/

4. install java version 11+:  
   4.1. download and install java from this page: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html  
   4.2. set JAVA_HOME environment variable to where you installed java  
   4.3. add $JAVA_HOME/bin to your PATH evironment variable  
   4.4. check installation with 'java --version'

5. run mongoDB in docker container:  
   5.1. open docker desktop app  
   5.2. go to 'deployment-service' directory and run 'docker-compose -f docker-compose.yml up'

6. install the project:  
   6.1. run './mvnw clean install -s settings.xml'

7. run the application:  
   7.1. run 'java -jar target/deployment-service.jar'

8. run the docker swarm:  
   8.1. run 'docker swarm init'

9. check the app health:  
   9.1. go to http://localhost:8080/swagger-ui.html#/ page and check all available endpoints  
