package com.example.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.base.BaseHttp;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.models.*;
import org.openqa.selenium.WebElement;


public class BaseClass {

    private BaseHttp http = null;
    private BaseWeb web = null;

    public BaseHttp HTTP() {
        if (http == null){
            http =  new BaseHttp();
        }
        return http;
    }
    public BaseWeb WEB = web == null ? web = new BaseWeb() : web;

    private Gson gson = new Gson();

    public List<Post> GetPosts()
    {
        Map<String, List<String>> headers = new HashMap<String,List<String>>();
        headers.put("headers", List.of("Content-type","application/json; charset=UTF-8"));
        headers.put("headers", List.of("Accept","*/*"));

        HttpResponse<String> response = http.GET("https://jsonplaceholder.typicode.com/posts",headers);

        Type type = new TypeToken<List<Post>>(){}.getType();
       return gson.fromJson(response.body(),type);


    }

    public HttpResponse<String> AddPost(Post post)
    {
        Map<String, List<String>> headers = new HashMap<String,List<String>>();
        headers.put("headers", List.of("Content-type","application/json; charset=UTF-8"));
        headers.put("headers", List.of("Accept","*/*"));

       return http.POST("https://jsonplaceholder.typicode.com/posts","{\"title\":\"foo\",\"body\":\"boo\",\"userId\":3}",headers);


    }

}
