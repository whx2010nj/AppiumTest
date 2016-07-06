package config;

/**
 * Created by wanghongxiang on 16/6/29.
 */
public class AssertLogger {

    /**
     * @param condition 断言条件
     */
    public static void assertLog(boolean condition,String activityname){
        try {
            assert condition;
        }catch (AssertionError error){

            String info = "\n************AssertionError*****************\n"
                    + "in the activity :" + activityname + "test step"
                    + SystemLogger.setErrortoLog(error);

            SystemLogger.getLogger().error(info);

        }

    }

    /**
     * @param condition 断言条件
     * @param message 错误信息
     */
    public static void asseretLog(boolean condition,String message,String activityname){

        try {
            assert  condition : message;
        } catch (AssertionError error) {
            String info = "\n************AssertionError*****************\n"
                    + "in the activity :" + activityname + "test step"
                    + SystemLogger.setErrortoLog(error);
            SystemLogger.getLogger().error(info);
        }

    }
}
