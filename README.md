# EvoLib-Demo Version 1.0.0 - 8/13/2018
EvoLib-Demo is a sample project showing how to use EvoLib (https://github.com/000haitham000/evolib)
for custom single and multiple objective optimization.

## USAGE
### Software Requirements
Make sure you have the following installed:
- JDK 1.8+
- Git
- Git bash
- Maven
- Gradle
- ItelliJ

### Notes
1. Although not required here specifically, it's a good advice for IntelliJ-Gradle users to make sure the "Use auto-import" checkbox is checked in the Gradle settings window (File > Setting > Build, Execution, Deployment > Gradle). This allows IntelliJ to automatically detect and add the necessary support to Gradle projects).

### Install
Since evolib-demo depends on evolib, and evolib depends on tx2ex, let's start by cloning the three libraries:
- > git clone https://github.com/000haitham000/tx2ex.git
- > git clone https://github.com/000haitham000/evolib.git
- > git clone https://github.com/000haitham000/evolib-demo.git

Once your have the three projects on your file system add tx2ex and evolib to your local maven repository respectively. This can be done using the following command once for each of the two projects (while in the root directory of the respective project):
> gradle install

It might be useful to refresh your dependency if any dependency-related problems arises. Keep the following command in mind (note: performing the aforementioned steps in the proper order should not result in dependency-related problems):
> gradle --refresh-dependencies

### Run
Now the code is ready. You can directly run the continuous optimization example by executing optimization.SampleScript in evolib. For the custom optimization example, execute tsp.solver.TSPSolver in evolib-demo.

### Develop Your Own Project
Consult the build scripts (build.gradle) of both evolib and evolib-demo to learn how to add tx2ex and evolib repectively as dependencies to your project.


## BUILT USING
Java SE Development Kit (JDK) 8 or later
(http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## VERSIONING
We use [SemVer](http://semver.org/) for versioning.

## AUTHORS
Haitham Seada (http://haithamseada.com/)

## LICENSE
This project is licensed under the Apache License Version 2.0
(http://www.apache.org/licenses/LICENSE-2.0). A simple explanation of the
license in layman's terms can be found at
(http://www.apache.org/foundation/license-faq.html#WhatDoesItMEAN).