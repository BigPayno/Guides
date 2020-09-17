package jdkguide.time;

import java.time.temporal.ChronoUnit;

public class TimeComparator {

    long timestamp;

    public static TimeComparator of(long timestamp){
        TimeComparator comparator = new TimeComparator();
        comparator.timestamp = timestamp;
        return comparator;
    }

    public TimeComparator plus(long num, ChronoUnit timeUnit){
        this.timestamp = this.timestamp +
                timeUnit.getDuration().toMillis()*num;
        return this;
    }

    public TimeComparator minis(long num, ChronoUnit timeUnit){
        this.timestamp = this.timestamp -
                timeUnit.getDuration().toMillis()*num;
        return this;
    }

    public boolean isAfter(long timestamp){
        return this.timestamp>timestamp;
    }

    public boolean isBefore(long timestamp){
        return this.timestamp<timestamp;
    }
}
