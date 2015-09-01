package org.cfg4j.sample;

import java.net.URL;
import java.util.List;
import java.util.Map;

public interface ReksioConfig {

  Boolean awake();

  String bornIn();

  Integer birthYear();

  List<String> friends();

  URL homepage();

  Map<String, Character> grades();

}
