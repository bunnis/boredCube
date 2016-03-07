package testpizza;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


public class test2 {

	public static void main(String[] args) {
//		 System.out.println("Hello World");
//
//	        String tmp_html_content ="Öçasasa";
//
//	        InputStream is = new ByteArrayInputStream(tmp_html_content.getBytes());            
//	        org.jsoup.nodes.Document doc_tbl;
//	        try {
//	            doc_tbl = Jsoup.parse(is, "ISO-8859-9", "");
//	              ((org.jsoup.nodes.Document) doc_tbl).outputSettings().charset().forName("UTF-8");
//	                ((org.jsoup.nodes.Document) doc_tbl).outputSettings().escapeMode(EscapeMode.xhtml);
//	                String htmlString = doc_tbl.toString();
//	                System.out.println(htmlString);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//
//	        } 
		/////////////
//		  Document doc = null;
//
//		    try {
//		        doc = Jsoup.connect("http://www.latijnengrieks.com/vertaling.php?id=5368").get();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//		    Element firstH1 = doc.select("h1").first();
//
//		    System.out.println((firstH1 != null) ? firstH1.text() : "First <h1> not found.");
		
		//////////////////
//		String url = "http://www.latijnengrieks.com/vertaling.php?id=5368";
//		Document document;
//		try {
//			document = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
//		
//			
//			
//			
//			document.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.base); // default
//
//			document.outputSettings().charset("UTF-8");
//			
//			
//			Element paragraph = document.getElementsByAttributeValue("class","kader translation").first();
//			
//System.out.println(paragraph);
//
//			for (Node node : paragraph.childNodes()) {
//			    if (node instanceof TextNode) {
//			        System.out.println(((TextNode) node).text().trim());
//			    }
//			}
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//////////
		
//		 String check = "<title>Hexyl &#946;-D-glucopyranoside &#8805;98.0% (TLC) | &#8805; &#8805;</title>";
//		  Document doc3 = Jsoup.parse(check);
//	        doc3.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.base); // default
//
//	        doc3.outputSettings().charset("UTF-8");
//	        System.out.println("UTF-8: " + doc3.html());
//	        //doc3.outputSettings().charset("ISO 8859-1");
//	        doc3.outputSettings().charset("ASCII");
//	        System.out.println("ASCII: " + doc3.html());
		
		////////////////
		
		String url = "http://pt.yeaaaah.com/concert/28279/moonspell-em-almada";
		//File input  = new File("E:/000_OS MEUS DOCUMENTOS/pedro/Dropbox/projecto BoredCube/workspace_eclipse/Bored/Nova Pasta/test.htm");
		
		Document doc;
		try {
			
			InputStream is = new URL(url).openStream();
			 java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
			 String html = s.hasNext() ? s.next() : "";
			 
			 doc =Jsoup.parse(html, url);
			 doc.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.base);
			// System.out.println(html);
			System.out.println(doc);
			//doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
			
			//doc.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.base); // default
			//
					//doc.outputSettings().charset("UTF-8");
					//System.out.println(doc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Document doc = Jsoup.parse(input, "UTF-8");
		//Document doc = Jsoup.connect(url).get();
		//OutputSettings outp = doc.outputSettings();
	
//		
//		
	}

}
