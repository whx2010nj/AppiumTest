package appiumUtil;

import com.sun.tools.javac.util.Log;
import io.appium.java_client.android.AndroidDriver;
import javaTest.Assert;

import java.util.logging.Logger;

/**
 * Created by wanghongxiang on 16/6/23.
 */
public class LaunchTest {

    static  Logger log = Logger.getLogger(LaunchTest.class.getName());

    public static void main(String[] args)  {

        AndroidDriver driver = AppiumInit.init();
        if(driver == null) {
            throw new RuntimeException("the init not success");

        }


        try {

            String page = "clicktab.xml";
            //首次登录

            AppiumActionUtil.clickAppiumFactory(driver,page,"我的");
            AppiumActionUtil.clickAppiumFactory(driver,page,"立即登录");

            AppiumActionUtil.inputAppiumFactory(driver,page,"手机号","15996239267");

            AppiumActionUtil.clickAppiumFactory(driver,page,"获取验证码");
            AppiumActionUtil.clickAppiumFactory(driver,page,"返回");
            AppiumActionUtil.clickAppiumFactory(driver,page,"立即登录");
            log.info("倒计时:" + AppiumActionUtil.textAppiumFactory(driver,page,"获取验证码"));
            AppiumActionUtil.clickAppiumFactory(driver,page,"密码登录");
            AppiumActionUtil.clickAppiumFactory(driver,page,"返回");

            log.info("倒计时:" + AppiumActionUtil.textAppiumFactory(driver,page,"获取验证码"));

            AppiumActionUtil.inputAppiumFactory(driver,page,"手机号","3343");
            AppiumActionUtil.clickAppiumFactory(driver,page,"登录1");
//            Thread.sleep(10000);
            System.out.println(driver.getPageSource());

            AppiumActionUtil.clickAppiumFactory(driver,page,"密码登录");
            String text = AppiumActionUtil.textAppiumFactory(driver,page,"手机");
            try {
                assert "手机/用户名/密码".equals(text) : "首页登录文本不对";
                log.info("首页登录文本正确 "+ text);
            }catch (AssertionError assertionError){
                assertionError.printStackTrace();
                log.info("非首次登录");
            }

            //登录
            AppiumActionUtil.inputAppiumFactory(driver,page,"手机","15996239267");

            AppiumActionUtil.clickAppiumFactory(driver,page,"忘记密码");
            AppiumActionUtil.clickAppiumFactory(driver,page,"返回");

            AppiumActionUtil.inputAppiumFactory(driver,page,"密码","whx0506,.");
            Thread.sleep(1000);
            AppiumActionUtil.clickAppiumFactory(driver,page,"明文密码");
            Thread.sleep(1000);
            log.info("明文密码:" + AppiumActionUtil.textAppiumFactory(driver,page,"密码"));
            AppiumActionUtil.clickAppiumFactory(driver,page,"明文密码");
            Thread.sleep(1000);
            log.info("暗文密码:" + AppiumActionUtil.textAppiumFactory(driver,page,"密码"));
            AppiumActionUtil.clickAppiumFactory(driver,page,"登录");

            Thread.sleep(5000);

            //退出登录
            AppiumActionUtil.clickAppiumFactory(driver,page,"设置");
            AppiumActionUtil.clickAppiumFactory(driver,page,"退出登录");

            AppiumActionUtil.clickAppiumFactory(driver,page,"立即登录");
            AppiumActionUtil.clickAppiumFactory(driver,page,"密码登录");
            String text1 = AppiumActionUtil.textAppiumFactory(driver,page,"手机");
            try{
                assert !text1.equals("手机/用户名/密码"): "首页登录文本不对";
                log.info("非首次登录,用户名为:" + text1);
            }catch (AssertionError assertionError){
                assertionError.printStackTrace();
            }

            //错误登录
            AppiumActionUtil.inputAppiumFactory(driver,page,"密码","whx0506 ");
            AppiumActionUtil.clickAppiumFactory(driver,page,"登录");
            Thread.sleep(5000);
            AppiumActionUtil.inputAppiumFactory(driver,page,"密码","whx0506,.");
            AppiumActionUtil.clickAppiumFactory(driver,page,"验证码图片");
            Thread.sleep(2000);
            AppiumActionUtil.clickAppiumFactory(driver,page,"换一张");
            AppiumActionUtil.inputAppiumFactory(driver,page,"验证码","517517");
            AppiumActionUtil.clickAppiumFactory(driver,page,"登录");

           // System.out.println(driver.getPageSource());


//
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }



    }

}
