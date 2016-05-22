# [cfg4j](http://cfg4j.org) sample app (uses **[Consul](http://consul.io)** as configuration store)
App demonstrating how to access configuration stored in a Consul service using cfg4j library. Uses configuration object binding.

## Usage
1. Make sure local consul agent is running and listening on **localhost:8500**. You can run a single-node consul instance by invoking:
```
> consul agent -data-dir=/tmp/consul -server -bootstrap
```

2. Put sample config values to Consul's key-value store
```
> curl -X PUT localhost:8500/v1/kv/reksio.awake -d true
> curl -X PUT localhost:8500/v1/kv/reksio.bornIn -d Poland
> curl -X PUT localhost:8500/v1/kv/reksio.birthYear -d 1967
> curl -X PUT localhost:8500/v1/kv/reksio.friends -d Bolek,Lolek
> curl -X PUT localhost:8500/v1/kv/reksio.homepage -d https://en.wikipedia.org/wiki/Reksio
> curl -X PUT localhost:8500/v1/kv/reksio.grades -d Math=A,PE=A,Polish=A
```

3. Run sample app
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
  ConfigurationSource source = new ConsulConfigurationSourceBuilder().build();
  
  // Reload configuration every 5 seconds
  ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);

  // Create provider
  return new ConfigurationProviderBuilder()
      .withConfigurationSource(source)
      .withReloadStrategy(reloadStrategy)
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
