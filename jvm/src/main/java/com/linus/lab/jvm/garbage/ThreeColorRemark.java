package com.linus.lab.jvm.garbage;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/29
 */
public class ThreeColorRemark {

    /**
     * 卡表-一种记忆集，用来记录老年代到年轻代的引用。
     * 帮助快速对年轻代进行垃圾回收。
     * 对年轻代垃圾回收，用gc root + 老年代的卡表为1的区域，为扫描根
     *
     *
     *
     *
     * card table 0|1-标记是否引用了年轻代对象，在年轻代
     * |
     * card page 在老年代
     *
     */


    /**
     * 并发标记阶段：
     * black:遍历完成-说明不是垃圾
     * grey:访问过但未遍历完成-说明不是垃圾，但是没完成遍历职责，要继续遍历.
     * white:未访问过-说明可能是垃圾
     *
     *  snapshot 1:
     *
     *  A(black)->B(grey)-->C(black)
     *             \->D(white)
     *
     *---------execute code-------------
     *
     *  snapshot 2:
     *
     *  A(black)->B(black)-->C(black)
     *    \->D(white)
     *
     * D在三色标记结束后，被标记白色，但是实际不是垃圾，需要靠重新标记解决。
     *
     * 重新标记阶段：
     * 解决方方法：
     * 1、增量更新-IncrementalUpdate
     * 通过写屏障，记录新增的引用(A->D)，再把A标记为灰色，重新走三色标记。
     *
     * 2、原始快照-Snapshot At The Beginning，SATB
     * 通过写屏障，记录删除的引用(B->D)，再把D标记为黑色，防止误删除，会产生浮动垃圾。相比增量更新因为不需要重新深度遍历，更高效。
     *
     *
     *
     */
    public static void main(String[] args) {

        A a = new A();
        // 并发标记

        D d = a.b.d;//
        a.b.d = null;
        a.d = d;


    }

    private static class A {
        B b = new B();
        D d = null;
    }

    private static class B {
        C c = new C();
        D d = new D();
    }

    private static class C {}

    private static class D {}
}
