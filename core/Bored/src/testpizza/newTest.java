package testpizza;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
public class newTest {

	public static void main(String[] args) throws IOException {
		//Document doc = Jsoup.parse("" +"<p>THIS &mdash; IS A &ldquo;TEST&rdquo;. 5 &gt; 4. trademark: &#153;</p>");

		
		String url = "http://pt.yeaaaah.com/pt/agenda-de-concertos";
		//http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
		InputStream is = new URL(url).openStream();
		Scanner s = new Scanner(is);
		s.useDelimiter("\\A");

		String html = s.hasNext() ? s.next() : "";

		Document doc =Jsoup.parse(html, url);
		
		    Document.OutputSettings settings = doc.outputSettings();

		    settings.prettyPrint(true);
		    settings.escapeMode(EscapeMode.xhtml);
		    settings.charset("utf-8");

		    String modifiedFileHtmlStr = doc.html();

		    System.out.println(modifiedFileHtmlStr);

	}

}
