package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Home_PO;
import pageObjects.Search_PO;

public class Search_Steps extends Base_PO {
    private Home_PO home_po;
    private Search_PO search_po;

    public Search_Steps(Home_PO home_po, Search_PO search_po) {
        this.home_po = home_po;
        this.search_po = search_po;
    }

    @Given("I access the Mercado Libre Home page")
    public void i_access_the_mercado_libre_home_page() {
        home_po.navigateTo_MercadoLibre_Home_Page();
    }

    @When("I accept the cookies information popup")
    public void i_accept_the_cookies_information_popup() {
        home_po.accept_Cookies();
    }

    @And("I enter a specific item {string} in the item Search field")
    public void i_enter_a_specific_item_in_the_search_field(String item) {
        home_po.set_Search_Item(item);
    }

    @And("I click on the Search icon")
    public void i_click_on_the_search_icon() {
        home_po.click_Search_Btn();
    }

    @And("I get the items information from the first three pages")
    public void i_get_the_items_information_from_the_first_three_pages() {
        search_po.read_Search_Content();
    }

    @And("I write and save the items information in a TXT file")
    public void i_write_and_save_the_items_information_in_a_txt_file() {
        search_po.write_File();
    }

    @Then("the information in the TXT file should be the same as the information captured from the 3 pages")
    public void these_information_should_be_the_same_after_read_the_txt_file() {
        search_po.read_File_And_Validate();
    }

    @Then("I should be presented with the correct items list page")
    public void i_should_be_presented_with_the_correct_items_list_page() {
        search_po.get_Search_Title_And_Validate_Text();
    }

}
