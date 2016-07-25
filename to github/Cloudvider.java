// DOES NOT WORK (can't find pre tags in the document to scrape from) 
package automation;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author motamedi
 */
public class Cloudvider extends Launcher implements LauncherInterface {

	private String cssSelector = "html body pre font";
//	private String cssSelector = "div.section:nth-child(3)";

	/**
	 *
	 * @param line
	 * @param folder
	 */
	@Override
	public void config(String line, String folder) {
		result = "fail";
		baseUrl = "http://lg.clouvider.net/index.cgi";
		super.configure(line, folder);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String launch() {
		
		
		
		try {
			
			Document doc = Jsoup.connect(baseUrl).method(Connection.Method.POST).userAgent("Mozilla").data("router", locVar).data("pass1", "").data("query", "7").data("arg", dstIP).timeout(100000).post();
			//System.out.println(doc); 

			result = (" " + doc.getElementsByTag("pre").first().html());

			 //System.out.println(result);

			


			//Document doc = c.post();
			//result = doc.getElementsByTag("pre").first().html();

		} catch (IOException ex) {
			Logger.getLogger(Level3.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			System.err.println(result);
			return result;
		}
	}
}

