package util;

import appiumUtil.AppiumDom4JXmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.*;

/**
 * Created by wanghongxiang on 16/6/28.
 */
public class CaseStepXmlParser {

    public static Document getfile(String page){
        return AppiumDom4JXmlUtil.getfile(page);
    }


    /**
     * @param page xml文件名
     *
     * @return  返回控件的查找属性
     */
    public static Map<String,Map<String,Map<String,String>>> getXmlValueofallPage(String page){

        try{

            Document document =getfile(page);
            Element rootElement = document.getRootElement();
            List<Element> cases = rootElement.elements("case");
            Map<String,Map<String,Map<String,String>>> caseMap = new LinkedHashMap<>();



            for (Element oneCase:
                    cases ) {
                Map<String,Map<String,String>> stepMap = new LinkedHashMap<>();
                List<Element> steps = oneCase.elements();
                for (Element step:steps
                        ) {
                    List<Element> arguments = step.elements();
                    Map<String,String> argumentMap = new HashMap<>();
                    for (Element argument :
                            arguments) {
                        argumentMap.put(argument.getName(),argument.getText());

                    }
                    stepMap.put(step.getName(),argumentMap);

                }
                System.out.print(oneCase.getName());
                caseMap.put(oneCase.getName(),stepMap);

            }

            return caseMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    /**
     * @param page xml文件名
     *
     * @return  返回控件的查找属性
     */
    public static Map<String,Map<String,List<String>>> getXmlValueofallPagewithList(String page){

        try{

            Document document =getfile(page);
            Element rootElement = document.getRootElement();
            List<Element> cases = rootElement.elements("case");
            Map<String,Map<String,List<String>>> caseMap = new LinkedHashMap<>();



            for (Element oneCase:
                    cases ) {
               Map<String,List<String>> stepMap = new LinkedHashMap<>();
                List<Element> steps = oneCase.elements();
                for (Element step:steps
                        ) {
                    List<Element> arguments = step.elements();
                    List<String> argumentList = new ArrayList<>();
                    for (Element argument :
                            arguments) {
                        argumentList.add(argument.getText());

                    }
                    stepMap.put(step.attributeValue("name"),argumentList);

                }
                caseMap.put(oneCase.attributeValue("name"),stepMap);
            }
            return caseMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
