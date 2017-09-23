package cn.ximcloud.dom;

public class Test {
    public static void main(String[] args) {
        Dom dom = Dom.getInstance();
        dom.setHealthInfo();
        System.out.println(dom.getHealthInfo());
    }
}
