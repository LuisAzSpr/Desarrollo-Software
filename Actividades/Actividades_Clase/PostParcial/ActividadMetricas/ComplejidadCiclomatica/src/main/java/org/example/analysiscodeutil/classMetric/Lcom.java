package org.example.analysiscodeutil.classMetric;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lcom implements ClassMetric{

    @Override
    public int calculateMetric(List<String> methods, Map<String, Set<String>> methodAttributes) {
        int p=0,q=0;
        for(int i=0;i<methods.size();i++){
            for(int j=i+1;j<methods.size();j++){
                String method1 = methods.get(i);
                String method2 = methods.get(j);

                Set<String> attrs1 = methodAttributes.get(method1);
                Set<String> attrs2 = methodAttributes.get(method2);

                Set<String> intersection = new HashSet<>(attrs1);
                intersection.retainAll(attrs2);

                if(intersection.isEmpty()){
                    p++;
                }
                else{
                    q++;
                }
            }
        }
        if(p-q<0){
            return 0;
        }
        return p-q;
    }
}
