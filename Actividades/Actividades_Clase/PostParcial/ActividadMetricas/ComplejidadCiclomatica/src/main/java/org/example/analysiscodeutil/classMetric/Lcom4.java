package org.example.analysiscodeutil.classMetric;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.*;

public class Lcom4 implements ClassMetric {


    @Override
    public int calculateMetric(List<String> methods, Map<String, Set<String>> methodAttributes) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String method : methods) {
            graph.put(method, new HashSet<>());
        }
        for (int i = 0; i < methods.size(); i++) {
            for (int j = i + 1; j < methods.size(); j++) {
                String method1 = methods.get(i);
                String method2 = methods.get(j);
                Set<String> attrs1 = methodAttributes.get(method1);
                Set<String> attrs2 = methodAttributes.get(method2);
                // Crear un arco si comparten atributos
                if (!Collections.disjoint(attrs1, attrs2)) {
                    graph.get(method1).add(method2);
                    graph.get(method2).add(method1);
                }
            }
        }
        return countComponents(graph,methods);
    }

    private int countComponents(Map<String, Set<String>> graph,List<String> methods) {
        Set<String> visited = new HashSet<>();
        int components = 0;
        for (String method : methods) {
            if (!visited.contains(method)) {
                dfs(graph, method, visited);
                components++;
            }
        }
        return components;
    }

    private void dfs(Map<String, Set<String>> graph, String method, Set<String> visited) {
        Stack<String> stack = new Stack<>();
        stack.push(method);
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (String neighbor : graph.get(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

}
