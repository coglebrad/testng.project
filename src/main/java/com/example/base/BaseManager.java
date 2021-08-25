package com.example.base;
import java.net.http.HttpClient;
import org.openqa.selenium.WebDriver;

class BaseManager {
    private static ThreadLocal<WebDriver> _webDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<HttpClient> _httpClient = new ThreadLocal<HttpClient>();

    public static WebDriver getWebDriver() {
        return _webDriver.get();
    }

    public static HttpClient getHttp(){
        return _httpClient.get();
    }

    static void setWebDriver(WebDriver driver) {
        _webDriver.set(driver);
    }

    static void setHttp(HttpClient httpClient){
        _httpClient.set(httpClient);
    }
}
