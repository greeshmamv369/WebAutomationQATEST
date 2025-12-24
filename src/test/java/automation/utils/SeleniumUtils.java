package automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class SeleniumUtils {
    static WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10; // seconds
    private static final int DEFAULT_POLLING = 500; // milliseconds
    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // ================= CLICK METHODS =================

    public static void click(WebElement element) {
        waitForClickability(element, DEFAULT_TIMEOUT);
        element.click();
    }

    public static void jsClick(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click();", element);
    }

    // ================= SEND KEYS / INPUT =================

    public static void type(WebElement element, String text) {
        waitForVisibility(element, DEFAULT_TIMEOUT);
        element.clear();
        element.sendKeys(text);
    }

    public static void typeJs(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    // ================= GET TEXT / ATTRIBUTES =================

    public static String getText(WebElement element) {
        waitForVisibility(element, DEFAULT_TIMEOUT);
        return element.getText();
    }

    public static String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    // ================= DROPDOWN =================

    public static void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public static void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public static List<WebElement> getAllOptions(WebElement element) {
        Select select = new Select(element);
        return select.getOptions();
    }

    // ================= SCROLLING =================

    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollBy(int x, int y) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    // ================= ALERTS =================

    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public static void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public static String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public static void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // ================= FRAMES =================

    public static void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    public static void switchToFrameByNameOrId(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public static void switchToFrameByWebElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // ================= ACTIONS =================

    public static void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public static void dragAndDrop(WebElement source, WebElement target) {
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    public static void rightClick(WebElement element) {
        new Actions(driver).contextClick(element).perform();
    }

    public static void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    public static void clickAndHold(WebElement element) {
        new Actions(driver).clickAndHold(element).perform();
    }

    public static void release(WebElement element) {
        new Actions(driver).release(element).perform();
    }

    public static void sendKeysUsingActions(WebElement element, String text) {
        new Actions(driver).sendKeys(element, text).perform();
    }

    // ================= CHECKBOX / RADIO =================

    public static void selectCheckbox(WebElement element) {
        if (!element.isSelected()) element.click();
    }

    public static void deselectCheckbox(WebElement element) {
        if (element.isSelected()) element.click();
    }

    public static boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    // ================= NAVIGATION =================

    public static void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public static void navigateForward() {
        driver.navigate().forward();
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }

    // ================= CLEAR INPUT =================

    public static void clearField(WebElement element) {
        element.clear();
    }

    // =============== EXCEL UTILITIES ===============

    public static String readCell(String filePath, int sheetIndex, int rowIndex, int cellIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            if (row == null) return "";
            Cell cell = row.getCell(cellIndex);
            return (cell == null) ? "" : cell.toString();
        }
    }

    public static void writeCell(String filePath, int sheetIndex, int rowIndex, int cellIndex, String value) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        fis.close();

        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        if (row == null) row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    public static List<List<String>> readSheet(String filePath, int sheetIndex) throws IOException {
        List<List<String>> sheetData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    rowData.add(cell.toString());
                }
                sheetData.add(rowData);
            }
        }
        return sheetData;
    }

    public static int getRowCount(String filePath, int sheetIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            return sheet.getLastRowNum() + 1;
        }
    }

    // ================= EXPLICIT WAITS =================

    public static WebElement waitForVisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPresence(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForText(WebElement element, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitForTitle(String title, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitForUrlContains(String keyword, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.urlContains(keyword));
    }

    public static void waitForAlert(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForFrameToBeAvailableAndSwitchToIt(WebElement frame, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    // ================= FLUENT WAIT =================

    public static WebElement fluentWait(final By locator, int timeoutInSeconds, int pollingInMillis) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    public static WebElement fluentWait(WebElement element, int timeoutInSeconds, int pollingInMillis) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> element.isDisplayed() ? element : null);
    }

    // ================= DEFAULT SHORTCUTS =================

    public static WebElement waitForVisibility(WebElement element) {
        return waitForVisibility(element, DEFAULT_TIMEOUT);
    }

    public static void waitForClickability(WebElement element) {
        waitForClickability(element, DEFAULT_TIMEOUT);
    }

    public static void waitForPresence(By locator) {
        waitForPresence(locator, DEFAULT_TIMEOUT);
    }

    public static void waitForAlert() {
        waitForAlert(DEFAULT_TIMEOUT);
    }

    public static void waitForText(WebElement element, String text) {
        waitForText(element, text, DEFAULT_TIMEOUT);
    }

    public static void waitForTitle(String title) {
        waitForTitle(title, DEFAULT_TIMEOUT);
    }

    public static void waitForUrlContains(String keyword) {
        waitForUrlContains(keyword, DEFAULT_TIMEOUT);
    }

    public static void waitForFrameToBeAvailableAndSwitchToIt(WebElement frame) {
        waitForFrameToBeAvailableAndSwitchToIt(frame, DEFAULT_TIMEOUT);
    }
}
