# Ecosia Widget Assignment
[![Plumillon](https://circleci.com/gh/Plumillon/EcosiaWidgetAssignment.svg?style=shield&circle-token=dda052dc2a5a979d79284cbee176110d7011c155)](https://app.circleci.com/pipelines/github/Plumillon/EcosiaWidgetAssignment)

An assignment from Ecosia

### The assignment
The app to develop is a widget with the following requirements :
  - Select and play a random mp3 file from the device library
  - Include a stop and a start function
  - Display the elapsed time
  - Display basic metadata of the audio file
  - Have the ability to run in the background, allowing for other sounds to be output while continuing to play the .mp3
  - Be created without the use of third-party libraries

### Tech choices
The tech stack I chose are :
  - Clean Architecture approach
  - Dependencies injection via constructors (custom made due to the "no third-party libraries" requirement)
  - Use of a Service to play the audio
  - Java unit test (with use of Mockito)
  - Git version control (hosted on Github) and Gitflow
  - Continuous integration with CircleCI

### Features
![Preview](app/src/main/res/drawable/preview.jpg)
The widget offers the following feature, matching the requirements :
  - Select a random audio from the device library
  - Ability to play and pause
  - Display title and artist
  - Display the elapsed time
  - Run in background, allowing other sound to be played at the same time
  - (BONUS) Configuration to check and grant the required permissions
  - (BONUS) Ability to skip audio to play another one
  - (BONUS) Test Unit examples
  - (BONUS) Continuous integration with CircleCI

### Instructions
You can download the APK from CircleCI artifacts ([pick on the pipelines](https://app.circleci.com/pipelines/github/Plumillon/EcosiaWidgetAssignment)).

Please be aware it's a debug build so you have to authorize unknown source on your device.

### Possible improvisation
Because of the short time spent, this project will act as a sample and improvisations (must) can be done :
  - There is no comment on the code
  - Better UI feedback on error
