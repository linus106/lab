package com.linus.lib.springmvc.framework.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/18
 */
public class LabXmlParser {

    public static String getBasePackage(String location) {

//
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(location);
//
//
//        Element root = document.getRootElement();





        return "com.linus.lib.springmvc.app";
    }
}
