## Sample applications using [cfg4j](http://cfg4j.org) library

## Apps
* [classpath-bind](classpath-bind/) - reads configuration from a **classpath files** and outputs values
  to the stdout.
* [consul-bind](consul-bind/) - reads configuration from a local **[Consul](http://consul.io) agent** and outputs values
  to the stdout. Automatically reloads configuration after change.
* [files-bind](files-bind/) - reads configuration from a local files and outputs values
  to the stdout. Automatically **reloads configuration after change**.
* [git-simple](git-simple/) - reads configuration from a given **git repository** (using *getProperty* methods) and outputs values
  to the stdout. Automatically reloads configuration after change.
* [git-bind](git-bind/) - reads configuration from a given git repository (using **object binding**) and outputs values
   to the stdout. Automatically reloads configuration after change.
* [git-multi-file](git-multi-file/) - reads configuration from a given git repository (from **multiple files**) and outputs values
  to the stdout. Automatically reloads configuration after change.
* [metrics](metrics/) - reads configuration from a a local file and outputs values
  to the stdout. Automatically reloads configuration after change. **Exposes metrics**.

## Usage
* Build all apps

```
> cd cfg4j-sample-apps/
> ./gradlew build
```

* Run app (in this example: "git-bind") - **read README.md file in the corresponding app root directory** for more information.

```
> java -jar git-bind/build/libs/git-bind-1.0.0-SNAPSHOT.jar
```