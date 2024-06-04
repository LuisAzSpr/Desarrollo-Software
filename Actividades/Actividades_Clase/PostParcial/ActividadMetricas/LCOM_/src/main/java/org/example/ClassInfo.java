package org.example;

import java.util.*;

/*
    Se agrego el metodo calcular LCOM que en el codigo base estaba
    definido en la clase Main.

 */

public class ClassInfo {
    private MetricStrategy metric;
    private List<String> methods = new ArrayList<>();
    private Map<String,Set<String>> methodAttributes = new HashMap<>();
    private Set<String> attributes = new HashSet<>();
    public void addMethod(String methodName,Set<String> atts){
        if(methodName.isEmpty()){
            throw new IllegalArgumentException("The name of a method cannot be empty");
        }
        methods.add(methodName);
        methodAttributes.put(methodName,atts);
        attributes.addAll(atts);
    }
    public int calculateMetric(){
        return metric.calculateMetric(methods,methodAttributes);
    }
    public void setMetric(MetricStrategy ms) {
        metric = ms;
    }
}
