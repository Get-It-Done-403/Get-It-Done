# Developer Manual

## Table of Contents
* [How to obtain the source code](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-obtain-the-source-code)
* [Directory structure layout](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#directory-structure-layout)
* [How to build the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-build-the-software)
* [How to test the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-test-the-software)
* [How to add new tests](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-add-new-tests)
* [How to build a release of the software](https://github.com/Get-It-Done-403/Get-It-Done/blob/main/documentation/developer-manual.md#how-to-build-a-release-of-the-software)

## How to obtain the source code
If your system uses multiple repositories or submodules, provide clear instructions for how to obtain all relevant sources.

## The layout of your directory structure
Our root folder is mainly split up into three different parts, the frontend folder, the backend folder, and everything else that is needed for the app to actually run and documentation. 
The frontend folder contains everything that is related towards the website and our react app and tests for the react app too. The source folder for out frontend folder is split into 4 main parts which are the components, css, pages, and tests. All of our tests written with JEST are contained in our tests folder, all of our website pages are contained in pages, any code that is used multiple times is added into to components for better reusability and modularity, and anything styling related is stored in our css folder.
Our backend folder is split into two main different parts, which is all of our springboot code stored in main and all of our tests for the springboot code stored in test/java. For every new API endpoint feature, it is stored in main/java/com/cse403/getitdone that is stored in its own folder that is usually made up of its own controller and service user. For every new endpoint, it has it's own tests that is stored in the tests folder all written with JUNIT tests.

## How to build the software
Provide clear instructions for how to use your project’s build system to build all system components.

## How to test the software
Provide clear instructions for how to run the system’s test cases. In some cases, the instructions may need to include information such as how to access data sources or how to interact with external systems. You may reference the user documentation (e.g., prerequisites) to avoid duplication.

## How to add new tests
Are there any naming conventions/patterns to follow when naming test files? Is there a particular test harness to use?

## How to build a release of the software
Describe any tasks that are not automated. For example, should a developer update a version number (in code and documentation) prior to invoking the build system? Are there any sanity checks a developer should perform after building a release?
