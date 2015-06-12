# cfg4 sample app (configuration source: git)
App demonstrating how to access configuration stored in a Git repository using cfg4j library. Uses configuration object binding.

## Usage
```
> cd cfg4j-sample-apps/
> ./gradlew build
> java -jar git-bind/build/libs/git-bind-1.0.0-SNAPSHOT.jar
```

To use your own git repository add **-DconfigRepoPath=...** parameter. You can use both local path and web URL.

```
> java -DconfigRepoPath=/tmp/myRepo.git -jar git-bind/build/libs/git-bind-1.0.0-SNAPSHOT.jar
```