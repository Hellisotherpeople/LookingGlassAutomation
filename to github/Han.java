/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Han extends Launcher implements LauncherInterface {

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
		baseUrl = "http://www.han.de/cgi-bin/nph-trace.cgi";
		super.configure(line, folder);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String launch() {
		
		
		
		try {
			
			
			
			Document doc = Jsoup.connect(baseUrl).method(Connection.Method.POST).userAgent("Mozilla").data("addr", dstIP).data("start", "Start").data("method", "traceroute").timeout(100000).post();
			//System.out.println(doc); 

			result = doc.getElementsByTag("pre").first().html();

			 //System.out.println(result);

			


			//Document doc = c.post();
			//result = doc.getElementsByTag("pre").first().html();

		} catch (IOException ex) {
			Logger.getLogger(Han.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			System.err.println(result);
			return result;
		}
	}
}







