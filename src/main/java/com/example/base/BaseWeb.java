package com.example.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseWeb {
    private WebDriver driver;
    private JavascriptExecutor jsdriver;
    public void OpenBrowser(){

        driver = BaseFactory.createDriverInstance();
        BaseManager.setWebDriver(driver);
        driver.navigate().to(System.getProperty("BASE_URL"));
        jsdriver = (JavascriptExecutor)driver;

    }
    public void CloseBrowser(){
        driver.close();
        driver.quit();
    }
    public void MaximiseBrowser(){
        driver.manage().window().maximize();
    }

    public WebElement FindElementByIdWhichContains(String text){
        //return  jsdriver.executeScript("document.getElementById([id*="+text+"])") as WebElement;
        return driver.findElement(By.cssSelector("[id*="+text+"]"));
    }
    public WebElement FindElementByIdWhichStartsWith(String text){
        return driver.findElement(By.cssSelector("[id^="+text+"]"));
    }
    public WebElement FindElementByClassNameWhichContains(String text){
        //return (WebElement) ((JavascriptExecutor)driver).executeScript("return document.getSelection(\"[id*="+text+"]\");");
        return driver.findElement(By.cssSelector("[id*="+text+"]"));
    }
    public WebElement FindElementByIdWhichEndsWith(String text){
        return driver.findElement(By.cssSelector("[id$="+text+"]"));
    }
    public WebElement FindActiveTadioButtonsIn(WebElement parent){
        return parent.findElement(By.cssSelector("radio:active"));
    }
    public WebElement FindRadioButtonsContaining(String text){
        return driver.findElement(By.cssSelector("[]"));
    }

    public void takeFullScreenshot(String fileName) throws Exception {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String tempDir = System.getProperty("java.io.tmpdir");
        File destFile = new File(Paths.get(tempDir, fileName + ".png").toString());
        //FileUtils.getFileUtils().copyFile(srcFile, destFile);
    }

    public void WaitUntilPageFullyLoaded() {

        Boolean result = new WebDriverWait(driver,60).until(x ->
        {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            String isReady = (String) javascriptExecutor.executeScript("return document.readyState");
            return isReady.equals("complete");
        });
    }

    public void SelectOptionIn(WebElement parent,String text){
        Select dropdown = new Select(parent);
        dropdown.selectByVisibleText(text);
    }

    public WebElement WaitUntilElementIsClickable(WebElement element){
         return new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void takeScreenshotOfElement(WebElement element, String fileName) throws Exception {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshotFile);
        Point point = element.getLocation();
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);
        ImageIO.write(eleScreenshot, "png", screenshotFile);
        String tempDir = System.getProperty("java.io.tmpdir");
        File destFile = new File(Paths.get(tempDir, fileName + ".png").toString());
        //FileUtils.getFileUtils().copyFile(screenshotFile, destFile);
    }
}
