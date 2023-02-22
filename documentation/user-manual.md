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

## How to install the software
If your system has prerequisites (e.g., tools, libraries, emulators, third-party applications, etc.), your instructions should list all of them and indicate how to install and configure them. Make sure to indicate what specific version requirements these prerequisites must satisfy. If running the system requires the installation of, e.g., a virtual machine, a database, or an emulator, make sure to provide clear step-by-step instructions.

## How to run the software
How to start up the system?

## How to use the software
You can assume that your user is familiar with your particular platform (e.g., use of a Web browser, desktop applications, or mobile applications). For missing functionality, your documentation should simply indicate that this functionality is work in progress.

## How to report a bug
This should include not just the mechanics (a pointer to your issue tracker), but also what information is needed. You can set up a bug-report template in your issue tracker, or you can reference a resource about how to write a good bug report. Here is an example for bug reporting guidelines.
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
Known bugs or limitations should be documented in the bug tracker. A user testing the implemented use case(s) should not encounter trivial bugs (e.g., NPEs) or a large number of bugs that are unlisted in your bug tracker.

### Bug: Schedule overlapping
* Occurs when a user has consecutive events planned back to back.
* The algorithm cannot detect the second event and will try to book the new event at the same time as the second event.
* The scheduling algorithm is being improved to handle edge cases like this.

### Limitation: Missing delete/edit features
* Currently, the application cannot edit or delete tasks from the user's Google Calendar.
* When a user edits the task, the soon to be implemented edit algorithm should first delete the calendar entries and run the scheduling algorithm again to adjust to the changes.
