package testpizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.*;

public class JSONTest {
	//	public static void main(String[] args) throws Exception {
	//		
	//		JSONObject obj = new JSONObject(" .... ");
	//		String pageName = obj.getJSONObject("pageInfo").getString("pageName");
	//
	//		JSONArray arr = obj.getJSONArray("posts");
	//		for (int i = 0; i < arr.length(); i++)
	//		{
	//		    String post_id = arr.getJSONObject(i).getString("post_id");
	//		    ......
	//		}
	//		
	//	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		//JSONObject json = readJsonFromUrl("https://graph.facebook.com/19292868552");
		JSONObject json = readJsonFromUrl("https://pt.mygon.com/MGMDW/REST/web/client/shops/getShops?startIndex=0&pageSize=30&hourInterval=0&onlyPromotions=false&categoryId=0&day=5&searchWords=&languageCode=en_EN&originMygon=true&capital=portugal%2C+portugal&_=1410373067855");
		//System.out.println(json.toString());
		System.out.println(json.get("data"));
	}


}
