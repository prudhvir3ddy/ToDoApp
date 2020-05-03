# ToDoApp
###### A pretty simple notes app covering modern android concepts

![Android CI](https://github.com/prudhvir3ddy/ToDoApp/workflows/Android%20CI/badge.svg)


<a href='https://play.google.com/store/apps/details?id=com.prudhvir3ddy.todo_app_gettingthingsdone'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width = "150px"/></a>

<table>
   <tr>
     <td><kbd><img src="./screenshots/ss1.png"></kbd></td>
     <td><kbd><img src="./screenshots/ss2.png"></kbd></td>
     <td><kbd><img src="./screenshots/ss3.png"></kbd></td>
     <tr> 
      <td><kbd><img src="./screenshots/ss4.png"></kbd></td>
     <td><kbd><img src="./screenshots/ss5.png"></kbd></td>
     <td><kbd><img src="./screenshots/ss6.png"></kbd></td>
    </tr>
     <tr> 
      <td><kbd><img src="./screenshots/ss7.png"></kbd></td>
     <td><kbd><img src="./screenshots/ss8.png"></kbd></td>
     <td><kbd><img src="./screenshots/ss9.png"></kbd></td>
</table>

### Features

* Be able to add tasks and with image support
* Automatically delete tasks which are completed for every 15 minutes
* Coming soon...


## How To Contribute

I am happy to accept contributions from the community. Please file issues before making Pull Requests.

This project uses Firebase and therefore relies on a `google-services.json` configuration file. This file is not included in this repo and every contributor is encouraged to generate their own.

When importing the project in Android Studio the build task will fail with the following error:

`org.gradle.api.GradleException: File google-services.json is missing. The Google Services Plugin cannot function without it.`

Or something similar depending on the Android Studio version you're using.

In order to generate a `google-services.json` configuration file follow these steps (Note: requires a Google account):

- Open the [Firebase Console](https://console.firebase.google.com/).
- Login with your Google account.
- Create a new project (name doesn't matter).
- Select "_Add Firebase to your Android app_".
- Provide package name:  `com.prudhvir3ddy.todo_app_gettingthingsdone`.
- Register app.
- Download `google-services.json` file.
- Follow instructions to add file to project.
- Skip "_Add Firebase SDK step_".
- Run app to verify that the configuration is picked up correctly.
