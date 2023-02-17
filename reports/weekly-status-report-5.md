# Weekly Status Report 3

## Team Report

### Goals From Last Week:
* Have a base draft of algorithm for the calendar
* add functionality to import google calendar
* Make sure we can make api calls from the frontend

### Progress and Issues:
* Implemented and tested main functionality (add a Task and edit a Task, schedule a Task, and log in)
* We ran into unexpected bugs in our schedule Task Service when we were trying to connect to Google Calendar API, and similarly we ran into issues connecting the log in to firebase
* Previously mentioned issues prevented us from being able to complete the demo on time

#### Project Meeting Agenda:
* Come up with an action plan for the upcoming week:
  * Final release
  * User documentation 
  * Developer documentation
  * pull request (must be tested, commented, and code reviewed) vs pushing directly to main
  * Commented Code?

### Plans and Goals:
* Complete final implementation by the end of the day Sunday
* Complete final testing and minor bug correction by the end of the day Monday
* Complete user and developer documentation by Tuesday

## Individual Contributions

* Tye Coleman
  * Goals from last week:
    - Create more Junit tests for the Calendar and Task classes
    - Finish implementation for the Calendar class
    - Create the User class and connect with the database
  * Progress and Issues:
    - Implemented Schedule Service which creates new calendar entries and adds to the user's google calendar
    - There are still edge cases that needs handling for schedule task
    - Sometimes the results I get for schedule task is in consistent 
  * Plans and Goals:
    - Handle errors and edge cases in Schedule Service
    - Make sure the user only needs to log into google once to use the google calendar api
    - Make tests for schedule service
* Mitchell Ly
  * Goals from last week:
    - Have login page designed and implemented along with the backend
    - Add more tests
    - Add functional buttons that actually do stuff now  
  * Progress and Issues: 
    - Connected Add Task to backend
    - Connected Edit Task to backend
    - Connected Get Tasks to backend 
    - Added search functionality 
    - Connected username to backend and change username 
    - Added signin auth with email and google 
    - Connecting to backend was really hard at first, we kept accidently maxing out our database 
    - Fixed all ESLINT warnings in the code 
    - Made a lot more code reusable by putting them in componenets 
  * Plans and Goals: 
    - Fix a couple of minor issues with the backend 
    - Imrpove front end visibility and usability 
    - Add settings page and calendar page   
* Sofia Barry
    * Goals from last week:
      - Find an alternative for "date" field data type compatible with Firebase
      - Thoroughly test TaskService with JUnit testing
      - Work on algorithm for Calendar 
    * Progress and Issues:
      - Found alternative for dueDate data type that is compatible with Firebase and Google Calendar API
      - Tested TaskService endpoints
      - Worked high level algorithm for scheduling
    * Plans and Goals:
      - Come up with an action plan for the next few days
      - Revist user cases that have not been implemented yet and assign todos to board accordingly
* Aidan Petta
    * Goals from last week: 
        - Finish calendar
        - work on firebase auth with frontend
        - create the task algorithm
    * Progress and Issues: 
        - Firebase Auth is working and reset password is working
        - Task algoritm is done
        - google calendar api is implemented
        - Small bug cases for the google calendar api
        - added the ability to have a username
    * Plans and Goals: 
        - Fix remaining bugs
        - help frontend with styling
        - finish security details and security implementation with spring boot
* Lance
    * Goals from last week:
       - Set up sign up
       - set up sign in
       - set up password reset
    * Progress and Issues:
       - Finished sign up
       - Finishd sign in
       - created password reset
       - record demo video
       - Struggled setting up firebase auth
       - Did not have time to write many frontend tests 
    * Plans and Goals:
       - Create more comprehensive frontend testing
       - Help with scheduling algorithm
       - Help with documenation
