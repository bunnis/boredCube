package testpizza;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class TestTuga {

	public static void main(String[] args) {

		String url = "http://pt.yeaaaah.com/concert/28279/moonspell-em-almada";
		Connection connectionTest = Jsoup.connect(url)
				.cookie("cookiereference", "cookievalue")
				.method(Method.GET);
		
		Document response;
		try {
			response = Jsoup.parse(new String(connectionTest.execute().bodyAsBytes(),"ISO-8859-15"));
			System.out.println(response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
