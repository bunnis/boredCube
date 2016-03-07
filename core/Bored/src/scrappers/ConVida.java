package scrappers;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import data.Evento;

public class ConVida {

	private LinkedList<Evento> eventos;
	public ConVida(){
		eventos = new LinkedList<Evento>();


		try {

			String url = "http://lisboa.convida.pt/";



			//			//http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
			//			InputStream is = new URL(url).openStream();
			//			Scanner s = new Scanner(is);
			//			s.useDelimiter("\\A");
			//
			//			String html = s.hasNext() ? s.next() : "";
			//
			//			Document doc =Jsoup.parse(html, url);



			Document doc = Jsoup.connect(url).get();
			Document.OutputSettings settings = doc.outputSettings();
			settings.prettyPrint(true);
			settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
			settings.charset("utf-8");

			//grab concerts
			Elements questions = doc.select("a[href]");
			Elements concerts = questions.select("a[href^=/concert/]");
			//System.out.println(concerts);
			//removeDuplicate(concerts);


		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
