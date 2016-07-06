package appiumUtil;

import config.SystemLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import util.CaseStepXmlParser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by wanghongxiang on 16/6/28.
 */
public class XmlStepforCase {
    static Logger log = Logger.getLogger(LaunchTest.class.getName());

    public static void main(String[] args) {

        AndroidDriver driver = null;
        try {


            String page = "clicktab.xml";
            String test = "test.xml";
            Map<String, Map<String, List<String>>> caseTest = CaseStepXmlParser.getXmlValueofallPagewithList(test);

            Set<String> caseKeys = caseTest.keySet();

            if (!verifyArgsParameter(args, caseKeys)) {
                log.info("输入参数格式有误,请认真检查后,再次输入!");
                return;
            }
            driver = AppiumInit.init();
            if (driver == null) {
                throw new RuntimeException("the init not success");

            }

            analyzeArgsParameter(args,driver,page,caseTest);

//            execute(driver, page, caseTest, "登录测试");
//            execute(driver, page, caseTest, "用户信息");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }


    }

    public static void execute(AppiumDriver driver, String page, Map<String, Map<String, List<String>>> caseTest, String tag) {
        try {
            Map<String, List<String>> launchTest = caseTest.get(tag);
            for (String step :
                    launchTest.keySet()
                    ) {
                List<String> arguments = launchTest.get(step);
                if (step.contains("click")) {
                    AppiumActionUtil.clickAppiumFactory(driver, page, arguments.get(0));
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                } else if (step.contains("input")) {
                    AppiumActionUtil.inputAppiumFactory(driver, page, arguments.get(0), arguments.get(1));

                } else if (step.contains("text")) {
                    AppiumActionUtil.textAppiumFactory(driver, page, arguments.get(0));
                } else if (step.contains("data")) {
                    AppiumActionUtil.printPageTextInfo(driver);
                } else  if(step.contains("swipe")){
                    AppiumActionUtil.swipeActionFactory(driver,arguments.get(0),Integer.parseInt(arguments.get(1)));
                }

            }

        }catch (Throwable e){
            SystemLogger.setErrortoLog(e);
        }

    }


    public static boolean verifyArgsParameter(String[] args, Set keyset) {

        for (String s:
             args) {

            System.out.println(s);

        }
        if (args.length != 2) return false;
        // cmd -x xxx,xxx,xxx,xxx
        String cmd = args[0];
        String params = args[1];
        if (cmd.equals("-ma")  && params.equals("default")) return true;
        if (cmd.equals("-mo") && keyset.contains(params)) return true;
        if (cmd.equals("-mm")) {
            if (params.contains(",")) {
                String[] cases = params.split(",");
                for (String s :
                        cases) {
                    if (!keyset.contains(s))
                        return false;

                }
                return true;
            }
        }
        return false;
    }

    public static void analyzeArgsParameter(String[] args, AppiumDriver driver, String page,
                                            Map<String, Map<String, List<String>>> cases) {

        String cmd = args[0];
        String params = args[1];

        switch (cmd){
            case "-ma":// run all cases
                for (String cs:
                     cases.keySet()) {
                    execute(driver,page,cases,cs);
                }
                break;
            case "-mo":// run one case
                execute(driver,page,cases,params);
                break;
            case "-mm"://run many cases

                for (String cs:
                params.split(",")) {
                    execute(driver,page,cases,cs);
                }
                break;
            default:break;
        }


    }


}
