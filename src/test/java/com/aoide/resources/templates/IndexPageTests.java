package com.aoide.resources.templates;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT )
public class IndexPageTests
{
    @LocalServerPort
	private int port;

    static WebDriver driver;
    
    @BeforeAll
    static void setUp()
    {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments( "--headless" );

        driver = new ChromeDriver( chromeOptions );
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
    }

    @AfterAll
    static void tearDown()
    {
        if ( driver != null )
            driver.quit();
    }

    @Test
    void indexPageExist()
    {
        driver.get( "http://localhost:" + port );
        String title = driver.getTitle();
        
        assertTrue( title.contains( "Aoide" ) );
    }
}
