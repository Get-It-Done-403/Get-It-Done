# Get it Done

## Table of Contents
* [Beta release](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#beta-release)
  * [Build and test the system](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#build-and-test-the-system)
  * [Run the system](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#run-the-system) 
* [Goals and Ideas](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#goals-and-ideas)
* [Repo layout](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#repo-layout)

## Beta Release

### Operational User Cases for Beta Release
1. User is able to log in
2. User is able to add a new task
3. User is able to search a task
4. User is able to edit task entries
5. User is able to see scheduled tasks on their Google Calendar

### Build and test the system

To build and test this application, you will need [Git](https://git-scm.com/), [Node Js](https://nodejs.org/en/download/), and [Gradle](https://gradle.org/install/). This project uses the [Spring Boot framework](https://spring.io/), Java's JDK 17 for the backend architecture and [React JS](https://reactjs.org/) for the frontend. 


From your command line:

```
# Clone this repository
$ git clone https://github.com/Get-It-Done-403/Get-It-Done.git

# Go into the server directory
$ cd backend

# Run the server
$ ./gradlew bootRun

# Test the server
$ ./gradlew test

# Go to the react project directory
$ cd ../frontend/get-it-done-frontend 

# Install dependencies
$ npm install

# Run the app
$ npm start
```
**Note on testing:** It is likely that current test will fail depending on entries on the database. If there are changes to the data base entries, the tests will need to be updates to match existing data in the database. Our testing uses ```RestTemplate```.

### Run the system

In order to run the application, the server must be started and then we can run our frontend. From command your line:
```
# Go into the server directory
$ cd backend

# Run the server
$ ./gradlew bootRun

# Go to the react project directory
$ cd ../frontend/get-it-done-frontend 

# Run the app
$ npm start
```

### How to use the software

When the system is started the user will initially be greeted with a login page. In order to access the main features of the application the user must login. If the user does not have an account they must make an account and then login.

## Home Page
Once the user is logged in their browser will navigate to that users home page where they can view todays tasks (remaining and completed). If a user wishes to create a new task for that day they can click the create new task button in the remaining tasks section where the browser will then navigate to the create task page. The user can also navigate between days by clicking the next and back butons in the upper right and upper left corner of the home page. Should the user need to find a specific task the home page allows you to search for a specific task. Simply type the name of your desired task in the search bar in the upper right hand corner of the web app. The upcoming tasks and overdue tasks are a work in progress.

The user has the ability to navigate between the home page, calendar page, and settings page by clicking on their corresponding tabs in the sidebar left side of the web app. All pages will have this sidebar. Finally, the user can navigate to the profile page by clicking on their profile name at the top right of the web app. All pages will have this profile button.

## Calendar Page
On the calendar page the user will be able to view their tasks in a calendar setting making them more visually digestible. From here they will be able to click on tasks and edit them or create new tasks if they like. However, this page is still a work in progress.

## Settings Page
The settings page is still a work on progress.

## Profile Page
On the profile page the user can reset their username by filling out the username form and clicking save. In addition, the user can sign out by clicking the sign out button.

## Task Creation page
At the task creation page the user is able to create a task by filling out the task creation form. The user is able to create a name for the task, select a date and time the task will be completed on, and how long the task will take.

----------------------------------------
             
## Goals and Ideas

* Ideas 
  - A productivity web application that allows users to plan, track assignment progress, and view their calendar. 
  - By inputting an assignment’s name, due date, and approximate time commitment, Get it Done, allocates hours into a user’s schedule to work on an assignment based on previously user-declared availability. 

* Goals
  - To create an easy-to-use productivity planner
  - Create an app to manage time better
  - Reduce the stresses that come with planning school work and events
  - Create an algorithm that creates a study schedule for students
  
  
  ### Repo Layout
  ```
  +- frontend
    +- get-it-done-frontend
      +- src 
        +- components
        | +- AddTask.js
        | +- EditTask.js
        | +- Header.js
        | +- NavBar.js
          +- auth
          | +- AuthDetails.js
          | +- ForgotPassword.js
          | +- GetItDone.png
          | +- SignIn.js
          | +- SignUp.js
        |
        +- css
        | +- mainCSS.css
        | +- Register.cc
        | +- Reset.css
        |
        +- fonts
        | +- Asar-Regular.tff
        | +- PlayfairDisplay-Italic-VariableFont_wght.tff
        | +- PlayfairDisplay-VariableFont_wght.tff
        |
        +- pages
        | +- homePage
        |   +- HomePage.js
        |   |
        |   +- HomePageFlow.js
        |   |
        | +- CalendarPage.js
        | +- ProfilePage.js
        | +- Register.js
        | +- Reset.js
        | +- SettingsPage.js
        |
        +- App.css
        |
        +- App.js
        |
        +- App.test.js
        |
        +- firebase.js
        |
        +- index.css
        |
        +- index.js
        |
        
  +- backend
    +- java
      +-com
         +- cse403
             +- getitdone
                 +- FireBaseADMIN.java
                 |
                 +- GetitdoneApplication.java
                 |
                 +- customer
                 |   +- Customer.java
                 |   +- CustomerController.java
                 |   +- CustomerService.java
                 |   +- CustomerRepository.java
                 |
                 +- task
                 |   +- Task.java
                 |   +- TaskController.java
                 |   +- TaskService.java
                 |   +- TaskRepository.java
                 |
                 +- calendar
                 |   +- CalendarService.java
                 |
                 +- googleCalendar
                 |  +- GoogleCal.java
                 |  +- ScheduleService.java
                 |
                 +- utils
                 | +- CalendarEntry.java
                 |
               

     ```
