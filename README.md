# Simpli CLI - API UsecaseServer

## How to Setup IntelliJ to run a local Tomcat
- Make sure you have a local database properly set
- On IntelliJ, on the top-right corner, on the dropdown menu, choose "Add Configuration" or "Edit Configuration"
- Add a new "Tomcat Server" > "Local"
- On "Deployment" tab, add a new Artifact selecting the "simplicli-apiusecase:war exploded" option. And on the bottom, in Application Context field, type "/usecase" 
- On "Server" tab, on "VM Options" field, add the following text:
```
-DENVIRONMENT=beta
-DDB_URL=localhost
-DDB_NAME=ndapp
-DDB_USER=root
-DDB_PASS=root
-DLOG_LEVEL=debug
-DLOG_NO_COLOR=false
```
Replace `root` with your MySQL username and password;

## How to Setup the Web application
- On a terminal, open the webapp folder (`cd src/main/webapp`)
- Run `npm i`

## How to Run the project
- Make sure you setup IntelliJ to run Tomcat and setup the web application
- Run the Server-side part by clicking the play icon on IntelliJ
- It will open a webpage on the browser, this is the compiled page to be used on beta or production server, it will probably show an error, but no problem, this page is not needed and can be closed
- On a terminal, open the webapp folder (`cd src/main/webapp`)
- Run `npm run serve`, this is the development server that updates automatically when the code is saved
- Done

## Things to do before the commit
- Check the terminal that is running `npm run serve` to see if there is any error
- Create **unit tests**, try to **cover** everything, you can check if everything is covered using IntelliJ tools to `Run 'All tests' with Coverage`
  - You may need to create a new test case, to do so, change the `ut_data.sql`. **Avoid breaking other tests while doing this**
- **Run all unit tests** of the project, if anything fails try to understand why it fails and do one of the following:
  - Fix your own code
  - Update the failling test
- If the changes affect the front-end, **run the project** on your browser to test the changes on all possible cases
- **Change the REQUIREMENTS.md** file informing which requirements were worked on (this step is better described in the file itself)
- Read your new code, make a **self code-review**

## Test Users
Password: `tester`

Login:
- `test@test.com` (administrator)
