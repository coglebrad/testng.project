package com.example.base;
import org.openqa.selenium.WebElement;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.*;


public class BaseHttp {

    private static HttpRequest.Builder Addheaders(HttpRequest.Builder builder, HashMap<String,String> headers){
        for (Map.Entry<String,String> e : headers.entrySet()){
            builder.header(e.getKey(), e.getValue());
        }

        return builder;
    }
    private static HttpRequest.Builder addHeaders(HttpRequest.Builder builder, Map<String, List<String>> headers) throws IllegalArgumentException{
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            for (String value : e.getValue()) {
                builder.header(e.getKey(), value);
            }
        }
        return builder;
    }
    public HttpResponse<String> POST(String uri, String stringJsonBody, Map<String, List<String>> headers){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response= null;
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpRequest myrequest = null;

        try {

            myrequest = addHeaders(builder,headers)
                    .uri(URI.create(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(stringJsonBody))
                    .timeout(Duration.ofMillis(60000))
                    .build();

            response = httpClient.send(myrequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception httpException){
            System.out.println(httpException.getStackTrace());

        }

        return response;
    }
    public HttpResponse<String> GET(String uri,Map<String, List<String>> headers){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response= null;
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpRequest myrequest = null;

        try {

            myrequest = addHeaders(builder,headers)
                    .uri(URI.create(uri))
                    .GET()
                    .timeout(Duration.ofMillis(60000))
                    .build();

            response = httpClient.send(myrequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception httpException){
            System.out.println(httpException.getStackTrace());

        }

        return response;
    }



}
