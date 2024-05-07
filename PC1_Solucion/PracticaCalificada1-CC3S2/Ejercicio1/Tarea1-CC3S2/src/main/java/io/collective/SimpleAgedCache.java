
package io.collective;

import java.time.Clock;
import java.time.Instant;

public class SimpleAgedCache {
    private String keys[] = new String[10];
    private String values[] = new String[10];
    private Instant instantFin[] = new Instant[10];
    private int numeroElementos = 0;

    private TestClock testClock = new TestClock();

    public SimpleAgedCache(){
    }
    public SimpleAgedCache(TestClock clock) {
        this.testClock = clock;
    }
    public boolean isEmpty() {
        actualizacion();
        return numeroElementos==0;
    }
    public void put(String anotherKey, String anotherValue, int i) {
        actualizacion();
        keys[numeroElementos] = anotherKey;
        values[numeroElementos] = anotherValue;
        instantFin[numeroElementos] = testClock.instant().plusMillis(i);
        numeroElementos++;
    }

    public int size() {
        actualizacion();
        return numeroElementos;
    }
    public String get(String aKey) {
        actualizacion();
        for(int i=0;i<10;i++){
            if(aKey.equals(keys[i])){
                return values[i];
            }
        }
        return null;
    }

    private void actualizacion(){
        for(int i=0;i<10;i++){
            if(PairExpired(i)){
                dropElement(i);
            }
        }
    }
    private boolean PairExpired(int ind){
        return instantFin[ind]!=null &&
                instantFin[ind].isBefore(testClock.instant());
    }
    private void dropElement(int ind){
        keys[ind] = null;
        values[ind] = null;
        instantFin[ind] = null;
        numeroElementos--;
    }
}
