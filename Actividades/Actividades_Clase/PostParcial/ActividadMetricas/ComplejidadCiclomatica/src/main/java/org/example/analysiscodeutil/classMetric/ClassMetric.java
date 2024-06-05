package org.example.analysiscodeutil.classMetric;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ClassMetric{
    public int calculateMetric(List<String> modules, Map<String, Set<String>> modulesRelation);

}
