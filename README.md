# Sample **cfg4j** apps

## Usage
* Build all apps

```
> ./gradlew build
```

* Run app (in this example: "git-bind") - read README in the app root directory for more information.

```
java -jar git-bind/build/libs/git-bind-1.0.0-SNAPSHOT.jar
```

## Apps
* [git-bind](git-bind/) - reads configuration from a given git repository (using object binding) and outputs values
   to the stdout. Reloads configuration every 3 seconds. 
* [git-simple](git-simple/) - reads configuration from a given git repository (using *getProperty* methods) and outputs values
  to the stdout. Reloads configuration every 3 seconds.