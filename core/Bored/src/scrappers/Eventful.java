package scrappers;

import java.io.IOException;
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

public class Eventful {


	private LinkedList<Evento> eventos;
	//portugal all events next 7days
	//http://eventful.com/events?q=*&ga_search=*&ga_type=events&t=Next+7+days&geo=country_id%3A173

	//portugal specific date
	//http://eventful.com/events?q=*&ga_search=*&ga_type=events&t=2014082500-2014082723&geo=country_id%3A173
	//YYYYMMDD00-YYYYMMDD00

	public Eventful(){
		eventos = new LinkedList<Evento>();

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//get current date time with Date()
		Date date = new Date();
		String[] data_completa = dateFormat.format(date).split("/");

		String start = ""+data_completa[0]+data_completa[1]+data_completa[2]+"00";//YYYYMMDD00
		String end = ""+data_completa[0]+data_completa[1]+(Integer.parseInt(data_completa[2])+2)+"00";//-YYYYMMDD00

		String url = "http://eventful.com/events?q=*&ga_search=*&ga_type=events&t=2014082500-2014082723&geo=country_id%3A173";
		//url = "http://eventful.com/events?q=*&ga_search=*&ga_type=events&t=2014082500-2014082723&geo=country_id%3A173&sort_order=Popularity&page_number=2";

		get2DayResults(url);
	}

	public void get2DayResults(String url){

		try {


			Document doc = Jsoup.connect(url).get();
			Document.OutputSettings settings = doc.outputSettings();
			settings.prettyPrint(true);
			settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
			settings.charset("utf-8");

			//grab concerts
			Elements results = doc.select("div#box-search-results-events");
			Elements questions = results.select("a[href]");
			Elements events = questions.select("a[href*=/events/]");

			Elements final_events = new Elements();

			for(int i=0;i<events.size();i++){
				if(!events.get(i).text().equals("") && !events.get(i).text().equals("read more")) {
					final_events.add(events.get(i));
				}
			}
			//System.out.println(concerts);
			//removeDuplicate(concerts);


			//grab each concert page and extract info
			for(Element link: final_events){

				String eventsURL = link.attr("abs:href");
				System.out.println(eventsURL);
				extractData(link.attr("abs:href"));

			}

			//next result page?
			if(!doc.getElementsByAttributeValueStarting("rel", "next").isEmpty()){//not empty means next page with results - parse
				get2DayResults(doc.getElementsByAttributeValueStarting("rel", "next").attr("abs:href"));
			}



			//System.out.println(concerts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void extractData(String link) throws IOException {
		//cats
		//Eventful.ad_data.cat = 'festivals_parades,performing_arts';
		//		animals
		//		art
		//		attractions
		//		business
		//		clubs_associations
		//		comedy
		//		community
		//		conference
		//		family_fun_kids
		//		family_fun_kidsbooks
		//		food
		//		foodholiday
		//		fundraisers
		//		holiday
		//		learning_education
		//		movies_film
		//		music
		//		other
		//		outdoors_recreation
		//		outdoors_recreationsports
		//		performing_arts
		//		politics_activism
		//		religion_spirituality
		//		sales
		//		schools_alumni
		//		science
		//		singles_social
		//		support
		//		technology

		Document doc = Jsoup.connect(link).get();
		Document.OutputSettings settings = doc.outputSettings();
		settings.prettyPrint(true);
		settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
		settings.charset("utf-8");

		//categorie code martelado
		Elements cat = doc.getElementsByTag("script");
		String categoria = "";
		String procurar = "Eventful.ad_data.cat";
		for(int i=0;i<cat.size();i++){
			if(cat.get(i).toString().contains(procurar)){
				int index = cat.get(i).toString().indexOf(procurar);

				//FIXME
				//get all string from index to ;
				int j=0;
				int interest_start = index+procurar.length()+3;// | = | sao 3chars
				while(cat.get(i).toString().charAt(interest_start+j) != ';'){
					categoria += cat.get(i).toString().charAt(interest_start+j);
					j++;
				}
			}

		}
		//

		Elements img = doc.getElementsByAttributeValue("property", "og:image");
		String imagemURL = img.attr("content");

		Elements event_details = doc.select("div");
		Elements test = doc.getElementsByClass("event-performers");
		String artistas = test.first().getElementsByAttributeValue("itemprop","performer").text(); //TODO maybe more artistas da merda?

		Elements localizacao = doc.getElementsByClass("event-venue");//TODO get gps coords from link of event venue
		String nome_sitio = localizacao.first().getElementsByAttributeValue("data-ga-label", "Venue Title Link").text();
		String localidade = localizacao.first().getElementsByAttributeValue("itemprop", "addressLocality").text();
		String regiao = localizacao.first().getElementsByAttributeValue("itemprop", "addressRegion").text();


		Elements data_hora_aux1 = doc.getElementsByClass("event-meta-details");
		Element data_hora_aux2 = data_hora_aux1.select("div.event-date").first();
		String data_hora_final = data_hora_aux2.attr("content");

		Evento e = new Evento("Concerto");
		//e.setMoradaActividade(location.text());
		eventos.add(e);
//TODO

	}
}
