# Pokedex Android
A Demo Application for implementing below items in an Android application,
* 100% [Kotlin](https://kotlinlang.org/)
* Test Driven Development in MVVM design pattern
* Multi Module Android Application Setup
* Code generation of boilerplate using [Hygen](https://www.hygen.io/).
* CI-CD using [Github Actions](https://github.com/features/actions).
* Dependency Injection using [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* Unit Testing via [kotest](https://github.com/kotest/kotest) and [Mockk](https://mockk.io/)
* Threading using [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines).
* Replacing LiveData with [Kotlin Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/).
* Ktlint

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

![Android CI](https://github.com/har5hit/PokeDex-Android/workflows/Android%20CI/badge.svg)

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/har5hit/PokeDex-Android)

![Screenshot](https://github.com/har5hit/PokeDex-Android/blob/master/previews/screenshot.png?raw=true)


# Architecture

![Overview](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

# Application Structure

Each individual feature folder structure

* **/data** (All data related files)
  * /entity_1 (for e.g: Person)
    * /model 
    * /dao 
    * /network
    * repository
    
* **/presentation** (All view related files)
  * /component_1 (for e.g: Person List)
    * /fragment
    * /viewmodel
    * /adapter
    * /view
      

# Code Generation

Directly Try it On: [![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/har5hit/PokeDex-Android)

### Prerequisite
* Install [Hygen](http://www.hygen.io/docs/quick-start)

### Generate View Components

```
hygen fragment new
```
![Screenshot](https://github.com/har5hit/PokeDex-Android/blob/master/previews/generate_fragment.png?raw=true)

```
hygen repository new
```
![Screenshot](https://github.com/har5hit/PokeDex-Android/blob/master/previews/generate_repository.png?raw=true)

# Github Actions (CI-CD) 

* [Android CI](https://github.com/har5hit/PokeDex-Android/blob/master/.github/workflows/android_ci.yml) - to verify every code push to/pull request on master branch is validated, error free and releases can be properly built.

![Workflow Complete](https://github.com/har5hit/PokeDex-Android/blob/master/previews/workflow_check.png?raw=true)

* [Android Artifact](https://github.com/har5hit/PokeDex-Android/blob/master/.github/workflows/android_artifact.yml) - Archives and hosts apk build on each tag push for sharing.

![Workflow Artifact](https://github.com/har5hit/PokeDex-Android/blob/master/previews/workflow_artifact.png?raw=true)

# Resources

## Pokemon Data

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png"/>

[PokeAPI](https://pokeapi.co/) - The RESTful Pokémon API

All the Pokémon data you'll ever need in one place,
easily accessible through a modern RESTful API.

## [Gitpod Android Configuration](https://github.com/vtorres/gitpod-flutter)

## App Icon
* ["Those Icons"](https://www.flaticon.com/authors/those-icons)

# License
```xml
Copyright 2020 Harshith Shetty (justadeveloper96@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
limitations under the License.
```
