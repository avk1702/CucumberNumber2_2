package com.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.pom.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSteps {


    private ProductsPage productsPage;

    @Given("I am on the food page")
    public void iAmOnTheFoodPage() throws InterruptedException {
        WebDriver driver = Hooks.getDriver();
        WebDriverWait wait = Hooks.getWait();
        productsPage = new ProductsPage(driver, wait);
        driver.get("http://149.154.71.152:8080/food");
        Thread.sleep(1000);
    }

    @When("I click the add button")
    public void iClickTheAddButton() throws InterruptedException {
        productsPage.clickAddButton();
        Thread.sleep(1000);
    }

    @When("I enter {string} in the name field")
    public void iEnterInTheNameField(String productName) throws InterruptedException {
        productsPage.nameProductInput(productName);
        Thread.sleep(3000);
    }

    @When("I select {string} from the type dropdown")
    public void iSelectFromTheTypeDropdown(String type) throws InterruptedException {
        productsPage.clickSelectTypeField();
        if (type.equals("Фрукт")) {
            productsPage.clickSelectTypeFruit();
        } else if (type.equals("Овощ")) {
            productsPage.clickSelectVegetableType();
        }
        Thread.sleep(3000);
    }

    @When("I check the exotic checkbox")
    public void iCheckTheExoticCheckbox() throws InterruptedException {
        productsPage.clickSelectExotic();
        Thread.sleep(3000);
    }

    @When("I click the save button")
    public void iClickTheSaveButton() throws InterruptedException {
        productsPage.clickSaveButton();
        Thread.sleep(3000);
    }

    @Then("I should see {string} page")
    public void iShouldSeePage(String pageTitle) throws InterruptedException {
        WebDriver driver = Hooks.getDriver();
        WebDriverWait wait = Hooks.getWait();
        assertEquals(pageTitle, productsPage.openWindowProductList(), "Переход на страницу списка товаров не выполнен");
        Thread.sleep(3000);

    }

    @Then("I should see {string} in the product list")
    public void iShouldSeeInTheProductList(String productName) throws InterruptedException {
        assertEquals(productName, productsPage.displayingNameOfProduct(), "Товар " + productName + " не найден");
        Thread.sleep(3000);

    }

    @Then("I should see {string} in the product list1")
    public void iShouldSeeInTheProductListVegetable(String productName) throws InterruptedException {
        assertEquals(productName, productsPage.displayingNameOfVegetableProduct(), "Товар " + productName + " не найден");
        Thread.sleep(3000);

    }
}
