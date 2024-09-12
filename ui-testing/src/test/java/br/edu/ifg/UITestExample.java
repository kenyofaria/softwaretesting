package br.edu.ifg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UITestExample {
    private static final String baseUri = "https://lti-ri.imsglobal.org/platforms";

    @Test
    public void shouldCreateNewPlatform() {
        WebDriver driver = new ChromeDriver();
        driver.get(baseUri);

        WebElement addPlatformButton = driver.findElement(By.className("btn-primary"));
        Assertions.assertNotNull(addPlatformButton);

        addPlatformButton.click();
        Assertions.assertEquals(baseUri+"/new", driver.getCurrentUrl());
        WebElement platformNameField = driver.findElement(By.id("platform_name"));
        WebElement platformClientIdField = driver.findElement(By.id("platform_client_id"));
        WebElement platformAudienceField = driver.findElement(By.id("platform_audience"));
        WebElement platformDeepLinkEndpointField = driver.findElement(By.id("platform_deep_link_endpoint"));
        WebElement platformPublicKeyField = driver.findElement(By.id("platform_public_key"));
        WebElement platformPrivateKeyField = driver.findElement(By.id("platform_private_key"));
        WebElement platformToolPublicKeyField = driver.findElement(By.id("platform_tool_public_key"));
        WebElement platformToolKeysetUrlField = driver.findElement(By.id("platform_tool_keyset_url"));

        Assertions.assertNotNull(platformNameField);
        Assertions.assertNotNull(platformClientIdField);
        Assertions.assertNotNull(platformAudienceField);
        Assertions.assertNotNull(platformDeepLinkEndpointField);
        Assertions.assertNotNull(platformPublicKeyField);
        Assertions.assertNotNull(platformPrivateKeyField);
        Assertions.assertNotNull(platformToolPublicKeyField);
        Assertions.assertNotNull(platformToolKeysetUrlField);

        platformNameField.sendKeys("platform name value");
        platformClientIdField.sendKeys("client id value");
        platformAudienceField.sendKeys("platform audience value");
        platformDeepLinkEndpointField.sendKeys("platform deep link endpoint value");
        platformPublicKeyField.sendKeys("platform public key value");
        platformPrivateKeyField.sendKeys("platform private key value");
        platformToolPublicKeyField.sendKeys("tool public key value");
        platformToolKeysetUrlField.sendKeys("tool key set value");


        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"new_platform\"]/input[3]"));
        Assertions.assertNotNull(saveButton);
        saveButton.click();
    }
}
