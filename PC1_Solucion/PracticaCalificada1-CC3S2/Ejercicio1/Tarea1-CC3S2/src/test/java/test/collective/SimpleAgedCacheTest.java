package test.collective;

import io.collective.SimpleAgedCache;
import io.collective.TestClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleAgedCacheTest {
    SimpleAgedCache empty = new SimpleAgedCache();
    SimpleAgedCache nonempty = new SimpleAgedCache();

    @BeforeEach
    public void before() {
        nonempty.put("aKey", "aValue", 2000);
        nonempty.put("anotherKey", "anotherValue", 4000);
        System.out.println("-----------------------------------------------------");
    }

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(nonempty.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(2, nonempty.size());
    }


    @Test
    public void get() {
        assertNull(empty.get("aKey"));
        assertEquals("aValue", nonempty.get("aKey"));
        assertEquals("anotherValue", nonempty.get("anotherKey"));
    }

    @Test
    public void getExpired() {
        TestClock clock = new TestClock();

        SimpleAgedCache expired = new SimpleAgedCache(clock);
        expired.put("aKey", "aValue", 2000);
        expired.put("anotherKey", "anotherValue", 4000);

        clock.offset(Duration.ofMillis(3000));

        assertEquals(1, expired.size());
        assertEquals("anotherValue", expired.get("anotherKey"));
    }

    
}
