package com.linus.lab.tool;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/7/16 17:14
 * @Description TODO
 */
public class LogExtract {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("D:\\Temp\\1_kbqa-ss_micro_app_kbqa_lt_all.log");

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> lines = br.lines().collect(Collectors.toList());

        Set<String> set = lines.stream().map(LogExtract::extract).filter(s->!"".equals(s)).collect(Collectors.toSet());
        set.stream().forEach(System.out::println);
    }
    private static String extract(String line) {
        if (line.contains("intent_handler.py")) {
            String expression =  "'payload': '(.*)'" ;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String a =  String.valueOf(matcher.group(1));
                try {
                    System.out.println(new String(a.getBytes(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return a;
            }
        }
        return "";
    }

}
