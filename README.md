# Sample **cfg4j** apps

## Usage
* Build all apps

```
> ./gradlew build
```

* Run app (in this example: "git-simple") - read README in the app root directory for more information.

```
java -jar git-simple/build/libs/git-simple-1.0.0-SNAPSHOT.jar
```

## Apps
* [git-simple](git-simple/) - reads configuration from a given git repository (using *getProperty* methods) and outputs values
  to the stdout. Reloads configuration every 5 seconds. 