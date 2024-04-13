package com.arun.study.java_ci_cd_spring_boot.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    
    private final String SITE_IS_UP = "Site is Up!";
    private final String SITE_IS_DOWN = "Site is down!" ;
    private final String INCORRECT_URL = "Incorrect URL";

    @GetMapping("/check")
    public String isUrlValid(@RequestParam String url){
        
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCodeCategory = urlConnection.getResponseCode() / 100;

            if(responseCodeCategory != 2 || responseCodeCategory != 3)
            {
                returnMessage = SITE_IS_DOWN;
            } else 
            {
                returnMessage = SITE_IS_UP;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }

}
