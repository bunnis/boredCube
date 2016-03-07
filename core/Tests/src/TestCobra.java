import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.parser.DocumentBuilderImpl;
import org.lobobrowser.html.parser.HtmlParser;
import org.lobobrowser.html.parser.InputSourceImpl;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.lobobrowser.html.test.SimpleUserAgentContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html2.HTMLElement;

import java.net.URL;
import java.net.URLConnection;
import java.awt.*;
import java.util.logging.*;

import org.xml.sax.InputSource;
public class TestCobra {
	public static void main(String[] args) throws Exception {


		//String uri = "https://pt.mygon.com/#!guia/todas-as-categorias/portugal/hoje/qualquer-hora";;


//		// Disable most Cobra logging.
//		Logger.getLogger("org.lobobrowser").setLevel(Level.WARNING);
//		UserAgentContext uacontext = new SimpleUserAgentContext();
//		// In this case we will use a standard XML document
//		// as opposed to Cobra's HTML DOM implementation.
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		URL url = new URL(uri);
//		InputStream in = url.openConnection().getInputStream();
//		try {
//			Reader reader = new InputStreamReader(in, "ISO-8859-1");
//			Document document = builder.newDocument();
//			// Here is where we use Cobra's HTML parser.            
//			HtmlParser parser = new HtmlParser(uacontext, document);
//			parser.parse(reader);
//			
//			
//			// Now we use XPath to locate "a" elements that are
//			// descendents of any "html" element.
//			XPath xpath = XPathFactory.newInstance().newXPath();
//			NodeList nodeList = (NodeList) xpath.evaluate("html//a", document, XPathConstants.NODESET);
//			int length = nodeList.getLength();
//			for(int i = 0; i < length; i++) {
//				Element element = (Element) nodeList.item(i);
//				System.out.println("## Anchor # " + i + ": " + element.getAttribute("href"));
//			}
//		} finally {
//			in.close();
//		}








				// Initialize logging so only Cobra warnings are seen.
				Logger.getLogger("org.lobobrowser")
					.setLevel(Level.SEVERE);
		
				// Open a connection on the URL we want to render first.
				String uri = "https://pt.mygon.com/#!guia/todas-as-categorias/portugal/hoje/qualquer-hora";
				URL url = new URL(uri);
				URLConnection connection = url.openConnection();
				InputStream in = connection.getInputStream();
		
				// A Reader should be created with the correct charset,
				// which may be obtained from the Content-Type header
				// of an HTTP response.
				Reader reader = new InputStreamReader(in);
		
				// InputSourceImpl constructor with URI recommended
				// so the renderer can resolve page component URLs.
				InputSource is = new InputSourceImpl(reader, uri);
				HtmlPanel htmlPanel = new HtmlPanel();
				UserAgentContext ucontext = new LocalUserAgentContext();
				HtmlRendererContext rendererContext = 
					new LocalHtmlRendererContext(htmlPanel, ucontext);
				
				// Set a preferred width for the HtmlPanel,
				// which will allow getPreferredSize() to
				// be calculated according to block content.
				// We do this here to illustrate the 
				// feature, but is generally not
				// recommended for performance reasons.
				htmlPanel.setPreferredWidth(800);
				
				// Note: This example does not perform incremental
				// rendering while loading the initial document.
				DocumentBuilderImpl builder = 
					new DocumentBuilderImpl(
						rendererContext.getUserAgentContext(), 
						rendererContext);
				
				Document document = builder.parse(is);
				in.close();
		
				System.out.println(document.getTextContent());
				
				// Set the document in the HtmlPanel. This method
				// schedules the document to be rendered in the
				// GUI thread.
				htmlPanel.setDocument(document, rendererContext);
		
				// Create a JFrame and add the HtmlPanel to it.
				final JFrame frame = new JFrame();
				frame.getContentPane().add(htmlPanel);
				
				// We pack the JFrame to demonstrate the
				// validity of HtmlPanel's preferred size.
				// Normally you would want to set a specific
				// JFrame size instead.
				
				// pack() should be called in the GUI dispatch
				// thread since the document is scheduled to
				// be rendered in that thread, and is required
				// for the preferred size determination.
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						frame.pack();
						frame.setVisible(true);
					}
				});
	}

	private static class LocalUserAgentContext 
	extends SimpleUserAgentContext {
		// Override methods from SimpleUserAgentContext to
		// provide more accurate information about application.

		public LocalUserAgentContext() {
		}

		public String getAppMinorVersion() {
			return "0";
		}

		public String getAppName() {
			return "BarebonesTest";
		}

		public String getAppVersion() {
			return "1";
		}

		public String getUserAgent() {
			return "Mozilla/4.0 (compatible;) CobraTest/1.0";
		}
	}

	private static class LocalHtmlRendererContext 
	extends SimpleHtmlRendererContext {
		// Override methods from SimpleHtmlRendererContext 
		// to provide browser functionality to the renderer.

		public LocalHtmlRendererContext(HtmlPanel contextComponent, 
				UserAgentContext ucontext) {
			super(contextComponent, ucontext);
		}

		public void linkClicked(HTMLElement linkNode, 
				URL url, String target) {
			super.linkClicked(linkNode, url, target);
			// This may be removed: 
			System.out.println("## Link clicked: " + linkNode);
		}

		public HtmlRendererContext open(URL url, 
				String windowName, String windowFeatures, 
				boolean replace) {
			// This is called on window.open().
			HtmlPanel newPanel = new HtmlPanel();
			JFrame frame = new JFrame();
			frame.setSize(600, 400);
			frame.getContentPane().add(newPanel);
			HtmlRendererContext newCtx = new LocalHtmlRendererContext(newPanel, this.getUserAgentContext());
			newCtx.navigate(url, "_this");
			return newCtx;
		}
	}
}
