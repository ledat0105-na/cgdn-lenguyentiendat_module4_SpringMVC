package com.example.library.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class VisitorCounter {
    private final AtomicLong counter = new AtomicLong();
    public long increaseAndGet() { return counter.incrementAndGet(); }
    public long get() { return counter.get(); }
}
