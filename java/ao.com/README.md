
## Tool/Libraries/Framework/plugins Used
  - Cucumber-Java framework 
  - Hamcrest-all
  - Maven
  - Java
  
    
  
### How to Run
##### via Intellij IDE
A Cucumber runner class is defined in (src/test/java/com/automation/assessment/RunCukesTest.java)

  Edit configurations for RunCukesTest if need to change uri & browser 

```sh
-ea -Duri="https://www.ao.com" -Dbrowser=chrome
```

##### via Command line 
```sh
mvn clean -Duri="https://www.ao.com" -Dbrowser=chrome test
         or 
 to run with defult browser,URL: 
 
 mvn clean test or mvn clean install 
```