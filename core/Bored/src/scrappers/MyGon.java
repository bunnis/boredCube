package scrappers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;



import data.Evento;

public class MyGon {
	private LinkedList<Evento> eventos;
	
	//https://pt.mygon.com/#!guia/todas-as-categorias/portugal/hoje/qualquer-hora
	//https://pt.mygon.com/#!guia/todas-as-categorias/portugal/amanha/qualquer-hora
	
	public MyGon(){
		eventos = new LinkedList<Evento>();

		
//https://en.mygon.com/MGMDW/REST/web/client/shops/getShops?startIndex=0&pageSize=30&hourInterval=0&onlyPromotions=false&categoryId=0&day=5&searchWords=sushi&languageCode=en_EN&originMygon=true&capital=portugal%2C+portugal&_=1410373067855
		String url = "https://pt.mygon.com/#!guia/todas-as-categorias/portugal/hoje/qualquer-hora";
		//url = "http://eventful.com/events?q=*&ga_search=*&ga_type=events&t=2014082500-2014082723&geo=country_id%3A173&sort_order=Popularity&page_number=2";

		
	
		
		
		
		
//		try {
//
//
//			//			//http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
//			//			InputStream is = new URL(url).openStream();
//			//			Scanner s = new Scanner(is);
//			//			s.useDelimiter("\\A");
//			//
//			//			String html = s.hasNext() ? s.next() : "";
//			//
//			//			Document doc =Jsoup.parse(html, url);
//
//
//			
//			Document doc2 = Jsoup.connect(url).data("nav").post();
//			
//
//			Document doc = Jsoup.connect(url).get();
//			Document.OutputSettings settings = doc.outputSettings();
//			settings.prettyPrint(true);
//			settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
//			settings.charset("utf-8");
//
//			//grab concerts
//			Elements questions = doc.select("a[href]");
//			Elements concerts = questions.select("a[href^=/#!campanha/]");
//			
//			//System.out.println(concerts);
//			//removeDuplicate(concerts);
//
//
//			//grab each concert page and extract info
//			for(Element link: concerts){
//
//				String concertURL = link.attr("abs:href");
//				//System.out.println(concertURL);
//				///extractData(link.attr("abs:href"));
//
//			}
//
//			//System.out.println(concerts);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
}
