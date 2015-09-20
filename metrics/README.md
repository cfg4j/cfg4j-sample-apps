# [cfg4j](http://cfg4j.org) sample app demonstrating publishing metrics
App demonstrating how to publish metrics for configuration source and provider. **Uses single, local configuration file.**

## Usage
```
> cd cfg4j-sample-apps/
> ./gradlew build
> java -jar metrics/build/libs/metrics-1.0.0-SNAPSHOT.jar
```

You can:
* use any path to config files: **-DconfigFilesPath=\<configFilesPath\>** parameter. Both absolute and relative paths are supported.

```
> java -DconfigFilesPath=./metrics/build/libs/ -jar metrics/build/libs/metrics-1.0.0-SNAPSHOT.jar
```


## Code snippets

### Configuration beans - see ConfigBeans.java
```java
@Value("${configFilesPath:./metrics/build/libs/}")
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
  ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);

  // Create provider
  return new ConfigurationProviderBuilder()
      .withConfigurationSource(source)
      .withReloadStrategy(reloadStrategy)
      .withEnvironment(environment)
      .withMetrics(metricRegistry, "firstProvider.") // Enable metrics
      .build();
}
```

### Metric publisher beans - see MonitoringBeans.java
```java
@Configuration
public class MonitoringBeans extends MetricsConfigurerAdapter {

  @Override
  public void configureReporters(MetricRegistry metricRegistry) {
    JmxReporter.forRegistry(metricRegistry)
        .convertRatesTo(TimeUnit.SECONDS)
        .convertDurationsTo(TimeUnit.MILLISECONDS)
        .build()
        .start();
  }
}
```
