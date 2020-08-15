package com.linus.lab.algorithm.sampling;

/**
 * @Author wangxiangyu
 * @Date 2020/8/10 13:54
 * @Description TODO
 * 方案一：极坐标，不通用
 * 方案二：拒接接受算法|接受拒绝算法，通用 。
 * https://leetcode-cn.com/problems/generate-random-point-in-a-circle/
 */
public class GenerateRandomPointInACircle {

    private double radius;
    private double xCenter;
    private double yCenter;

    public GenerateRandomPointInACircle(double radius, double xCenter, double yCenter) {
        this.radius = radius;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public double[] randPointByMath() {

        double degreeRandom = 360 * Math.random();
        double radiusRandom = radius *  Math.sqrt(Math.random());

        return new double[]{Math.sin(degreeRandom) * radiusRandom + xCenter,
                Math.cos(degreeRandom) * radiusRandom + yCenter};
    }

    public double[] randPoint() {
        while (true) {
            double xRandom = 1 - 2 * Math.random();
            double yRandom = 1 - 2 * Math.random();

            if (xRandom * xRandom + yRandom * yRandom < 1) {
                return new double[] {radius * xRandom + xCenter, radius * yRandom + yCenter};
            }
        }
    }

    public static void main(String[] args) {
        GenerateRandomPointInACircle o = new GenerateRandomPointInACircle(1,0,1);
        double[] result = o.randPoint();
        System.out.println(result[0] + "   " + result[1]);
    }
}
