import java.util.LinkedList;

import scrappers.AllEvents;
import scrappers.ConVida;
import scrappers.Eventful;
import scrappers.Facebook;
import scrappers.FourSquare;
import scrappers.MyGon;
import scrappers.Yeaaaah;
import utils.DB;
import data.Evento;

public class Main {
	
	public static void main(String[] args) {
		
		DB db = new DB();
		
//		FourSquare fq = new FourSquare();
//		
		Yeaaaah yh = new Yeaaaah();
		
		db.populateWith(yh);
//		LinkedList<Evento> eventos = yh.getEventList();
		
		//Eventful etf = new Eventful();
		//MyGon mygon = new MyGon();
		
		//ConVida convida = new ConVida();
		//Facebook fb = new Facebook();
		
		//AllEvents alleve = new AllEvents();
	}
}
