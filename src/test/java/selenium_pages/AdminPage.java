package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage verifyNavigation() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text and ./parent::*[@id='toolbar']]")));
        String title = driver.findElement(By.xpath("//*[@text and ./parent::*[@id='toolbar']]")).getText();
        assertTrue("Admin Page Title contains Administrator Home", title.contains("Administrator Home"));
        return new AdminPage(driver);
    }

    public void navigateToProfileEdit() {
        // Click the Nav Drawer Popout

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text and ./parent::*[@id='toolbar']]")));
        String toolBarTitle = driver.findElement(By.xpath("//*[@text and ./parent::*[@id='toolbar']]")).getText();
        assertTrue("Toolbar Title contains Administrator Home", toolBarTitle.contains("Administrator Home"));
        driver.findElement(By.xpath("//*[@contentDescription='Open navigation drawer']")).click();

        // Use Nav Drawer to Nav to Edit Profile Page;
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='edit']")));
        driver.findElement(By.xpath("//*[@id='edit']")).click();
    }

}
