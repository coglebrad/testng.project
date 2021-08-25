package com.example.stepdefs;
import com.example.base.BaseClass;
import com.example.models.Post;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GoogleStepDefs extends BaseClass {
    //Will need to share information here about the request and response object
    // SendRequest(u,p,c) <-> ReceiveResponse
    // Three steps for api's Setup.Request.Validate response
    private HttpResponse<String> response;

    @Before
    public void startUp(){
        WEB.OpenBrowser();
        WEB.WaitUntilPageFullyLoaded();
        WEB.MaximiseBrowser();
    }

    @After
    public void endScenario() {
        WEB.CloseBrowser();
    }

    @Given("I open google")
    public void i_open_google() {

      Assert.assertEquals(WEB.FindElementByClassNameWhichContains("footer").isDisplayed(),true);

        //FindElementByIdWhichContains("test");
    }


    @Given("I send a request")
    public void iSendARequest()   {

              //posts = GetPosts().stream().filter(x -> x.id == 40).findFirst().get();
        Map<String, List<String>> headers = new HashMap<String,List<String>>();
        headers.put("headers", List.of("Content-type","application/json; charset=UTF-8"));
        headers.put("headers", List.of("Accept","*/*"));

        response = HTTP().GET("https://jsonplaceholder.typicode.com/posts",headers);


    }

    @Then("response status code is 200 Ok")
    public void responseStatusCodeIs200Ok(){
        //System.out.println("post:" + posts.body);
    }
}
