package com.linus.lab.tool;

import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/7/16 17:14
 * @Description TODO
 */
public class LogStatistics {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("D:\\Temp\\1_kbqa-ss_micro_app_kbqa_lt_all.log");

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> lines = br.lines().collect(Collectors.toList());

        Map<String, List<Cost>> map =
                lines.stream().map(LogStatistics::adpat).filter(LogStatistics::isNotEmpty).collect(Collectors.groupingBy(Cost::getType));
        printAll(map);
    }

    static int KBQA_SUM = 0;

    static int INTENT_SUM = 0;

    static int ENTITY_SUM = 0;

    static int NEO4J_SUM = 0;
    private static Cost adpat(String line) {
        Cost cost= new Cost();
        if (line.contains("KBQAHandler")) {
            String expression =  "consume ([\\d]+\\.[\\d]+) seconds." ;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                cost.setType("KBQA");
                cost.setCost(Double.valueOf(matcher.group(1)));
            }
        } else if (line.contains("url[http://127.0.0.1:9012/v1/kbqa/intent_predict/]")){

            String expression =  "consume ([\\d]+\\.[\\d]+) seconds." ;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                cost.setType("INTENT");
                cost.setCost(Double.valueOf(matcher.group(1)));
            }
        } else if (line.contains("url[http://127.0.0.1:9010/v1/kbqa/entity/get_integrated_entity_from_sentence/]")){

            String expression =  "consume ([\\d]+\\.[\\d]+) seconds." ;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                cost.setType("ENTITY");
                cost.setCost(Double.valueOf(matcher.group(1)));
            }
        } else if (line.contains("NEO4J")){

            String expression =  "consume ([\\d]+\\.[\\d]+) seconds." ;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                cost.setType("NEO4J");
                cost.setCost(Double.valueOf(matcher.group(1)));
            }
        }


        return cost;
    }

    private static boolean isNotEmpty(Cost c) {
        return c.cost != null;
    }

    private static void printAll(Map<String, List<Cost>> map) {

        System.out.println(map.get("KBQA").stream().mapToDouble(Cost::getCost).summaryStatistics().toString());
        System.out.println(map.get("INTENT").stream().mapToDouble(Cost::getCost).summaryStatistics().toString());
        System.out.println(map.get("ENTITY").stream().mapToDouble(Cost::getCost).summaryStatistics().toString());
        System.out.println(map.get("NEO4J").stream().mapToDouble(Cost::getCost).summaryStatistics().toString());

    }
}

class Cost {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String type;

    public Double cost;
}
