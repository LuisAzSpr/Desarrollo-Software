package org.example.analysiscodeutil;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.*;

public class MethodAttributesExtractor {

    private final CompilationUnit cu;
    private final ClassOrInterfaceDeclaration classDeclaration;

    public MethodAttributesExtractor(CompilationUnit cu,String className){
        this.cu = cu;
        this.classDeclaration = cu.getClassByName(className)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }

    public Set<String> extractAttributesOfMethod(String method){

        // extraeremos el metodo de la clase actual
        MethodDeclaration methodDeclaration = classDeclaration.getMethods().stream()
                                                    .filter(m->m.getNameAsString().equals(method))
                                                    .findFirst()
                                                    .orElseThrow(()->new RuntimeException("Method not found"));
        // tomamos el cuerpo de este metodo
        String body = methodDeclaration.getBody().toString();

        // Inicializamos los atributos de esa clase
        Set<String> classAttributes = extractAllAtributes();
        // Inicializamos los atributos del metodo de interes de esa clase
        Set<String> attributesOfMethod = new HashSet<>();

        // Para cada atributo de la clase que este contenido en el cuerpo del metodo lo agreagamos a attributesOfMethod
        for(String attr:classAttributes){
            if(body.contains(attr)){
                attributesOfMethod.add(attr);
            }
        }
        return attributesOfMethod;
    }

    public Map<String,Set<String>> extractAllAtributesOfEachMethod(){
        Map<String,Set<String>> attrsOfMethods = new HashMap<>();
        Set<String> nameOfMethods = extractAllMethods();
        for(String name:nameOfMethods){
            attrsOfMethods.put(name,extractAttributesOfMethod(name));
        }
        return attrsOfMethods;
    }

    public Set<String> extractAllAtributes(){
        Set<String> classAttributes = new HashSet<>();
        for(FieldDeclaration fd:classDeclaration.getFields()){
            fd.getVariables().forEach(variable->{classAttributes.add(variable.getNameAsString());});
        }
        return classAttributes;
    }

    public Set<String> extractAllMethods(){
        Set<String> classMethods = new HashSet<>();
        for(MethodDeclaration md:classDeclaration.getMethods()){
            classMethods.add(md.getNameAsString());
        }
        return classMethods;
    }

}
