# [cfg4j](http://cfg4j.org) sample app (uses **classpath** as configuration store)
App demonstrating how to access configuration stored in a classpath files using cfg4j library. **Uses multiple configuration files.**

## Usage
```
> cd cfg4j-sample-apps/
> ./gradlew build
> java -jar classpath-bind/build/libs/classpath-bind-1.0.0-SNAPSHOT.jar
```

## Code snippets

### Configuration beans - see ConfigBeans.java
```java
// Specify which files to load. Configuration from both files will be merged.
ConfigFilesProvider configFilesProvider = () -> Arrays.asList(new File("application.properties"), new File("otherConfig.properties"));

// Use classpath repository as configuration store
ConfigurationSource source = new ClasspathConfigurationSource(configFilesProvider);

// Create provider
return new ConfigurationProviderBuilder()
    .withConfigurationSource(source)
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