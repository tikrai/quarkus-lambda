package com.amazon.example.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringService {

    public String respond() {
        return "Test response";
    }
}
