package testpizza;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Entities.EscapeMode;


public class TestQUeDeviaDar {
	public static void main(String []args){
        System.out.println("Hello World");

        String tmp_html_content ="Öçasasa";

        InputStream is = new ByteArrayInputStream(tmp_html_content.getBytes());            
        org.jsoup.nodes.Document doc_tbl;
        try {
            doc_tbl = Jsoup.parse(is, "ISO-8859-9", "");
              ((org.jsoup.nodes.Document) doc_tbl).outputSettings().charset().forName("UTF-8");
                ((org.jsoup.nodes.Document) doc_tbl).outputSettings().escapeMode(EscapeMode.xhtml);
                String htmlString = doc_tbl.toString();
                System.out.println(htmlString);
        } catch (IOException e) {
            e.printStackTrace();

        } 

     }
}
