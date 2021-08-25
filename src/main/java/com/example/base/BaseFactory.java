package com.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class BaseFactory {
    static WebDriver createDriverInstance(){
        return new ChromeDriver();
    }

}
