package JunitTest;
import PageModel.*;
import appiumUtil.AppiumInit;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * Created by wanghongxiang on 16/6/30.
 */
public class Test {

    public static Logger logger = Logger.getLogger(Test.class);

    public AppiumDriver driver;
    public HomePage homePage;
    public LaunchPage launchPage;
    public MobileVerifyLaunchPage mobileVerifyLaunchPage;
    public PasswordLaunchPage passwordLaunchPage;
    public UserInformation userInformation;
    public OtherLaunchUserInformation otherLaunchUserInformation;
    public SettingPage settingPage;
    public WeiXinLaunchPage weiXinLaunchPage;
    public SettingLaunchPasswordPage settingLaunchPasswordPage;

    @Before
    public void setup(){
        driver= AppiumInit.init();
        homePage = new HomePage(driver);



//        userInformation = new UserInformation(driver);

    }


    @org.junit.Test
    public void homePageclickTest(){
        homePage.clickTakeAwayButton();
        homePage.clickOrderButton();
        homePage.clickFindButton();
        homePage.clickUserButton();
    }

    @org.junit.Test
    public void firstLaunchTest(){

        homePage.clickUserButton();
        launchPage = new LaunchPage(driver);
        if(launchPage.getuserNameText().equals("立即登录")) {
            launchPage.click_launchButton();

            mobileVerifyLaunchPage = new MobileVerifyLaunchPage(driver);
            mobileVerifyLaunchPage.click_passwordLaunch();

            passwordLaunchPage = new PasswordLaunchPage(driver);
            passwordLaunchPage.userNameClear();
            Assert.assertTrue("手机/邮箱/用户名".equals(passwordLaunchPage.getText_userName()));
            logger.info("首次登录 : 显示 手机/用户名/邮箱" + "status: success" );
            String userName = "whx2010nj";
            String password = "whx0506,.";
            passwordLaunchPage.verifyPasswordLaunch(userName,password);
            Assert.assertEquals(userName +"登录错误",userName,launchPage.getuserNameText());
            logger.info("*********获取登录的信息*************\n" + launchPage.getLaunchInfomation()
                    + "\n***********************************************\n");
            launchPage.click_launchButton();
            userInformation = new UserInformation(driver);
           // userInformation.getuserInformation();

            logger.info("*********获取用户的信息*************\n" + userInformation.getuserInformation()
                    + "\n***********************************************\n");
            userInformation.click_backButton();
            launchPage.click_setting();
            settingPage = new SettingPage(driver);
            settingPage.click_logout();

        } else {
            logger.info("用户: " + launchPage.getuserNameText() + "已登录");
            logger.info("*********获取登录的信息*************\n" + launchPage.getLaunchInfomation()
                    + "\n***********************************************\n");
            launchPage.click_launchButton();
            userInformation = new UserInformation(driver);
            userInformation.getuserInformation();
            logger.info("*********获取用户的信息*************\n" + userInformation.getuserInformation()
            + "\n***********************************************\n");
            userInformation.click_backButton();
            launchPage.click_setting();
            settingPage = new SettingPage(driver);
            settingPage.click_logout();


        }
    }


    @org.junit.Test
    public void weixinLaunchTest(){
        homePage.clickUserButton();
        launchPage = new LaunchPage(driver);
        if(!launchPage.getuserNameText().equals("立即登录")){
            launchPage.click_setting();
            settingPage = new SettingPage(driver);
            settingPage.click_logout();

            settingLaunchPasswordPage = new SettingLaunchPasswordPage(driver);
            settingLaunchPasswordPage.click_skipButton();

        }
        launchPage.click_launchButton();
        mobileVerifyLaunchPage = new MobileVerifyLaunchPage(driver);
        mobileVerifyLaunchPage.click_weixinLaunch();
        weiXinLaunchPage = new WeiXinLaunchPage(driver);
        weiXinLaunchPage.click_launch();
        launchPage.click_launchButton();
        otherLaunchUserInformation = new OtherLaunchUserInformation(driver);
        //otherLaunchUserInformation.getuserInformation();
        logger.info("*********获取用户的信息*************\n" + otherLaunchUserInformation.getuserInformation()
                + "\n***********************************************\n");

    }





    @org.junit.Test
    public void passwordLaunchPageTest(){
        homePage.clickUserButton();
        launchPage = new LaunchPage(driver);
        if(launchPage.getuserNameText().equals("立即登录")){
            launchPage.click_launchButton();

            mobileVerifyLaunchPage = new MobileVerifyLaunchPage(driver);
            mobileVerifyLaunchPage.click_passwordLaunch();

            passwordLaunchPage = new PasswordLaunchPage(driver);
            String userName = "whx2010nj";
            String password = "whx0506,.";
            passwordLaunchPage.verifyPasswordLaunch(userName,password);

            Assert.assertTrue(userName.equals(launchPage.getuserNameText()));

//            passwordLaunchPage.input_userName("15996239267");
//            passwordLaunchPage.input_password("whx0506,.");
//            passwordLaunchPage.click_seePasswordButton();
//            passwordLaunchPage.click_launchButton();
        }



    }

    @After
    public void endclose(){
        driver.quit();
    }



}
