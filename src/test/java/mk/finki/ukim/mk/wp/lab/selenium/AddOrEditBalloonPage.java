package mk.finki.ukim.mk.wp.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloonPage extends AbstractPage{

    private WebElement name;
    private WebElement desc;
    private WebElement manufacturer;
    private WebElement submit;

    public AddOrEditBalloonPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage addBalloon(WebDriver driver,String name, String desc, String manufacturer){
        get(driver,"/balloons/add-form");
        AddOrEditBalloonPage addOrEditBalloonPage = PageFactory.initElements(driver,AddOrEditBalloonPage.class);
        addOrEditBalloonPage.name.sendKeys(name);
        addOrEditBalloonPage.desc.sendKeys(desc);
        addOrEditBalloonPage.manufacturer.click();
        addOrEditBalloonPage.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addOrEditBalloonPage.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }
}
