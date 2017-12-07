package com.highrock.pdf.util;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MailConfig {

	public static Map<String,String> loadEmailContext(String mailType) {
		Map<String,String> mailMap = new HashMap<String,String>();
		System.err.println("读取配置文件mail.xml...");
        try {  XMLConfiguration conf=new XMLConfiguration("mail.xml");
        HierarchicalConfiguration sub = conf.configurationAt(mailType);
            Iterator it = sub.getKeys();
            while(it.hasNext()){
                String key = (String)it.next();
                mailMap.put(key,sub.getString(key));
            }
            System.err.println("读取配置文件mail.xml...完毕！");
        } catch(Exception ex){
            ex.printStackTrace();
            System.err.println("读取配置文件mail.xml...出错！");
        }
        return mailMap;
	}
 /*   public static void main(String[] args) throws Exception {
        MailConfig.loadEmailContext("createMail");
    }*/
}
