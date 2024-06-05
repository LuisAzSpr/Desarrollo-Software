package org.example.analysiscodeutil.systemMetric;

import com.github.javaparser.ast.CompilationUnit;

import java.io.FileNotFoundException;
import java.util.List;

public interface SystemMetric {
    int calculateMetric(List<String> allClasses,String class1) throws FileNotFoundException;

    int calculateMetric(List<String> allClasses, String class1, String path) throws FileNotFoundException;
}
