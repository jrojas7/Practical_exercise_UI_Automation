package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Global_Vars;

public class Home_PO extends Base_PO{
    private @FindBy(css = "button[data-testid='action:understood-button']") WebElement acceptCookies_Btn;
    private @FindBy(css = "input[id='cb1-edit']") WebElement search_TextField;
    private @FindBy(css = "div[class='nav-icon-search']") WebElement search_Btn;

    public Home_PO() {
        super();
    }

    public void navigateTo_MercadoLibre_Home_Page() {
        navigateTo_URL(Global_Vars.MERCADO_LIBRE_HOMEPAGE_URL);
    }
    public void accept_Cookies() {
     waitForWebElementAndClick(acceptCookies_Btn);
    }
    public void set_Search_Item(String item) {
        sendKeys(search_TextField, item);
    }
    public void click_Search_Btn() {
        waitForWebElementAndClick(search_Btn);
    }

}
