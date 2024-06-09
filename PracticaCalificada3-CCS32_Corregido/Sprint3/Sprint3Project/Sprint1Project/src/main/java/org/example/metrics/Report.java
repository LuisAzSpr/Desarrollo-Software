package org.example.metrics;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Report {

    // Este atributo se usara para extraer los atributos asociados a cada metodo
    MethodAttributesExtractor mthdAttrExtr;

    // Este atributo se usara para mapear las metricas con sus respectivos valores
    Map<String,Integer> scoreMetrics;

    public Report(String path,String className) throws FileNotFoundException {
        String direccion = path+"/"+className+".java"; // direccion del archivo
        FileInputStream in = new FileInputStream(direccion);
        CompilationUnit cu = StaticJavaParser.parse(in);
        mthdAttrExtr = new MethodAttributesExtractor(cu,className); // inicializamos el extractor
        scoreMetrics = new HashMap<>(); // inicializamos el scoreMetrics
    }


    public void doReport(List<String> methods){
        // Extraemos todos los metodos de la clase
        //List<String> methods = mthdAttrExtr.extractAllMethods().stream().toList();
        // Extraemos todos los metodos con los respectivos atributos de los cuales hacen uso
        Map<String, Set<String>> methodAttributes = mthdAttrExtr.extractAllAtributesOfEachMethod();
        // Inicializamos las metricas que queremos
        Map<String,ClassMetric> metrics = new HashMap<>();
        addMetrics(metrics);
        // para cada una de las metricas
        for(String metric:metrics.keySet()){
            ClassMetric classMetric = metrics.get(metric);
            int valor = classMetric.calculateMetric(methods,methodAttributes);
            scoreMetrics.put(metric,valor);
        }
    }

    // Mostramos el scoreMetrics que fue llenado en DoReport
    public void showReport(){
        for(String metric:scoreMetrics.keySet()){
            System.out.println(metric+" = "+scoreMetrics.get(metric));
        }
    }

    // Se inicializa las metricas que se requieren
    public void addMetrics(Map<String,ClassMetric> metrics){
        metrics.put("LCOM",new Lcom());
        metrics.put("LCOM4",new Lcom4());
    }
}
