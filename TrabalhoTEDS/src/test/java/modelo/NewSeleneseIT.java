package modelo;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewSeleneseIT {

    private WebDriver driver;

    @Rule
    public TestRule watcher = new TestWatcher() {

        protected void failed(Throwable throwable, Description description) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("failshot_" + description.getClassName() + "_" + description.getMethodName() + ".png"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    };

    @BeforeClass
    public static void setupClass() {
        //Chrome
        ChromeDriverManager.getInstance().setup();

        //Firefox
//        MarionetteDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        //Chrome
        driver = new ChromeDriver();

        //Firefox
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("marionette", true);
//        driver = new FirefoxDriver(capabilities);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void deveConseguirInserirBebida() throws InterruptedException {

        driver.get("localhost:8084/TrabalhoTEDS/");

        WebElement elemento = driver.findElement(By.id("adicionar"));
        elemento.click();

        WebElement codigo_bebida = driver.findElement(By.id("codigo_bebida"));
        codigo_bebida.sendKeys("210010");

        WebElement nome_bebida = driver.findElement(By.id("nome_bebida"));
        nome_bebida.sendKeys("cerveja");

        WebElement descricao_bebida = driver.findElement(By.id("descricao_bebida"));
        descricao_bebida.sendKeys("cerveja puro malte");

        WebElement preco_bebida = driver.findElement(By.id("preco_bebida"));
        preco_bebida.sendKeys("5.50");

        WebElement inserir_bebida = driver.findElement(By.id("inserir_bebida"));
        inserir_bebida.submit();

        Thread.sleep(10000);
    }

    @Test
    public void deveConseguirInserirPrato() throws InterruptedException {

        driver.get("localhost:8084/TrabalhoTEDS/");

        WebElement elemento = driver.findElement(By.id("adicionar"));
        elemento.click();

        WebElement codigo_prato = driver.findElement(By.id("codigo_prato"));
        codigo_prato.sendKeys("210010");

        WebElement nome_prato = driver.findElement(By.id("nome_prato"));
        nome_prato.sendKeys("rocambole");

        WebElement descricao_prato = driver.findElement(By.id("descricao_prato"));
        descricao_prato.sendKeys("gostoso");

        WebElement preco_prato = driver.findElement(By.id("preco_prato"));
        preco_prato.sendKeys("5.50");

        WebElement inserir_prato = driver.findElement(By.id("inserir_prato"));
        inserir_prato.submit();

        Thread.sleep(10000);
    }

    @Test
    public void deveConseguirFinalizarCompra() throws InterruptedException {

        driver.get("localhost:8084/TrabalhoTEDS/");

        WebElement elemento = driver.findElement(By.id("comprar"));
        elemento.click();

        WebElement quant = driver.findElement(By.id("quantidadebebida"));
        quant.sendKeys("3");

        org.openqa.selenium.support.ui.Select dropdown
                = new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("bebidaid")));
        dropdown.selectByIndex(0);

        WebElement add = driver.findElement(By.id("adicionarbebida"));
        add.click();

        WebElement finalizar = driver.findElement(By.id("finalizar"));
        finalizar.submit();

        Thread.sleep(10000);
    }

}
