package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MetricStrategy {
    public int calculateMetric(List<String> methods, Map<String, Set<String>> methodAttributes);
}
