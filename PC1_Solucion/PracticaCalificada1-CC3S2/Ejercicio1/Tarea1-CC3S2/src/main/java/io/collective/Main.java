package io.collective;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[]args){
        TestClock testClock = new TestClock();
        System.out.println(testClock.getZone());
        Instant instant1 = testClock.instant();
        testClock.offset(Duration.ofMillis(30000));
        Instant instant2 = testClock.instant();
        if(instant1.isAfter(instant2)){

        }
    }
}
