package org.openqa.selenium;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * Collects the data
 *
 * @author  Cormac
 * @since 4/28/2017.
 */
public class DataCollector {
    private static ChromeDriverService service;
    private WebDriver driver;

    public static void main(String[] args) {
        DataCollector.run();
    }


    /**
     */
    private static void run() {
        try {
            String inputContents = new String(Files.readAllBytes(Paths.get("SIS Logon.txt")));
            StringTokenizer tokenizer = new StringTokenizer(inputContents, ",");
            /* the goal of the loop is to read each word and adding it or counting it to the table */
            sis(tokenizer.toString(), tokenizer.nextToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sis(String username, String password){
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        System.out.println("1");
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe")).usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDriver(new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome()));
        driver.get("http://www.google.com");
        System.out.println("1");
        driver.get("https://sis.case.edu/"); // goto sis
        System.out.println("1");
        WebElement user = driver.findElement(By.name("userid")); //enter user
        System.out.println("1");
        WebElement pw = driver.findElement(By.name("pwd")); //enter pw
        System.out.println("1");
        user.sendKeys(username);
        pw.sendKeys(password);
        System.out.println("1");
        WebElement submit = driver.findElement(By.name("Submit")); //enter sign in
        submit.submit();
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle() + " @ " + driver.getCurrentUrl());
    }

    public static ChromeDriverService getService() {
        return service;
    }

    public static void setService(ChromeDriverService service) {
        DataCollector.service = service;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
