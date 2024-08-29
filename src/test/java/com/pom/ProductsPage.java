package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    private final WebDriverWait wait;

    @FindBy(xpath = ".//*[contains(text()[normalize-space(.)],'Добавить')]")
    private WebElement AddButton;

    @FindBy(xpath = "//*[@id=\"editModalLabel\"]")
    private WebElement мodWindowAddingProduct;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement nameInputField;


    @FindBy(xpath = "//*[@id=\"type\"]")
    private WebElement selectTypeField;

    @FindBy(xpath = "//*[@id=\"type\"]/option[2]")
    private WebElement selectTypeFruit;

    @FindBy(xpath = "//*[@id=\"type\"]/option[1]")
    private WebElement selectTypeVegetable;

    @FindBy(xpath = "//*[@id=\"exotic\"]")
    private WebElement selectExotic;

    @FindBy(xpath = ".//*[contains(text()[normalize-space(.)],'Сохранить')]")
    private WebElement saveButton;

    @FindBy(xpath = "//*[contains(normalize-space(text()), 'Список товаров')]")
    private WebElement WindowProductList;

    @FindBy(xpath = ".//*[contains(text()[normalize-space(.)],'Авокадо')]")
    private WebElement displayNameProduct;

    @FindBy(xpath = ".//*[contains(text()[normalize-space(.)],'Огурец')]")
    private WebElement displayNameVegetableProduct;

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }

    public void clickAddButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(AddButton)).click();
    }

    public String openModWindowAddingProduct()
    {
        return wait.until(ExpectedConditions.visibilityOf(мodWindowAddingProduct)).getText();
    }

    public void nameProductInput(String productName)
    {
        wait.until(ExpectedConditions.visibilityOf(nameInputField)).sendKeys(productName);
    }

    public String displayingProductName()
    {
        return wait.until(ExpectedConditions.visibilityOf(nameInputField)).getAttribute("value");
    }

    public void clickSelectTypeField()
    {
        wait.until(ExpectedConditions.elementToBeClickable(selectTypeField)).click();
    }

    public void clickSelectTypeFruit()
    {
        wait.until(ExpectedConditions.elementToBeClickable(selectTypeFruit)).click();
    }

    public void clickSelectVegetableType()
    {
        wait.until(ExpectedConditions.elementToBeClickable(selectTypeVegetable)).click();
    }

    public String displayingTypeField()
    {
        return wait.until(ExpectedConditions.visibilityOf(selectTypeFruit)).getText();
    }

    public String displayingTypeFieldVegetable()
    {
        return wait.until(ExpectedConditions.visibilityOf(selectTypeVegetable)).getText();
    }

    public void clickSelectExotic()
    {
        wait.until(ExpectedConditions.elementToBeClickable(selectExotic)).click();
    }

    public boolean checkSelectExotic()
    {
        return wait.until(ExpectedConditions.visibilityOf(selectExotic)).isSelected();
    }

    public void clickSaveButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

    }
    public String openWindowProductList()
    {
        return wait.until(ExpectedConditions.visibilityOf(WindowProductList)).getText();
    }

    public String displayingNameOfProduct()
    {
        return wait.until(ExpectedConditions.visibilityOf(displayNameProduct)).getText();
    }

    public String displayingNameOfVegetableProduct()
    {
        return wait.until(ExpectedConditions.visibilityOf(displayNameVegetableProduct)).getText();
    }

}