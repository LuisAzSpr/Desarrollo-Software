package org.example.analysiscodeutil.systemMetric;

import com.github.javaparser.ast.CompilationUnit;

import java.io.FileNotFoundException;
import java.util.List;

public interface SystemMetric {
    public int calculateMetric(List<String> allClasses,String class1) throws FileNotFoundException;

}
