# e2e-boiler-plate-tests

This repo houses e2e-boiler-plate-tests - that can be used as a quick starting point for creating a set of integration, selenium, appium, test fixture, OCR, etc. end to end tests for end point, browser based, Android, iOS phone devices plus associated test fixtures.  This is because we need to be able to mix and match tests on a variety of devices.  For example, a browser may be used to pass configurations to a data base, that changes a mobile apps behavior, when accessing a hardware device.  The backend may need additional end points called to do things like place the app into a known state.  Test harnesses may also need to be controlled via the command line, UART, serial bluetooth, etc.  This repo gives boiler plate to get all of the many types of testing up and running quickly.

To use this repo:

1. Install Browsers, Selenium, Selenium Server, XCode, Android Studio, Appium, Appium Studio, Appium Server,  Arduino, Python, Node, etc.
2. Download the repo to /Users/Shared/code
3. Startup either the Appium studio or Appium Server
4. Add an Android device with the OS defined in src/test/java/scenarios/AndroidSetup
5. Build and run the tests

Design:

This repo follows a basic Page Object and Sequence Model design.
The Page Object model implements a group of model classes that correspond one-to-one to each page in the app.  This abstraction level allows a UI change to the app to only need the page object to be changed to reflect it making code maintenance much easier.  

The sequence model abstracts a series of steps allowing repetitive tasks such as logging in or pairing devices to also reduce code change to a single place.

The design also incorporates a class heirachy to extend the functionality of the Selenium and Appium languages to create new verbs for a series of Selenium and Appium commands, for example, clickWhenClickable will wait for an item to become clickable, prior to clicking it.

