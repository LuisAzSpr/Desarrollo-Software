package io.collective;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class TestClock extends Clock {
    Duration offset = Duration.ZERO;

    @Override
    public ZoneId getZone() {
        return Clock.systemDefaultZone().getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.offset(Clock.system(zone), offset);
    }

    @Override
    public Instant instant() {
        return Clock.offset(Clock.systemDefaultZone(), offset).instant();
    }

    public void offset(Duration offset) {
        this.offset = offset;
    }
}

