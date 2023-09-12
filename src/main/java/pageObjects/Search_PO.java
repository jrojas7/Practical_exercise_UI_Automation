package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Search_PO extends Base_PO {
    List<String> names = new ArrayList<>();
    List<String> prices = new ArrayList<>();
    List<String> links = new ArrayList<>();
    File fi = new File(System.getProperty("user.dir") + "/src/test/resources/txtFiles/camisetas.txt");

    private @FindBy(css = "h2[class='ui-search-item__title shops__item-title']") List<WebElement> item_Names;
    private @FindBy(css = ".ui-search-price__part--medium .andes-money-amount__fraction") List<WebElement> item_Prices;
    private @FindBy(css = "a[class='ui-search-item__group__element shops__items-group-details ui-search-link']") List<WebElement> item_Links;
    private @FindBy(css = "a[title='Siguiente']") WebElement next_Btn;
    private @FindBy(css = "h1.ui-search-breadcrumb__title.shops-custom-primary-font") WebElement search_Title;

    public Search_PO() {
        super();
    }

    public void click_Next_Btn() {
        waitForWebElementAndClick(next_Btn);
    }

    public void read_Search_Content() {
        for (int i = 0; i < 3; i++) {

            for (WebElement itemName : item_Names) {
                names.add(itemName.getText());
            }
            for (WebElement itemPrice : item_Prices) {
                prices.add(itemPrice.getText());
            }
            for (WebElement itemLink : item_Links) {
                links.add(itemLink.getAttribute("href"));
            }

            click_Next_Btn();
        }

        System.out.println(" ");
        System.out.println("**************************** GETTING INFO FROM THE FIRST 3 PAGES... ****************************************");

        for (String name : names) {
            System.out.println(name);
        }
        for (String price : prices) {
            System.out.println(price);
        }
        for (String link : links) {
            System.out.println(link);
        }

        int totalItems = names.size();
        System.out.println("Total number of items: " + totalItems);

        int totalPrices = prices.size();
        System.out.println("Total number of prices: " + totalPrices);

        int totalLinks = links.size();
        System.out.println("Total number of links: " + totalLinks);
    }

    public void write_File() {
        try {
            fi.createNewFile();
            FileWriter fw = new FileWriter(fi);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String name : names) {
                bw.write(name + "\n");
            }

            for (String price : prices) {
                bw.write(price + "\n");
            }

            for (String link : links) {
                bw.write(link + "\n");
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read_File_And_Validate() {

        ArrayList<String> aList = new ArrayList<>();
        List<String> concatList1 = Stream.concat(names.stream(), prices.stream()).collect(Collectors.toList());
        List<String> concatList2 = Stream.concat(concatList1.stream(), links.stream()).collect(Collectors.toList());

        try {
            FileReader fr = new FileReader(fi);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                aList.add(line);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" ");
        System.out.println("**************************** READING THE .TXT FILE... ****************************************");
        for (int i = 0; i < aList.size(); i++) {
            System.out.println(aList.get(i));
        }

        // Assert for verification of the contents in the TXT file and the information from the 3 pages
        Assert.assertEquals(aList, concatList2);
    }

    public void get_Search_Title_And_Validate_Text() {
        waitFor(search_Title);
        String title_Text = search_Title.getText();

        // Assert to verify that the list of items page is displaying the correct title
        Assert.assertEquals(title_Text, "Balones futbol");
    }
}
