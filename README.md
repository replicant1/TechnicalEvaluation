##Technical Evaluation App

Rod Bailey
Friday 24 June 2016

#Summary

This is an Android application created as a technical exercise. It aims to illustrate an exemplary architecture (MVP) and the use of some core technologies and patterns that find common application in Android development.

#Design Overview
Individual features of the application (e.g. "scenario1", "scenario2") are structured according to the MVP pattern. The overall app architecture is also inline with MVP but there is an additional "service" layer through which all remote data access occurs.

Other aspects of the design:

- I try and keep the views as simple and "dumb" (devoid of logic) as possible.
- As per usual MVP, each layer only communiccates with the layer above and below it. View never talks to Model directly, but only via the intermediary of the presenter. Model never talks to View directly, but only via the intermediary of the presenter.
- Runtime configuration properties are set in the file `assets/config.properties`
- Views and Presenters communicate via well defined interfaces e.g. `IScenario1View`, `IScenario1Presenter`.
- Presenters send messages to Models synchronously, via well defined interfaces. 
- Models send messages to Presenters asynchrounsly using Events and an EventBus. I have only done this for Scenario 2 at the moment, as it involves use cases where asynchronous results are achieved. This is not so for Scenario 1, so I have saved some time by simply having the presenter update both model and view synchronously.

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

#Orientation Changes

Handling orientation changes in an implementation employing fragments is a challenge, because fragments are recreated, making it necessary to somehow preserve their state across the orientation change and recreate the views from the preserved state information. In this app, the class `AppDirectorSingleton` is the repository of all state info retained across orientation changes. 

I have not captured all of those attributes of the view's state that I might have e.g. the scroll position of scroll bars. Doing so would move the architecture closer to MVVP, but perhaps require a bidirectional binding library to achieve efficiently.

#Android Versions

This app will work on Android versions from API level 16 onwards.

#Libraries

The following third party libraries have been used:

- **GSON** for parsing of JSON
- **ViewPagerIndicator** for showing the circular page indicators
- **Otto** an event bus
- **Volley** for sending HTTP requests
- **ButterKnife** for injecting Views
- **Android Support Library** for backwards compatibility and the **card** widget
- **Google Play Services** for Google Maps

#TODO

Due to a limited time budget, I have not completed the following aspects of the exercise:

- Supporting distinct DEBUG and RELEASE flavors of the app
- Unit tests

Be aware that for unit testing I usually employ *Espresso* for instrumented tests and *JUnit* / *Mockito* for uninstrumented tests.