package cn.ximcloud.test;

import cn.ximcloud.dom.Result;

public class Test {
    public static void main(String[] args) {
        Result result = Result.getInstance();
        result.setAll();
        result.getAll();
    }
}
