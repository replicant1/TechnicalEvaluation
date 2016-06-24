Technical Evaluation App
========================

Rod Bailey
Friday 24 June 2016

Summary
-------

This is an Android application created as a technical exercise. It aims to illustrate an exemplary architecture (MVP) and the use of some core technologies and patterns that find common application in Android development.

Design Overview
---------------
Individual features of the application (e.g. "scenario", "scenario2") are structured according to the MVP pattern. The overall app architecture is also inline with MVP but there is an additional "service" layer through which all remote data access occurs.

#### The Facade Pattern

The interface `ITravelTimeService` is a facade to backend service operations. There are two alternative implementations of it in `FakeTravelTimeService` and `TravelTimeService`. The former reads JSON data from the local file `assets/sample.json`, but the latter reads it over the internet. The `config.properties` file contains a property `TravelTimeService.fake.use` which specifies which implementation to actually use. The `FakeTravelTimeService` is useful for testing during development.

#### The Singleton Pattern

`EventBusSingleton`, `AppDirectorSingleton`, `ConfigSingleton`, `JsonUtils`

#### The Observer Pattern

The publish / subscribe of events.

#### The Adapter Pattern

ListAdapter and PageAdapter.

#### The MVP Pattern

MVP is used at both the feature and architectural level. The **View** is either an Android `Activity` or an Android `Fragment`. For example `MainActivity`, `Scenario1Fragment`, `Scenario2Fragment`

Versions
--------
This app will work on Android versions from API level 16 onwards.

Libraries
---------

The following third party libraries have been used:

- **GSON** for parsing of JSON
- **ViewPagerIndicator** for showing the circular page indicators
- **Otto** an event bus
- **Volley** for sending HTTP requests
- **Android Support Library** for backwards compatibility and the **card** widget
- **Google Play Services** for Google Maps

Shortcomings
------------
Due to a limited time budget, I have not completed the following aspects of the exercise:

- Supporting distinct DEBUG and RELEASE flavors of the app
- Unit tests

Be aware that for unit testing I usually employ *Espresso* for instrumented tests and *JUnit* / *Mockito* for uninstrumented tests.