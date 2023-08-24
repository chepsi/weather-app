# Weather App

An application that consumes the [Open Weather API](https://openweathermap.org/api) to display
weather information for a users location

#Building Your Application
In your `local.properties` add the line below

```
API_KEY = Your Open Weather api key
```

The api key can be found [here](https://home.openweathermap.org/api_keys)

*Environment*

- Built on A.S Hedgehog
- JDK 17

# Design/Architectural decisions 📐

The project makes use of common android patterns in modern android codebases.

**Project Structure**
Inside the Core directory, the Project has 5 main different modules with the following names and
libraries used

1. **Presentation**
   Contains All the UI logic; Screens, ViewModel, Theme etc.
    - [Compose](https://developer.android.com/jetpack/compose)
    - [HiltCompose](https://developer.android.com/jetpack/compose/libraries)
2. **Domain**
   Contains Repository Interface and Domain Models

3. **Data**
   Contains Repository Implementation and Data Models as well as mappers from DataSources to Data
   and Data to Domain

4. **LocalDataSource**
   Contains logic to fetch current user location and cache as well as entities for the database
    - [Room](https://developer.android.com/jetpack/androidx/releases/room)
    - [Google Play Services](https://developers.google.com/android/guides/setup)
5. **RemoteDataSource**
   Contains logic to make network calls
    - [kotlinx.serialization](https://kotlinlang.org/docs/serialization.html)
    - [Ktor Client](https://ktor.io/docs/getting-started-ktor-client.html)

# Technologies 🔨

**The Application is fully written in :** [Kotlin](https://github.com/JetBrains/kotlin)

* For testing I used the following libraries

- [Junit5](https://github.com/mannodermaus/android-junit5)
- [Mockk](https://mockk.io/)
- [CoroutinesTest](https://developer.android.com/kotlin/coroutines/test)
- [Ktor Client Test](https://ktor.io/docs/http-client-testing.html)

* Tools and General setup

- [Gradle secrets plugin](https://github.com/google/secrets-gradle-plugin)
- [Hilt - For Dependency injection](https://developer.android.com/training/dependency-injection/hilt-android)
- [Timber - For viewing logs](https://github.com/JakeWharton/timber)
- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle)


* Workflows
- The app has a (github action workflow)[https://github.com/chepsi/weather-app/actions] to run tests
  and build the application once a PR is raised against the main branch

# Architecture

![Add MVVM diagram](/docs/mvvm.png)

- For the Presentation Layer I used MVVM. I have the MainScreen, MainViewModel and MainScreenState

![Add Clean architecture diagram](/docs/clean_arch.png)

- Generally I used clean architecture to Separate the different layers of the application as
  outlined above
