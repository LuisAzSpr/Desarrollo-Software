package org.example.analysiscodeutil.systemMetric;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Ca implements SystemMetric{

    @Override
    public int calculateMetric(List<String> allClasses, String class1) throws FileNotFoundException {
        return 0;
    }

    @Override
    public int calculateMetric(List<String> allClasses, String class1, String path) throws FileNotFoundException {
        List<CompilationUnit> cus = new ArrayList<>();
        List<ClassOrInterfaceDeclaration> classes = new ArrayList<>();
        for(String className:allClasses){
            FileInputStream in = new FileInputStream(path+className+".java");
            CompilationUnit cu = StaticJavaParser.parse(in);
            classes.add(cu.getClassByName(className)
                    .orElseThrow(() -> new RuntimeException("Class not found")));
        }

        for(ClassOrInterfaceDeclaration c:classes){

        }
    }
}
