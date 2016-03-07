package scrappers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import data.Evento;


public class Yeaaaah {

	private LinkedList<Evento> eventos;
	private LinkedList<Integer> ids;

	public Yeaaaah(){
		eventos = new LinkedList<Evento>();
		ids = new LinkedList<Integer>();


		try {

			String url = "http://pt.yeaaaah.com/pt/agenda-de-concertos";



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


			//grab each concert page and extract info
			for(Element link: concerts){

				String concertURL = link.attr("abs:href");
				//System.out.println(concertURL);
				extractData(link.attr("abs:href"));

			}

			//System.out.println(concerts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> void removeDuplicate(List <T> list) {
		HashSet <T> h = new HashSet<T>(list);
		list.clear();
		list.addAll(h);
	}

	//extract concert data
	private void extractData(String link) throws IOException {
		//Elements time = doc.getElementsByTag("time");
		//if time.size = 0 means festival/various days
		//or
		//Elements festival = doc.getElementsByClass("event_date_concerts_listing");
		//festival.isEmpty() = true //is not festival

		Document doc = Jsoup.connect(link).get();
		Document.OutputSettings settings = doc.outputSettings();
		settings.prettyPrint(true);
		settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
		settings.charset("utf-8");


		//extract id
		//String Href = link.attr("href"); 
		String[] splitted = link.split("/"); //0 = httpk, 1 = blank, 2= pt.yeaaah.com , 3 = concert , 4 = id,  5 = description
		int id = Integer.parseInt(splitted[4]);

		Elements festival = doc.getElementsByClass("event_date_concerts_listing");
		boolean isFestival = !festival.isEmpty(); //if festival.isEmpty = true is not festival

		if(!isFestival && !ids.contains(id)){//concerto musica
			ids.add(id);

			//if time.size = 0 means festival/various days
			Elements time = doc.getElementsByTag("time");

			//can have various artists
			Elements artists = doc.getElementsByAttributeValueStarting("href", "/artist/");

			Elements location = doc.getElementsByAttributeValue("itemprop", "location");

			Evento e = new Evento("Concerto");
			e.setMoradaActividade(location.text());
			
			eventos.add(e);
		}
		else{//Festival
			//TODO add festival parsing
		}
	}

	public LinkedList<Evento> getEventList() {
		return eventos;
	}

}
