package cn.ximcloud.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 单例，饿汉
 */
public class Dom {
    private String healthInfo;
    private String exportInfo;
    private String[][] myInfo;
    private String[][][] recordInfo;
    private Document document;


    //创建类中是有构造方法
    private Dom(){

    }

    private  static Dom dom = new Dom();


    public Dom getAll(){
        return this;
    }

    @Test
    public void setAll(){
        setHealthInfo();
        setExportInfo();
        setMyInfo();
        setRecordInfo();
    }


    public static Dom getInstance(){
        return dom;
    }

    {
        //1、创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //2、创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3、通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            /*注意导入Document对象时，要导入org.w3c.dom.Document包下的*/
            document = documentBuilder.parse("C:\\Users\\wizard\\Desktop\\this.xml");//传入文件名可以是相对路径也可以是绝对路径
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }


    public void getData(){
        System.out.println("一共有"
                + document.getElementsByTagName("HealthData").getLength() + "个HealthData记录");
        System.out.println("一共有"
                + document.getElementsByTagName("ExportDate").getLength() + "个ExportDate记录");
        System.out.println("一共有"
                + document.getElementsByTagName("Me").getLength() + "个Me记录");
        System.out.println("一共有"
                + document.getElementsByTagName("Record").getLength() + "个Record记录");
    }


    /**
     * 设置获取设备语言
     */
    @Test
    public void setHealthInfo() {
        NodeList nodeList = document.getElementsByTagName("HealthData");
        //通过nodelist的getLength()方法可以获取nodeList的长度
        Node node = nodeList.item(0);
        NamedNodeMap attrs = node.getAttributes();
        Node attr = attrs.item(0);
        healthInfo = attr.getNodeValue();
    }

    /**
     *
     * @return String 设备语言
     */
    @Test
    public String  getHealthInfo() {
        return healthInfo;
    }

    @Test
    public void setExportInfo(){
        NodeList nodeList = document.getElementsByTagName("ExportDate");

        Node node = nodeList.item(0);
        NamedNodeMap attrs = node.getAttributes();
        Node attr = attrs.item(0);
        exportInfo = attr.getNodeValue();
    }

    public String getExportInfo(){
        return exportInfo;
    }

    @Test
    public void setMyInfo(){
        NodeList nodeList = document.getElementsByTagName("Me");
        Node node = nodeList.item(0);
        NamedNodeMap namedNodeMap = node.getAttributes();
        myInfo = new String[namedNodeMap.getLength()][2];
        for (int j = 0; j < namedNodeMap.getLength(); j++) {
            Node attr = namedNodeMap.item(j);
            myInfo[j][0] = attr.getNodeName();
            myInfo[j][1] = attr.getNodeValue();
        }
    }


    public String[][] getMyInfo(){
        return myInfo;
    }

    @Test
    public void setRecordInfo(){
        /**
         * nodeList
         */
        NodeList nodeList = document.getElementsByTagName("Record");
        recordInfo = new String[nodeList.getLength()][][];
        //遍历每一个book节点
        int sum = 0;
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            NamedNodeMap attrs = node.getAttributes();
            if(attrs.getLength()>sum) sum = attrs.getLength();
        }
        recordInfo = new String[nodeList.getLength()][sum][2];
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap attrs = node.getAttributes();
            recordInfo = new String[nodeList.getLength()][sum][2];
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                for (int k = 0; k <sum; k++) {
                    recordInfo[j][k][0] = attr.getNodeName();
                    recordInfo[j][k][0] = attr.getNodeValue();
                }
            }
        }
    }

    public String[][][] getRecordInfo(){
        return recordInfo;
    }

}
