package com.highrock.pdf.util;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Created by user on 2017/12/6.
 */
public class CreateEmail {
/*
    public static void main(String args[]){
        Map<String,String> mail = MailConfig.loadEmailContext("createMail");
        EmailUtils.EmailBean emailBean = new EmailUtils.EmailBean();
        emailBean.setTo("jian_zhang@highrock.com.cn");
        emailBean.setSubject(mail.get("title"));
        emailBean.setMsg(mail.get("message"));
        System.out.println(mail.get("message"));
        EmailUtils.getInstance().sendEmail(emailBean);
    }
*/

    //替换模板中的静态文字
    private String replaceTempletContent(Map<String,String> replaceMap,String content){
        Iterator<Entry<String, String>> entries = replaceMap.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();

            content.replaceAll("##"+entry.getKey(), entry.getValue());

        }
        return content;
    }
}
