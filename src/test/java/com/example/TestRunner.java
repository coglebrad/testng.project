package com.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import com.example.utils.*;

@CucumberOptions(
        plugin ={"pretty","html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/cucumber.json"},
        features = {"src/test/resources/features"},
        glue={"com.example.stepdefs"},
        tags = "@test"
)

public class TestRunner extends AbstractTestNGCucumberTests {
    Configuration configuration = new Configuration();

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        loadConfiguration();
        return super.scenarios();
    }

    private void loadConfiguration(){
        //pass in variables mvn test -Denv="test" to load the test.propeties contents.
        configuration.loadProperties();
    }
}
