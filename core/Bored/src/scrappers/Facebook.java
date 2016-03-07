package scrappers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.jsoup.*;
import org.jsoup.nodes.Document;

public class Facebook {

	public Facebook(){
		  String login = "secret.user@gmail.com";
	        String password = "veryHardPassword";
	        String URL = "https://www.facebook.com/events/";
	        String loginURL = "https://www.facebook.com/login.php?login_attempt=1";
	        String useragent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0";
	        String referrer = "http://www.google.com";
//		
//		final WebClient webClient = new WebClient();
//		HtmlPage page1 = webClient.getPage(URLEvent + eventFBId);
//		final HtmlForm form = page1.getForms().get(0);
//		webClient.setJavaScriptTimeout(45000);
//
//		final HtmlTextInput textField = form.getInputByName("email");
//		textField.setValueAttribute(login);
//		final HtmlPasswordInput textField2 = form.getInputByName("pass");
//		textField2.setValueAttribute(password);
//		page1 = (HtmlPage) form.getInputByValue("Log In").click();
//
//		URL url = new URL(URLEvent + eventFBId);
//		Set<Cookie> set = webClient.getCookies(url);
//
//		Map<String, String> mapFromSet = new HashMap<String, String>();
//		   for (Cookie entry : set) {
//		       mapFromSet.put(entry.getName(), entry.getValue());
//		   }
//
//		Map<String, String> cookies = mapFromSet;
//		    for (Map.Entry<String, String> entry : cookies.entrySet()) {
//		        System.out.println(entry.getKey() + " : " + entry.getValue());
//		    }
//
//		Document doc = Jsoup.connect(URLEvent + eventFBId)
//		                    .userAgent(useragent)
//		                    .timeout(200000)
//		                    .data("email", login)           
//		                    .data("pass", password)
//		                    .cookies(cookies).get();
//
//		System.out.println(doc);
	}
	
}
