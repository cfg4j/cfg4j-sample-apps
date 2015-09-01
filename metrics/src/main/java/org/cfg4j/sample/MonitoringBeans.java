package org.cfg4j.sample;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

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
