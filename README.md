# YouiTest
This is a java maven project, using selenium as testing tool, junit as testing framework, cucumber as BDD framework, extent report as report framework, and github Action as CI/CD solution.

## 5 ways to run the tests
- Running through the TestRunner for IDE
  > Executed runner file located in **"src/test/java/util/TesterRunner"** 
- Running through mvn command in terminal in browser mode
  > **mvn clean install** 
- Running through mvn command in terminal in headless mode
  > **mvn clean install -Denv.USER=remote**
- Running on remote with a click of a button
  > - Go to **Actions** page, select **ScalaPayPipeline** workflow and click on **Run workflow**.
  > - For the drop down, can input a version No in Release Version text box (optional), if added the artefact produced will have the version No. attached
  > - And if any test tag inputted into **Excluded Scenario** text box(optional), test case will be ignored.
  > - Click on Run workflow will trigger the run
  ![image](https://user-images.githubusercontent.com/26472066/189790859-feb2272b-d6e2-4735-95fe-e2b5d512f2ae.png)


- Running on remote server by triggering through a pull or push 
