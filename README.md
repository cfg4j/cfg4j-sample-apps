## Sample applications using [cfg4j](http://cfg4j.org) library

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

## Apps
* [git-bind](git-bind/) - reads configuration from a given git repository (using object binding) and outputs values
   to the stdout. Reloads configuration every 3 seconds. 
* [git-simple](git-simple/) - reads configuration from a given git repository (using *getProperty* methods) and outputs values
  to the stdout. Reloads configuration every 3 seconds.