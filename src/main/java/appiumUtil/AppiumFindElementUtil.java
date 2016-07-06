package appiumUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by wanghongxiang on 16/6/23.
 */
public class AppiumFindElementUtil {

    /**
     * By UIAutomator
     * @param driver  Appium
     * @param pathValue 元素路径
     * @return WebElement
     */
    public static WebElement findElementByAndroidUIAutomator(AppiumDriver driver, String pathValue){

        return ((AndroidDriver)driver).findElementByAndroidUIAutomator(pathValue);
    }

    /**
     * ByxPath
     * @param driver  Appium
     * @param pathValue 元素路径
     * @return WebElement
     */
    public static WebElement findElementByXPath(AppiumDriver driver, String pathValue){
        return driver.findElementByXPath(pathValue);
    }

    /**
     * ById
     * @param driver  Appium
     * @param pathValue 元素路径
     * @return WebElement
     */
    public static WebElement findElementById(AppiumDriver driver,String pathValue){
        return driver.findElementById(pathValue);

    }

    /**
     * ByClassName
     * @param driver  Appium
     * @param pathValue 元素路径
     * @return WebElement
     */
    public static WebElement findElementByClassName(AppiumDriver driver,String pathValue){
        return driver.findElementByClassName(pathValue);

    }

    /**
     * find ParentElement
     * @param driver  Appium
     * @param pathValue 元素路径
     * @return WebElement
     */
    public static WebElement findParentElementofElement(AppiumDriver driver,String classType,String childElement){
//        return driver.findElementByClassName(pathValue);
        String parentUiselector = "new UiSelector().className(" +classType + ").text("+ childElement +")";

        return ((AndroidDriver)driver).findElementByAndroidUIAutomator(parentUiselector);


    }
}
