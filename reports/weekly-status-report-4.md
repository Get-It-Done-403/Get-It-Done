# Weekly Status Report 4

## Team Report

### Goals From Last Week:
* Have a base draft of the algorithm we will need for Calendar
* Complete TaskService
* Begin working on Calendar feature
* Break down tasks and add them to our GetItDone kanban board and add assignee

### Progress and Issues:
* Spring boot is online and connected with Firebase
* Calendar class is started and close to finish
#### Project Meeting Agenda:
* Discuss ideas for Calendar algorithm


### Plans and Goals:
* Have a base draft of algorithm for the calendar
* add functionality to import google calendar
* Make sure we can make api calls from the frontend


## Individual Contributions

* Tye Coleman
  * Goals from last week:
      - Start implementing the Calendar class
      - Connect Task and Calendar class to the database
   * Progress and Issues: 
      - Implemented CalendarController and most methods in CalendarService
      - Created HTTP endpoint tests
      - Planned out the algorithm for Task allocation
  * Plans and Goals:
      - Create more Junit tests for the Calendar and Task classes
      - Finish implementation for the Calendar class
      - Create the User class and connect with the database
* Mitchell Ly
    * Goals from last week:
    * Integrate these two pages with the backend (help with login and maybe start pulling and pushing code to the database)
      * Continue implementing more pages (settings and more)
      * Continue to revise and improve designs and functionality of pages
    * Progress and Issues:
      * Added pop up modules to add tasks
      * Set up forms to input data and send through with our APIs
      * Set up frontend testing to ensure correct functionality 
      * Started implementing profile page
    * Plans and Goals: 
      * Have login page designed and implemented along with the backend 
      * Add more tests 
      * Add functional buttons that actually do stuff now
* Sofia Barry
    * Goals from last week:
      - Help with breakdown of Tasks and create kanban for the team
      - Complete TaskService and test communication with Firebase
      - Assist Tye with the implementation of Calendar feature 
    * Progress and Issues:
      - Issue: There is an issue with the data type of the "date" field in the Task class. Not compatible with Firebase. Exploring alternatives.
      - Setup Gradle CI for Java testing
      - Completed TaskService, TaskController, and TaskRepository
    * Plans and Goals:
      - Find an alternative for "date" field data type compatible with Firebase
      - Thoroughly test TaskService with JUnit testing
      - Work on algorithm for Calendar
* Aidan Petta
    * Goals from last week:
      - Setup firebase to read and write data from frontend and backend
      - Talk about importing google calendar
    * Progress and Issues: 
      - Firebase is setup and springboot is setup
      - Calendar is almost done
    * Plans and Goals: 
      - Finish calendar
      - work on firebase auth with frontend
      - create the task algorithm
* Lance
    * Goals from last week:
    * Progress and Issues:
    * Plans and Goals:
