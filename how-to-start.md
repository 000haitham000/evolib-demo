# Start Using EvoLib

## Software Requirements

Make sure you have the following installed:
- JDK 1.8+
- Git
- Git bash
- Maven
- Gradle
- ItelliJ

## Notes

1. If you are using Ford internal network (durectky or through VPN) make sure that your Git proxy is setup correctly. If not type the following command in your Git bash:
git config --global http.proxy http://proxyvipecc.nb.ford.com:83
If you are not behind a proxy (e.g. working from home without using VPN) you need to reset your Git proxy using the following command:
git config --global --unset http.proxy

2. Although not required here specifically, it's a good advice for IntelliJ-Gradle users to make sure the "Use auto-import" checkbox is checked in the Gradle settings window (File > Setting > Build, Execution, Deployment > Gradle). This allows IntelliJ to automatically detect and add the necessary support to Gradle projects).

## Install

Since evolib-demo depends on evolib, and evolib depends on tx2ex, let's start by cloning the three libraries:

> git clone https://github.com/000haitham000/tx2ex.git
> git clone https://github.com/000haitham000/evolib.git
> git clone https://github.com/000haitham000/evolib-demo.git

Once your have the three projects on your file system add tx2ex and evolib to your local maven repository respectively. This can be done using the following command once for each of the two projects (while in the root directory of the respective project):

> gradle install

It might be useful to refresh your dependency if any dependency-related problems arises. Keep the following command in mind (note: performing the aforementioned steps in the proper order should not result in dependency-related problems):

> gradle --refresh-dependencies

## Run

Now the code is ready. You can directly run the continuous optimization example by executing optimization.SampleScript in evolib. For the custom optimization example, execute tsp.solver.TSPSolver in evolib-demo.

## Develop Your Own Project

Consult the build scripts (build.gradle) of both evolib and evolib-demo to learn how to add tx2ex and evolib repectively as dependencies to your project.
