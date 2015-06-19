# [cfg4j](http://cfg4j.org) sample app (uses **[Consul](http://consul.io)** as configuration store)
App demonstrating how to access configuration stored in a Consul service using cfg4j library. Uses configuration object binding.

## Usage
```
> cd cfg4j-sample-apps/
> ./gradlew build
> java -jar consul-bind/build/libs/consul-bind-1.0.0-SNAPSHOT.jar
```

## Code snippets

### Configuration beans - see ConfigBeans.java
```java
@Bean
public ConfigurationProvider configurationProvider() {
  // Use Consul service as configuration store
  ConfigurationSource source = new ConsulConfigurationSource();
  
  // Reload configuration every 5 seconds
  RefreshStrategy refreshStrategy = new PeriodicalRefreshStrategy(5, TimeUnit.SECONDS);

  // Create provider
  return new ConfigurationProviderBuilder()
      .withConfigurationSource(source)
      .withRefreshStrategy(refreshStrategy)
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