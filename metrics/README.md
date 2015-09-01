# [cfg4j](http://cfg4j.org) sample app (uses **local files** as configuration store)
App demonstrating how to access configuration stored in a local files using cfg4j library. **Uses multiple configuration files.**

## Usage
```
> cd cfg4j-sample-apps/
> ./gradlew build
> java -jar files-bind/build/libs/files-bind-1.0.0-SNAPSHOT.jar
```

You can:
* use any path to config files: **-DconfigFilesPath=\<configFilesPath\>** parameter. Both absolute and relative paths are supported.

```
> java -DconfigFilesPath=./files-bind/build/libs/ -jar files-bind/build/libs/files-bind-1.0.0-SNAPSHOT.jar
```


## Code snippets

### Configuration beans - see ConfigBeans.java
```java
@Value("${configFilesPath:./files-bind/build/libs/}")
private String filesPath; // Run with -DconfigFilesPath=<configFilesPath> parameter to override

@Bean
public ConfigurationProvider configurationProvider() {
  // Specify which files to load. Configuration from both files will be merged.
  ConfigFilesProvider configFilesProvider = () -> Arrays.asList(new File("application.yaml"), new File("otherConfig.properties"));

  // Use local files as configuration store
  ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);

  // Use relative paths
  Environment environment = new ImmutableEnvironment(filesPath);

  // Reload configuration every 5 seconds
  RefreshStrategy refreshStrategy = new PeriodicalRefreshStrategy(5, TimeUnit.SECONDS);

  // Create provider
  return new ConfigurationProviderBuilder()
      .withConfigurationSource(source)
      .withRefreshStrategy(refreshStrategy)
      .withEnvironment(environment)
      .build();
}
```

### Obtain configuration - see MainController.java
```java
// Define configuration interface 
public interface ReksioConfig {
  URL homepage();
  ...
}

ReksioConfig reksioConfig = configurationProvider.bind("reksio", ReksioConfig.class);
URL url = reksioConfig.homepage();
```