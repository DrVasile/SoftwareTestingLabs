import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Float.parseFloat;

class Pair {
    String x;
    Float y;

    public Pair(String x, Float y) {
        this.x = x;
        this.y = y;
    }
}

class SortList {

    void compare(List<Pair> list) {
        // Comparator to sort the pair according to second element
        list.sort(new Comparator<Pair>() {
            @Override public int compare(Pair p1, Pair p2) {
                return p2.y.compareTo(p1.y);
            }
        });
    }
}


public class Main {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private List<WebElement> itemList;

    private void initDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\drume\\Downloads\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driverWait = new WebDriverWait(driver, 30);
            driver.navigate().to("https://www.elefant.md");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getList() {
        String firstButtonXpath = "//*[@id=\"mobileCat-CartiCatalog\"]/a[1]";
        //String secondButtonXpath = "//*[@id=\"js-categories-container\"]/div/div[1]/ul[1]/li[1]/ul/li[1]/a";
        String itemsPageXpath = "//*[@id=\"family-page\"]/div/div/div[2]/div[4]";
        String itemsXpath = "//*[@id=\"family-page\"]/div/div/div[2]/div[4]/div";

        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstButtonXpath)));
        driver.findElement(By.xpath(firstButtonXpath)).click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(itemsPageXpath)));
        itemList = driver.findElements(By.xpath(itemsXpath));
        List<Pair> pairs = new ArrayList<Pair>();

        for (WebElement element : itemList) {
            String nameClass = "product-title";
            String priceClass = "current-price";
            String name;
            String price;
            Float floatPrice;
            name = element.findElement(By.className(nameClass)).getText();
            price = element.findElement(By.className(priceClass)).getText();
            price = price.replaceAll("[^,.0123456789]","");
            price = price.replace(",", ".");
            floatPrice = parseFloat(price);
            pairs.add(new Pair(name, floatPrice));
        }
        sortPairs(pairs);
        driver.close();
    }

    private void sortPairs(List<Pair> pairs) {
        SortList sortList = new SortList();
        sortList.compare(pairs);
        for (Pair it : pairs) {
            System.out.println(it.x + " " + it.y);
        }
    }

    public static void main(String[] args) {
        Main test = new Main();
        test.initDriver();
        test.getList();
    }
}
