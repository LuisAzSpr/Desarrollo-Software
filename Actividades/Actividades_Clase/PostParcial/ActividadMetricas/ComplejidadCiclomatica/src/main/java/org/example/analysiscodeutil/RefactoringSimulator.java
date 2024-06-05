package org.example.analysiscodeutil;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.StaticJavaParser;

import java.io.FileInputStream;
import java.util.*;



public class RefactoringSimulator {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("src/main/java/org/example/Calculator.java");
        CompilationUnit cu = StaticJavaParser.parse(in);

    }

    static Map<String,Integer> metrics(CompilationUnit cu){
        return null;
    }

}