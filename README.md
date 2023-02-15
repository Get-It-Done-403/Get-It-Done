# Get it Done

## Beta Release

### User Cases for Beta Release

### Build and test the system

### Run the system

----------------------------------------
## Design
             
### Goals and Ideas

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
        |
        +- css
        | +- Login.css
        | +- mainCSS.css
        | +- Register.cc
        | +- Reset.css
        |
        +- fonts
        |
        +- pages
        | +- homePage
        |   +- HomePage.js
        |   |
        |   +- HomePageFlow.js
        |   |
        | +- CalendarPage.js
        | +- Register.js
        | +- Reset.js
        |
        +- App.css
        |
        +- App.js
        |
        +- App.test.js
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
