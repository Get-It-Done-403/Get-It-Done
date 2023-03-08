# User Manual

## Table of Contents
* [Description](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#description)
* [How to install the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#how-to-install-the-software)
* [How to run the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#how-to-run-the-software)
* [How to use the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#how-to-use-the-software)
* [How to report a bug](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#how-to-report-a-bug)
* [Known bugs](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#known-bugs)

## Description
Get It Done is a productivity web application designed to help people schedule working sessions on their calendars corresponding to larger projects or assignments. This is different from any other calendar or productivity app because it breaks down tasks that are a big time commitment into smaller, more manageable sessions to help users complete their work in a timely manner and without the overhead of manually planning their schedule.

## How to run the software

1. Open your prefered browser
2. Navigate to https://GetItDone.com
3. Refer to [How to use the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/user-manual.md#how-to-use-the-software)

## How to use the software
When the system is started the user will initially be greeted with a login page. In order to access the main features of the application the user must login. If the user does not have an account they must make an account and then login.

#### Home Page
Once the user is logged in their browser will navigate to that users home page where they can view todays tasks (remaining and completed). If a user wishes to create a new task for that day they can click the create new task button in the remaining tasks section where the browser will then navigate to the create task page. The user can also navigate between days by clicking the next and back butons in the upper right and upper left corner of the home page. Should the user need to find a specific task the home page allows you to search for a specific task. Simply type the name of your desired task in the search bar in the upper right hand corner of the web app. The upcoming tasks and overdue tasks are a work in progress.

The user has the ability to navigate between the home page, calendar page, and settings page by clicking on their corresponding tabs in the sidebar left side of the web app. All pages will have this sidebar. Finally, the user can navigate to the profile page by clicking on their profile name at the top right of the web app. All pages will have this profile button.

#### Calendar Page
On the calendar page the user will be able to view their tasks in a calendar setting making them more visually digestible. From here they will be able to click on tasks and edit them or create new tasks if they like. However, this page is still a work in progress.

#### Settings Page
The settings page is still a work on progress.

#### Profile Page
On the profile page the user can reset their username by filling out the username form and clicking save. In addition, the user can sign out by clicking the sign out button.

#### Task Creation page
At the task creation page the user is able to create a task by filling out the task creation form. The user is able to create a name for the task, select a date and time the task will be completed on, and how long the task will take.

## How to report a bug

### For each bug make a separate report
Therefore, an effective bug report will be completed for each issue.

### Details you need
* How would you describe the bug using no more than 20 words?
* Explain the problems and not your suggested solution
* Make sure your software is up-to date with the required version for the application 
* Write the precise steps to reproduce this error
    * Indicate whether you can reproduce the bug every time, occasionally, or not at all
    * Describe how you interact with the software up until the bug
    * After executing these steps describe the result, vs the expected result
* Expected and Actual result
* Severity/Priority
### For specific bugs or crash bugs
* If you are running into a crashbug, include the stack trace that you received
* Include an error code if applicable _Ex : 404, 400, 505_

### To report the bug
Fill out the following Google Form link.
[Bug Report Form](https://forms.gle/jw5RDFBB5aZ5PaqY8)


## Known bugs

### Limitation: Google Calendar API Authentication Issue
* Currently when a new user tries to create a new task they would have to click on the link on the backend terminal in order to complete the authentication with the API. It does not have the feature to make the link pop up on the screen.

### Bug: Buggy Edit Task Feature
* The edit task feature sometimes causes overlaps for the edited version of the tasks. 
* We are not sure why this is happening since we are simply deleting the tasks and running the scheduling algorithm again. (The scheduling algorithm itself works fine without overlapping)
