# quarkus
In quarkus- controller is called as resource
mvn quarkus:dev
mvn compile quarkus:dev
mvn package quarkus:dev


[s] - Force restart                                                                
[e] - Edits the command line parameters and restarts ()                            
[i] - Toggle instrumentation based reload (disabled)                               
[l] - Toggle live reload (enabled)                                                 
[j] - Toggle log levels (INFO)                                                     
[h] - Show this help                                                               
[:] - Enter terminal mode                                                          
[q] - Quit the application  

Command :
Check Available Extension:  mvn quarkus:list-extensions
Add new extension:          mvn quarkus:add-extension -Dextensions="extension-name"
 
eg. mvn quarkus:add-extension -Dextensions="quarkus-smallrye-openapi"
mvn quarkus:add-extension -Dextensions="quarkus-rest-client"
mvn quarkus:add-extension -Dextensions="quarkus-smallrye-fault-tolerance"


http://localhost:8080/q/swagger-ui/


while true; do 
  start=$(date +%s%3N); 
  curl -s http://localhost:8080/microprofile-rest-client-fault-tolerance-part3/weather/dubai; 
  end=$(date +%s%3N); 
  echo -e "\nResponse Time: $((end - start)) ms\n"; 
  sleep 1; 
done



while true; do sleep 1; curl -w "\nTime: %{time_total} sec\n" -s http://localhost:8080/microprofile-rest-client-fault-tolerance-part3/weather/dubai; echo -e '\n'; done

while true; do sleep 1; curl http://localhost:8080/microprofile-rest-client-fault-tolerance-part3/weather/dubai; echo -e '\n'; done