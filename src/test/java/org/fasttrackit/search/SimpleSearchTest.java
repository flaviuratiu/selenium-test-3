package org.fasttrackit.search;

import org.fasttrackit.AppConfig;
import org.fasttrackit.webviews.Header;
import org.fasttrackit.webviews.ProductsGrid;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SimpleSearchTest {

    @Test
    public void simpleSearchWithOneKeyword() {
        System.setProperty("webdriver.chrome.driver",
                AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();

        driver.get(AppConfig.getSiteUrl());

        String keyword = "vase";

        Header header = PageFactory.initElements(driver, Header.class);

        header.search(keyword);

        ProductsGrid productsGrid = PageFactory.initElements(driver, ProductsGrid.class);
        System.out.println("Stored " + productsGrid.getProductNames().size() + " product names from search results.");

        for (WebElement productName : productsGrid.getProductNames()) {
            assertThat("Some of the product names do not contain the searched keyword.",
                    productName.getText(), containsString(keyword.toUpperCase())
            );
        }
    }

    @Test
    public void specialPriceDisplayedAfterSimpleSearch() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\webdrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://fasttrackit.org/selenium-test/");

        driver.findElement(By.id("search")).sendKeys("vase" + Keys.ENTER);

        String oldPrice = driver.findElement(By.xpath("//p[@class='old-price']//span[@class='price']")).getText();
        String specialPrice = driver.findElement(By.xpath("//p[@class='special-price']//span[@class='price']")).getText();

        String oldPriceValue = oldPrice.split(" ")[0]
                .replace(",", ".");

        String specialPriceValue = specialPrice.split(" ")[0]
                .replace(",", ".");

//        assertThat("Old price greater than special price",
//                Double.parseDouble(oldPriceValue),
//                is(lowerThan(Double.parseDouble(specialPriceValue)))
//                );

    }

}
