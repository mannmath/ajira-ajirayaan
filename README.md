# ajira-ajirayaan
A rover in an environment

## Deployment Instructions
  1. This is a spring boot application. The .war file after `maven clean build` is located under `target/`.
  2. The application can be run directly if the host system has spring boot, else the .war file can be deployed to any server having a srevlet configuration to run the application.
  3. To run the .war file in tomcat, copy it from `target/` to the `tomcat/webapps/` folder
  4. From a terminal navigate to `tomcat/bin` folder and execute
      `catalina.bat run (on Windows)` or
      `catalina.sh run (on Unix-based systems)`
  5. Go to `http://<machine ip>:<port>/<war filename without .war extension>/` and start running the APIs.
