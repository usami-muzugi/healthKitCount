package cn.ximcloud.dom;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class Result{
    public Dom dom;
    private Result(){
        dom = Dom.getInstance();
    }

    private static Result result = new Result();
    public static Result getInstance(){
        return result;
    }

    public void setAll(){
        System.out.println("开始时间:"+new Date());
        System.out.println("==============================================");
        System.out.println("setAll");
        dom.setAll();
        System.out.println("==============================================");
        System.out.println("结束时间:"+new Date());
        System.out.println();
    }

    @Test
    public void getAll(){
        System.out.println("开始时间:"+new Date());
        System.out.println("==============================================");
        dom.getData();
        System.out.println("==============================================");
        String word = dom.getHealthInfo();
        String[] words = word.split("-");
        if(words[0].equals("zh")) word ="(中文)";
        System.out.println("设备语言:"+dom.getHealthInfo() + word);
        System.out.println("数据生成时间:"+dom.getExportInfo());
        String[][] myInfo =dom.getMyInfo();
        for (int i = 0; i <myInfo.length ; i++) {
            switch (myInfo[i][0]) {
                case "HKCharacteristicTypeIdentifierBiologicalSex" :
                    if(myInfo[i][1].equals("HKBiologicalSexMale")){
                        myInfo[i][1] ="男";
                    }else if(myInfo[i][1].equals("HKBiologicalSexFeMale")){
                        myInfo[i][1] ="女";
                    }else System.out.println("你居然是基佬");
                    System.out.println("性别:"+ myInfo[i][1]);
                    break;
                case "HKCharacteristicTypeIdentifierBloodType" :
                    if(myInfo[i][1].equals("HKBloodTypeNotSet"))
                        myInfo[i][1] ="血型为设置";
                    System.out.println("血型:"+ myInfo[i][1]);
                    break;
                case "HKCharacteristicTypeIdentifierDateOfBirth" :
                    System.out.println("生日:"+myInfo[i][1]);
                    break;
                default: break;
            }
        }
        System.out.println("==============================================");
        System.out.println("结束时间:"+new Date());
    }
}
