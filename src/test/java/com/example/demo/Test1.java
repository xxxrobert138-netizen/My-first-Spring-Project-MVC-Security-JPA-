package com.example.demo;

import org.springframework.web.client.RestTemplate;

public class Test1 {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String targetEndpoint = "https://accounts.google.com/v3/signin/";

        System.out.println(restTemplate.getForObject(targetEndpoint, String.class));
    }

}
