package com.highrock.pdf.util;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.internet.MimeUtility;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EmailUtils {
	private static EmailUtils instance;
	private EmailUtils(){}


	public static EmailUtils getInstance(){
		if(instance == null){
			instance = new EmailUtils();
		}
		return instance;
	}
	public void sendEmail(EmailBean emailBean){
		EmailThread et = new EmailThread(emailBean);
		et.start();
	}
	public class EmailThread extends Thread{
		private EmailBean emailBean;
		public EmailThread(EmailBean emailBean) {
			this.emailBean = emailBean;
		}
		private final Logger logger = LoggerFactory.getLogger(EmailThread.class);
		@Override
		public void run() {
			Map<String,String> mailConfig = MailConfig.loadEmailContext("mailConfig");

			try{
				long start = System.currentTimeMillis();
				//Email email = new SimpleEmail();
				HtmlEmail email = new HtmlEmail();
				//email.setHostName(mailConfig.get("hostName"));
				email.setHostName("smtp.163.com");
				//email.setSmtpPort(Integer.valueOf(mailConfig.get("smtpPort")));
				if("true".equals(mailConfig.get("useAuth"))){
					email.setAuthentication(mailConfig.get("authenticatorName"), mailConfig.get("authenticatorPassword"));
					String from = mailConfig.get("mailFrom");
					email.setFrom(from, from);
				}
				else{
					String from = this.emailBean.getFromEmail()==null?mailConfig.get("mailFrom"):this.emailBean.getFromEmail();
					email.setFrom(from, this.emailBean.getFromName()==null?"卓朗审批系统":this.emailBean.getFromName());
				}
				email.setCharset(mailConfig.get("charset"));
				
				email.setSubject(this.emailBean.getSubject());
				if(this.emailBean.getUseHtmlStyle()){
					email.setHtmlMsg(setHtmlStyle()+this.emailBean.getMsg());
				}
				else{
					email.setHtmlMsg(this.emailBean.getMsg());
				}
				
				//群发
				String toEmails = this.emailBean.getTo();
				if(toEmails.indexOf(";")!=-1){
					Object [] adds = toEmails.split(";");
					for(int i=0;i<adds.length;i++){
						email.addTo(adds[i].toString());
					}
				}else{
					email.addTo(this.emailBean.getTo());
				}
				if(this.emailBean.getEmailAttachments()!=null&&this.emailBean.getEmailAttachments().length>0){
					for(EmailAttachment attach:this.emailBean.getEmailAttachments()){
						email.attach(attach);
					}
				}
				if(this.emailBean.getAttachFile()!=null){
					email.attach(this.emailBean.getAttachFile());
				}
				if("true".equals(mailConfig.get("useSSL"))){
					email.setSSLOnConnect(true);
				}
				email.send();
				long end = System.currentTimeMillis();
				logger.info("邮件发送完毕：[" + this.emailBean.getTo() + "]，共"+(end-start)+"ms");
			}catch (Exception e) {
				logger.error("邮件发送失败：[" + this.emailBean.getTo() + "]，原因：" + e.getMessage());
			}
		}
	}
	public static class EmailBean{
		private String to;
		private String subject;
		private String msg;
		private String fromEmail;//发送人EMAIL
		private String fromName;//发送人姓名
		private EmailAttachment[] emailAttachments;//附件
		private File attachFile;//附件备用
		private Boolean useHtmlStyle;

		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getFromEmail() {
			return fromEmail;
		}
		public void setFromEmail(String fromEmail) {
			this.fromEmail = fromEmail;
		}
		public String getFromName() {
			return fromName;
		}
		public void setFromName(String fromName) {
			this.fromName = fromName;
		}
		public EmailAttachment[] getEmailAttachments() {
			return emailAttachments;
		}
		public void setEmailAttachments(EmailAttachment[] emailAttachments) {
			this.emailAttachments = emailAttachments;
		}
		public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
			this.emailAttachments = emailAttachments.toArray(new EmailAttachment[emailAttachments.size()] );
		}
		public File getAttachFile() {
			return attachFile;
		}
		public void setAttachFile(File attachFile) {
			this.attachFile = attachFile;
		}
		public Boolean getUseHtmlStyle() {
			if(useHtmlStyle==null){
				return false;
			}
			return useHtmlStyle;
		}
		public void setUseHtmlStyle(Boolean useHtmlStyle) {
			this.useHtmlStyle = useHtmlStyle;
		}
	}

	private String setHtmlStyle(){
		StringBuffer sb = new StringBuffer();
		sb.append("<style>");
		sb.append("table {");
		sb.append(" border-collapse: collapse; ");
		sb.append("} ");
		sb.append("table td {");
		sb.append("    border:1px solid ;");
		sb.append("}");
		sb.append("</style>");
		return sb.toString();
		
	}

	public static void abc(String[] args) throws IOException {
		EmailBean emailBean = new EmailBean();
		emailBean.setSubject("aaabbb");
		emailBean.setFromName("测试");//发信人显示名
		/*//附件1
		List<EmailAttachment> list = new ArrayList<EmailAttachment>();
		String attachPath ="http://172.26.18.106/pic/picture/09005/1412161417004724.jpg";
        EmailAttachment  attachment = null;
	       if(attachPath!=null){
	           attachment = new EmailAttachment();
	           try {
	               attachment.setURL(new URL(attachPath));  //附件地址
	               attachment.setName(MimeUtility.encodeText("abc.txt"));//附件显示名
	               attachment.setDisposition(EmailAttachment.ATTACHMENT);
	               attachment.setDescription("Picture of 王丙成"); //附件描述
	           }catch (UnsupportedEncodingException e) {
	               e.printStackTrace();
	           }
	       }
	       //附件2
	       String attachPath2 ="http://172.26.18.106/pic/picture/dingwancheng/1411071520468585.jpg";
	       EmailAttachment  attachment2 = null;
	       if(attachPath!=null){
	           attachment2 = new EmailAttachment();
	           try {
	               attachment2.setURL(new URL(attachPath2));
	               attachment2.setName(MimeUtility.encodeText("def.txt"));
	               attachment2.setDisposition(EmailAttachment.ATTACHMENT);
	               attachment2.setDescription("Picture of 丁晚成");
	           }catch (UnsupportedEncodingException e) {
	               e.printStackTrace();
	           }
	       }
	       list.add(attachment);
	       list.add(attachment2);
	       //EmailAttachment[] attaches = new EmailAttachment[]{attachment,attachment2};
	       emailBean.setEmailAttachments(list);*/
		//HTML内容
	      /* String strURL = "http://www.troila.com/" ;
	       URL url = new URL(strURL);
	       HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	       InputStreamReader input = new InputStreamReader(httpConn
	               .getInputStream(), "utf-8");
	       BufferedReader bufReader = new BufferedReader(input);
	       String line = "";
	       StringBuilder contentBuf = new StringBuilder();
	       while ((line = bufReader.readLine()) != null) {
	           contentBuf.append(line);
	       }  */

		//emailBean.setMsg(contentBuf.toString());
		StringBuffer sb = new StringBuffer();
		sb.append("<p><b>1<strike>.今天上语文</strike></b></p><p><i><br></i></p><pre class='lang-java' data-lang='java'>2.下午数</pre><pre class='lang-java' data-lang='java'>String s = 'HelloWorld';</pre><p>333</p>");
		emailBean.setMsg(sb.toString());

		//群发
		emailBean.setTo("suki_zhao@highrock.com.cn;");
		EmailUtils.getInstance().sendEmail(emailBean);
	}
}
