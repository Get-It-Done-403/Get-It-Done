# Developer Manual

## Table of Contents
* [How to obtain the source code](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-obtain-the-source-code)
* [Directory structure layout](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#directory-structure-layout)
* [How to build the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-build-the-software)
* [How to test the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-test-the-software)
* [How to add new tests](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-add-new-tests)
* [How to build a release of the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-build-a-release-of-the-software)

## How to obtain the source code
To obtain the source code for this project
From your command line:
```
# Clone this repository
$ git clone https://github.com/Get-It-Done-403/Get-It-Done.git

# Go to the react project directory
$ cd ../frontend/get-it-done-frontend 

# Install dependencies
$ npm install
```
### Configure Google's Firebase

Please contact developers to provide you with the corresponding keys to configure Google's Firebase. 

**Contact Info**
* Aidan Peta - aidanjp8@uw.edu
* Tye Coleman - tcolem@uw.edu
* Mitchell Ly - lymit000@uw.edu
* Sofia Barry - smbarry@uw.edu

Once you have been provided with the ```servicekey.json``` copy content into ```../backend/src/main/resources/servicekey.json```.

Similarly, in ```../frontend/get-it-done-frontend/src/firebase.js``` copy the provided key into firebaseConfig. You will see something like this:

```
const firebaseConfig = {
    DELETED
};
```
Replace "DELETED" with the provided key.

## The layout of your directory structure
  Our root folder is mainly split up into three different parts, the frontend folder, the backend folder, and everything else that is needed for the app to actually run and documentation. 
  The frontend folder contains everything that is related towards the website and our react app and tests for the react app too. The source folder for out frontend folder is split into 4 main parts which are the components, css, pages, and tests. All of our tests written with JEST are contained in our tests folder, all of our website pages are contained in pages, any code that is used multiple times is added into to components for better reusability and modularity, and anything styling related is stored in our css folder.
  Our backend folder is split into two main different parts, which is all of our springboot code stored in main and all of our tests for the springboot code stored in test/java. For every new API endpoint feature, it is stored in main/java/com/cse403/getitdone that is stored in its own folder that is usually made up of its own controller and service user. For every new endpoint, it has it's own tests that is stored in the tests folder all written with JUNIT tests.

## How to build the software
Please reference [here](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#build-and-test-the-system) for building the software.

## How to test the software
Please reference [here](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/README.md#build-and-test-the-system) for testing the software.

## How to add new tests
When adding new tests for the backend the tests should be added into of "TheFileName"Test.java for whatever endpoint you are testing in the test/java directory.
For frontend tests, they should all be stored in the src/tests directory as "PageName".test.js for whatever page you are testing.

## How to build a release of the software
To release a new build of a software, the main branch must be up to date, the GetitDoneApplication script must be run to start the server, and npm prod must be ran in the get-it-done-frontend directory in the terminal
