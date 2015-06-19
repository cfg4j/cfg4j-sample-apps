/*
 * Copyright 2015 Norbert Potocki (norbert.potocki@nort.pl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cfg4j.sample;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.git.ConfigFilesProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Arrays;

@Configuration
public class ConfigBeans {

  @Bean
  public ConfigurationProvider configurationProvider() {
    // Specify which files to load. Configuration from both files will be merged.
    ConfigFilesProvider configFilesProvider = () -> Arrays.asList(new File("application.properties"), new File("otherConfig.properties"));

    // Use classpath repository as configuration store
    ConfigurationSource source = new ClasspathConfigurationSource(configFilesProvider);

    // Create provider
    return new ConfigurationProviderBuilder()
        .withConfigurationSource(source)
        .build();
  }
}
