package scrappers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

public class AllEvents {

//NOT RELIABLE ON CATEGORY CLASSIFICATION
	public AllEvents(){

		String url = "http://allevents.in/lisbon/today";
		//http://allevents.in/lisbon/today#
		//"http://allevents.in/lisbon/all";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();

			Document.OutputSettings settings = doc.outputSettings();
			settings.prettyPrint(true);
			settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
			settings.charset("utf-8");



			//city list <div class="city-list">
			Elements city_list = doc.select(".city-list");

			//grab concerts
			Elements questions = doc.select("a[href]");
			Elements concerts = questions.select("[property=v:url]");

			//grab all cities and links
			//TODO
			for(Element link: city_list){

			}

			//System.out.println(concerts);
			//System.out.println(concerts.size());
			//removeDuplicate(concerts);

			//grab each concert page and extract info
			for(Element link: concerts){

				String concertURL = link.attr("abs:href");
				//System.out.println(concertURL);
				extractData(concertURL);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//extract concert data
	private void extractData(String link) throws IOException {

		Document doc = Jsoup.connect(link).get();
		Document.OutputSettings settings = doc.outputSettings();
		settings.prettyPrint(true);
		settings.escapeMode(EscapeMode.xhtml);//para imprimir os acentos
		settings.charset("utf-8");

		Elements description = doc.getElementsByAttributeValue("property", "v:description");
		//String descriptionTXT = description.attr("");
		Elements image = doc.getElementsByAttributeValue("rel", "v:photo");
		String imagemURL = image.attr("content");

		Elements startD = doc.getElementsByAttributeValue("property", "v:startDate");
		Elements endD = doc.getElementsByAttributeValue("property", "v:endDate");

		Elements nome = doc.getElementsByAttributeValue("property", "v:summary");
		Elements morada = doc.getElementsByAttributeValue("property", "v:street-address");
		Elements regiao = doc.getElementsByAttributeValue("property", "v:region");
		Elements localidade = doc.getElementsByAttributeValue("property", "v:locality");
		
		Elements latitude = doc.getElementsByAttributeValue("property", "v:latitude");
		Elements longitude = doc.getElementsByAttributeValue("property", " v:longitude");
		
		Elements Venue = doc.getElementsByClass("toh");
		
		//			v:latitude v:longitude
		//			<span rel="v:location">
		//			<span typeof="v:Organization">
		// 			<span property="v:name"  content=""></span>
		//			<span rel="v:address">
		//			<span typeof="v:Address">
		//			<span property="v:street-address"  content="Praça de Espanha"></span>
		//			<span property="v:locality"  content="Lisbon"></span>
		//			<span property="v:region"  content=""></span>
		//			property="v:startDate" content="2014-09-21T22:44:49Z">
		//			property="v:endDate" content="2014-09-27T21:30:00Z"> 
		//			v:summary - nome
		//			v:photo

	}

}
