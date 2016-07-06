package appiumUtil;


import config.AssertLogger;
import config.SystemLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghongxiang on 16/6/23.
 */
public class AppiumActionUtil {

    static Logger logger = Logger.getLogger(AppiumHandleUtil.class.getName());

    public static WebElement getElementByXmlPath(AppiumDriver driver, Map<String, String> map) {


        String pathtype = map.get("pathtype");
        String pathValue = map.get("path");
        switch (pathtype) {
            case "1":
                return AppiumFindElementUtil.findElementByAndroidUIAutomator(driver, pathValue);
            case "2":
                return AppiumFindElementUtil.findElementByXPath(driver, pathValue);

            case "3":
                return AppiumFindElementUtil.findElementById(driver, pathValue);
            case "4":
                return AppiumFindElementUtil.findElementByClassName(driver, pathValue);
            default:
                SystemLogger.getLogger().error("\n please input correct pathtype! Thank you!");
                return null;


        }
    }

    public static String actionByElementType(WebElement element, Map<String, String> map, String value) {

        String type = map.get("type");
        switch (type) {
            case "input":
                AppiumHandleUtil.sendKeys(element, value);
                return null;
            case "button":
                AppiumHandleUtil.click(element);
                return null;
            case "text":
                return AppiumHandleUtil.getText(element);
            default:
                return null;
        }
    }

    public static void inputAppiumFactory(AppiumDriver driver, String page, String elementsName, String value) {

        Map<String, String> map = AppiumDom4JXmlUtil.getXmlValue(page, elementsName);
        WebElement element = getElementByXmlPath(driver, map);
        if (!value.equals(element.getText()))
            AppiumHandleUtil.sendKeys(element, value);
        AssertLogger.assertLog(element.getText() == value, ((AndroidDriver) driver).currentActivity());

    }

    public static void clickAppiumFactory(AppiumDriver driver, String page, String elementsName) {
        Map<String, String> map = AppiumDom4JXmlUtil.getXmlValue(page, elementsName);
        WebElement element = getElementByXmlPath(driver, map);
        AppiumHandleUtil.click(element);
        //logger.info("Activity :" + AppiumHandleUtil.getActivity(driver));
        // AssertLogger.assertLog();
        SystemLogger.getLogger().info("click the button of :" + elementsName);
    }

    public static boolean clickAppiumFactory(AppiumDriver driver, String page, String elementsName, boolean check) {
        Map<String, String> map = AppiumDom4JXmlUtil.getXmlValue(page, elementsName);
        WebElement element = getElementByXmlPath(driver, map);
        WebElement element1 = element.findElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").index(0)"));
        System.out.println(element1.getText());
        if (check && elementsName.equals(element1.getText())) {

            AppiumHandleUtil.click(element);
            return true;
        }
        return false;
    }


    public static String textAppiumFactory(AppiumDriver driver, String page, String elementName) {
        Map<String, String> map = AppiumDom4JXmlUtil.getXmlValue(page, elementName);
        WebElement element = getElementByXmlPath(driver, map);
        String text = AppiumHandleUtil.getText(element);
//        AssertLogger.assertLog();
        SystemLogger.getLogger().info("The text information of " + elementName + "is " + text);
        return text;
    }


    public static void printTextViewText(AppiumDriver driver1) {

        AndroidDriver driver = (AndroidDriver) driver1;

//        List<WebElement> elementsByAndroidUIAutomator = ((AndroidDriver)driver).findElementsByAndroidUIAutomator("new UiSelector().classNameMatches(\".*TextView$\")");

        try {

            List<WebElement> elementsByAndroidUIAutomator = driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")");

//             List<WebElement> elementsByAndroidUIAutomator = driver.findElements(By.className("android.widget.TextView"));
            for (WebElement e : elementsByAndroidUIAutomator
                    ) {
                System.out.println(e.getText());

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("android TextView don't find");

        }
        try {
            List<WebElement> checkedelements = driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.CheckedTextView\")");


            for (WebElement e : checkedelements
                    ) {
                System.out.println(e.getText());

            }

        } catch (Exception e) {

            e.printStackTrace();
            logger.info("android CheckedTextView don't find");
        }

        try {
            List<WebElement> ivelements = driver.findElementsByAndroidUIAutomator("new UiSelector().className(\\\"android.widget.ImageView\")");


            for (WebElement e : ivelements
                    ) {
                System.out.println(e.getText());

            }

        } catch (Exception e) {

            e.printStackTrace();
            logger.info("android ImageView don't find");
        }

    }

    public static void printPageTextInfo(AppiumDriver driver) {

        SystemLogger.getLogger().info(
                "*************Page information ***************"
        );

        try {
            Thread.sleep(2000);
            StringBuilder sb = new StringBuilder();
            String pageXml = driver.getPageSource();
            String[] split = pageXml.split("\\s+");
            for (String str : split
                    ) {

                if (str.contains("text=")) {
                    for (String s : str.split("\"")
                            ) {
                        if (!s.contains("text")) {
                            System.out.println(s);
                            sb.append(s + "\n");
                        }


                    }

                }

                if (str.contains("content=")) {
                    for (String s : str.split("\"")
                            ) {
                        if (!s.contains("content")) {
                            System.out.println(s);
                            sb.append(s + "\n");
                        }


                    }

                }


            }
            SystemLogger.getLogger().info(
                    "************************************************"
            );

        } catch (Exception e) {
            e.printStackTrace();
            SystemLogger.setErrortoLog(e);
            logger.info("The sleep time exception");

        }

    }


    /**
     * 等待控件的出现
     *
     * @param driver
     * @param elementName
     * @return
     */
    public static WebElement waitForWebElement(AppiumDriver driver, final String elementName) {


        WebElement e = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id(elementName));
            }
        });
        return e;

    }

    public static void swipeActionFactory(AppiumDriver driver, String upOrdown, int during) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            SystemLogger.setErrortoLog(e);
            e.printStackTrace();
        }

        if (upOrdown.equals("up")) {
            swipeToUp(driver, during);
        } else if (upOrdown.equals("down")) {
            swipeToDown(driver, during);
        }


    }

    /**
     * This Method for swipe down
     *
     * @param driver
     * @param during
     */
    public static void swipeToDown(AppiumDriver driver, int during) {
        try {
            Thread.sleep(5000);
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);

//            Thread.sleep(5000);
        } catch (InterruptedException e) {
            SystemLogger.setErrortoLog(e);
            e.printStackTrace();
        }
        // wait for page loading
    }

    /**
     * This Method for swipe up
     *
     * @param driver
     * @param during
     */
    public static void swipeToUp(AppiumDriver driver, int during) {
        try {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            Thread.sleep(5000);
            driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);
            // wait for page loading

//            Thread.sleep(2000);
        } catch (InterruptedException e) {
            SystemLogger.setErrortoLog(e);
            e.printStackTrace();
        }
    }

    public static void scollerView(AppiumDriver driver1, int during) {

        AndroidDriver driver = ((AndroidDriver) driver1);
        driver.findElementByAndroidUIAutomator("new UiSelector().className(\\\"android.widget.ImageView\")\"");
    }

}
