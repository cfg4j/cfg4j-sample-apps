# cfg4 sample app (configuration source: git)
App demonstrating how to access configuration stored in a Git repository using cfg4j library. Uses configuration object binding.

## Usage
```
> cd cfg4j-sample-apps/
> ./gradlew build
> java -jar git-bind/build/libs/git-bind-1.0.0-SNAPSHOT.jar
```

You can:
* use your own git repository: **-DconfigRepoPath=<repositoryUrl>** parameter. Both local and remote repos are supported.
* use non-default branch: **-DconfigBranch=<branchName>**

```
> java -DconfigRepoPath=/tmp/myRepo.git -DconfigBranch-master -jar git-bind/build/libs/git-bind-1.0.0-SNAPSHOT.jar
```

## Snippets

### Configuration beans (using Spring Boot) - **see ConfigBeans.java**
```java
@Value("${configRepoPath:https://github.com/cfg4j/cfg4j-git-sample-config.git}")
private String configRepoPath; // Run with -DconfigRepoPath=<repositoryUrl> parameter to override

@Value("${configBranch:https:production-env}")
private String branch; // Run with -DconfigBranch=<branchName> parameter to override

@Bean
public ConfigurationProvider configurationProvider() {
  // Use Git repository as configuration store
  ConfigurationSource source = new GitConfigurationSourceBuilder()
      .withRepositoryURI(configRepoPath)
      .build();

  // Select branch to use (use new DefaultEnvironment()) for master
  Environment environment = new ImmutableEnvironment(branch);

  // Reload configuration every 5 seconds
  RefreshStrategy refreshStrategy = new PeriodicalRefreshStrategy(5, TimeUnit.SECONDS);

  // Create provider
  return new ConfigurationProviderBuilder()
      .withConfigurationSource(source)
      .withEnvironment(environment)
      .withRefreshStrategy(refreshStrategy)
      .build();
}
```

### Obtain configuration - **see MainController.java**
```java
// Define configuration interface 
public interface ReksioConfig {
  URL homepage();
  ...
}

ReksioConfig reksioConfig = configurationProvider.bind("reksio", ReksioConfig.class);
URL url = reksioConfig.homepage();
```