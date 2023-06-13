# [Restful-booker](https://restful-booker.herokuapp.com/) API automation project
<img title="Restful-booker" src="images/api-top.png"></div>

##  <a name="contents">:page_facing_up: Сontents</a>
- [Tools and technologies](#hammer_and_wrench-tools-and-technologies)
- [Test cases](#white_check_mark-test-cases)
- [How to run](#arrow_forward-how-to-run)
- [Test results report in Allure Report](#-test-results-report-in-allure-report)
- [Allure TestOps integration](#-allure-testops-integration)
- [Telegram notifications](#-telegram-notifications)

## :hammer_and_wrench: Tools and technologies
<a href="https://www.jetbrains.com/idea/"><img src="images/icons/intellij_idea.svg" title="IntelliJ IDEA" alt="IntelliJ IDEA" width="50" height="50"/></a>
<a href="https://www.java.com"><img src="images/icons/java.svg" title="Java" alt="Java" width="50" height="50"/></a>
<a href="https://junit.org/junit5"><img src="images/icons/junit5.svg" title="JUnit5" alt="JUnit5" width="50" height="50"/></a>
<a href="https://gradle.org"><img src="images/icons/gradle.svg" title="Gradle" alt="Gradle" width="50" height="50"/></a>
<a href="https://rest-assured.io"><img src="images/icons/rest_assured.svg" title="REST Assured" alt="REST Assured" width="50" height="50"/></a>
<a href="https://www.jenkins.io"><img src="images/icons/jenkins.svg" title="Jenkins" alt="Jenkins" width="50" height="50"/></a>
<a href="https://qameta.io/allure-report"><img src="images/icons/allure_report.svg" title="Allure Report" alt="Allure Report" width="50" height="50"/></a>
<a href="https://qameta.io"><img src="images/icons/allure_testops.svg" title="Allure Testops" alt="REST Assured" width="50" height="50"/></a>
<a href="https://web.telegram.org/"><img src="images/icons/telegram.svg" title="Telegram" alt="Telegram" width="50" height="50"/></a> 

## :white_check_mark: Test cases
- [x] Successful create a new auth token `POST`
- [x] Successful create a new booking `POST`
- [x] Successful update booking data `PUT`
- [x] Unsuccessful update booking without auth token `PUT`
- [x] Get booking request returns not null data `GET`
- [x] Get all booking ids returns status 200 `GET`
- [x] Delete request returns status 201 `DELETE`
- [x] Health check endpoint to confirm API is up `GET`

## :arrow_forward: How to run
To run tests locally and in [Jenkins](https://jenkins.autotests.cloud/job/gloomyana-restful-booker-API/) the following gradle command is used:
```bash
$ gradle clean test 
```
<img src="images/jenkins-project-page.jpg" alt="Jenkins project page">
 
After the build is done the test results are available in `Allure Report` and `Allure TestOps`

<img src="images/jenkins-build-page.jpg" alt="Jenkins build page"> 

[back to Contents ⬆](#contents)

## <img width="3%" title="Allure Report" src="images/icons/allure_report.svg"> Test results report in [Allure Report](https://jenkins.autotests.cloud/job/gloomyana-restful-booker-API/allure/)
### Overview page

Overview page of Allure report contains the following parts:
>- **ALLURE REPORT** displays date and time of the test, overall number of launched tests and chart showing the percentage and number of successful, fallen and broken tests
>- **SUITES** displays groups of tests that share a common context such as a specific test environment or a particular test category
>- **FEATURES** displays groups of tests according to Epic, Feature tags
>- **TREND** displays trend of running tests for all runs
>- **CATEGORIES** displays distribution of unsuccessful tests by defect types
>- **EXECUTORS** displays information on test executors that were used to run the tests

<img src="images/allure-result-main.jpg" alt="Allure Report Overview page">

### Graphs page
Graphs allow to see different statistics collected from the test data: statuses breakdown or severity and duration diagrams.

<img src="images/allure-result-graphs.jpg" alt="Allure Report graphs">

### Suites page
On the **SUITES** tab a standard structural representation of the executed tests, grouped by suites and classes can be found.
Each test case have information such as `severity`, `description`, `duration`, `test data` and execution `steps`.

<img src="images/allure-result-test-example.jpg" alt="Allure Report suites">

<img src="images/allure-result-test-example-2.jpg" alt="Allure Report suites">

### Attachment examples

<img src="images/allure-result-test-example-auth.jpg" alt="Allure Report auth example">

<img src="images/allure-result-test-example-request.jpg" alt="Allure Report request example">

Before tests,  a simple **health check** request to confirm whether the API is up and running is checked. \
Also creates a new **auth token** to use for access to the `PUT` and `DELETE` /booking.

<img src="images/allure-result-test-example-setup.jpg" alt="Allure Report test set up">

[back to Contents ⬆](#contents)

## <img width="3%" title="Allure TestOps" src="images/icons/allure_testops.svg"> [Allure TestOps](https://allure.autotests.cloud/project/3221/dashboards) integration
### Dashboards
<img src="images/allure-testops-dashboard.jpg" alt="Allure TestOps dashboards">

### Test cases
<img src="images/allure-testops-test-cases.jpg" alt="Allure TestOps test cases">

[back to Contents ⬆](#contents)

## <img width="3%" title="Telegram" src="images/icons/telegram.svg"> Telegram notifications

**Telegram bot** sends a report to a specified telegram chat by results of each project build.

<p align="center"><img src="images/telegram-notifications.jpg" alt="Telegram notifications">
</p>

[back to Contents ⬆](#contents)
