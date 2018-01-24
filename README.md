# HeroesDemo

An attempt to apply functional programming to Android

Work in progress. 

## Motivation
Main idea is, that holding state and side effects should be pushed to edges of the architecture - UI and dataacess layers.

## MVP
Since it's a bit difficult to deal with configuration changes and the fact, that activities might not exist by the time network
calls or other asynchronous operations are finished, this issue is solved by introducing Presenters, that hold state - reference
to their views. Presenters in this setup are considered the UI edge of the architecture.

To retain instance of ongoing network calls etc, Presenters are kept in memory using `Loader`. `Loader` takes care of Presenter
surviving configuration changes and it also destroys presenters when they're no longer necessary (activity is completely destroyed, etc)

## Mutli-module architecture
Project features multi-module architecture, so that implementations of MVP, or data source code or similar can be changed later.

## Dependency Injection Strategy
For dependency injection project utilizes concept of `Reader`, that is inspired by Reader Monads from other functional programming
languages, but it's just very bare bones implementation of it. Idea is, that functions that need dependencies return a `Reader`.
`Reader` then provides context to the function calls, provided by calling `instanceOfReader.run(injectedContext)`.

## IO
Since functions in functional programming are supposed to be pure, without side effects, with clear return type value, that does
not bode very well with asynchronous operations, where in the typical approach the return type would be `Unit`, with some kind of
callback function being called later. In this project, asynchronous code is wrapped into `IO` type, that is bare-bones attempt at 
implementing IO Monad from other functional programming languages.

## Tests
Tests in this app are integration tests. The way this system is designed, it is very simple to mock context passed into function
calls, that can be then used to verify that tested functions behave as expected.
