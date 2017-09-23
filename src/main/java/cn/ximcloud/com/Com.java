package cn.ximcloud.com;

import cn.ximcloud.dom.Result;

public class Com {
    public Com(){
        Result result = Result.getInstance();
        result.setAll();
        result.getAll();
    }

    public static void main(String[] args) {
        new Com();
    }
}
