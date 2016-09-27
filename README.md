#Technical Evaluation App

Rod Bailey
Friday 24 June 2016

#Summary

This is an Android application created as a technical exercise for Android Developers. It aims to illustrate an exemplary architecture (MVP) and the use of some core technologies and patterns that find common application in Android development. The requirements document is in a PDF file in the root directory of this project, and is called *Android Developer - Step Code Review.pdf*.

#Screenshots

##Landscape

![Scenario 1 when device is in landscape orientation](http://www.github.com/replicant1/TechnicalEvaluation/doc/scenario_1_landscape.png)

#Design Overview
Individual features of the application (e.g. "scenario1", "scenario2") are structured according to the MVP pattern. The overall app architecture is also inline with MVP but there is an additional "service" layer through which all remote data access occurs.

Other aspects of the design:

- I try and keep the views as simple and "dumb" (devoid of logic) as possible.
- As per usual MVP, each layer only communiccates with the layer above and below it. View never talks to Model directly, but only via the intermediary of the presenter. Model never talks to View directly, but only via the intermediary of the presenter.
- Runtime configuration properties are set in the file `assets/config.properties`
- Views and Presenters communicate via well defined interfaces e.g. `IScenario1View`, `IScenario1Presenter`.
- Presenters send messages to Models synchronously, via well defined interfaces. 
- Models send messages to Presenters asynchrounsly using Events and an EventBus.

#### The Facade Pattern

The interface `ITravelTimeService` is a facade to backend service operations. There are two alternative implementations of it in `FakeTravelTimeService` and `TravelTimeService`. The former reads JSON data from the local file `assets/sample.json`, but the latter reads it over the internet. The `config.properties` file contains a property `TravelTimeService.fake.use` which specifies which implementation to actually use. The `FakeTravelTimeService` is useful for testing during development.

#### The Singleton Pattern

`EventBusSingleton`, `AppDirectorSingleton`, `ConfigSingleton`

#### The Observer Pattern

The publish / subscribe of events to the `Otto` event bus is an illustration of the Observer pattern.

#### The MVP Pattern

MVP is used at both the feature and architectural level. The **View** is either an Android `Activity` or an Android `Fragment`. For example `MainActivity`, `Scenario1Fragment`, `Scenario2Fragment`

#Orientation Changes

When the device orientation changes, the following occurs:
1. The `MainActivity` is destroyed and recreated by Android.
2. The fragments `Sceario1Fragment` and `Scenario2Fragment` are destroyed by Android
3. As part of their destruction, each fragment unregisters their associated presenters: `IScenario1Presenter` and `IScenario2Presenter`. See the `onDestroy()` methods of both.
4. The models behind each fragment are retained in the singleton `AppDirectorSingleton`. In this way, state has been preserved.
5. Android recreates `Scenario1Fragment` which, in its `onViewCreated` method creates a new `Scenario1Presenter` attached to the pre-existing `Scenario1Model`.
6. Android recreates `Scenario2Fragment` which, in its `onViewCreated` method creates a new `Scenario2Presenter` attached to the pre-existing `Scenario2Model`.
7. When each of the Presenters is recreated, they register themselves with the Otto Event Bus. Because they both `@Subscribe` to various property change events, the corresponding `@Produce` methods of the models are fired and there is a flood of events from Model to Presenter to bring the Presenter in line with the current state of the model.

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