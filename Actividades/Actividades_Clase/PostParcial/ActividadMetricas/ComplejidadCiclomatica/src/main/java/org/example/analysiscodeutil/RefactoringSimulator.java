package org.example;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import javax.naming.NameParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;



public class RefactoringSimulator {

    private Map<String,Set<String>> methodsAttributes = new HashMap<>();

    public void Inicializar(String pathClass) throws FileNotFoundException {
        // leemos el archivo java
        FileInputStream in = new FileInputStream(pathClass);

        // incializamos el siguiente objeto que nos ayudara a analizar las clases
        CompilationUnit cu = StaticJavaParser.parse(in);

        // Escogemos la clase (el nombre de la clase de ese archivo) que queremos analizar
        ClassOrInterfaceDeclaration classDeclaration = cu.getClassByName("Calculator")
                .orElseThrow(() -> new RuntimeException("Class not found"));

        // Inicializamos los atributos de esa clase
        Set<String> classAttributes = new HashSet<>();
        // Almacenamos todos los metodos de esa clase en methodDeclarations
        List<MethodDeclaration> methodDeclarations = classDeclaration.getMethods();

        // Para cada atributo extraemos su nombre
        for(FieldDeclaration fd:classDeclaration.getFields()){
            fd.getVariables().forEach(variable->{classAttributes.add(variable.getNameAsString());});
        }

        // Ahora para cada metodo veremos si existe un atributo de la clase
        for(MethodDeclaration md: methodDeclarations){
            // Inicilizamos los atributos de la clase que contiene un metodo
            Set<String> attributes = new HashSet<>();
            // Extraemos el cuerpo del metodo
            String body = md.getBody().toString();
            // verificamos si existe un attributo de la clase en ese metodo
            for(String attr:classAttributes){
                if(body.contains(attr)){
                    attributes.add(attr);
                }
            }
            // agregamos el nombre y los atributos correspondientes a ese motodo
            methodsAttributes.put(md.getNameAsString(),attributes);
        }
    }

    static List<RefactoringSuggestion> analyzeCode(CompilationUnit cu) {
        // Implement analysis logic here
        return new ArrayList<>();
    }
    static List<RefactoringImpact> simulateRefactorings(List<RefactoringSuggestion> suggestions,
                                                        CompilationUnit cu) {
        // Simulate refactorings and calculate potential impact on metrics
        return new ArrayList<>();
    }
    static void reportResults(List<RefactoringImpact> impacts) {
        // Output the simulation results
        impacts.forEach(impact -> System.out.println(impact));
    }
}
class RefactoringSuggestion {
    // Details about suggested refactorings
}
class RefactoringImpact {
    // Details about the impact of a refactoring
}