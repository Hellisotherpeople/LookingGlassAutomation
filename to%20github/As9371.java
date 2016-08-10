/*
//
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
 * @author Allen
 */
public class As9371 extends Launcher implements LauncherInterface {

	/**
	 *
	 * @param line
	 * @param folder
	 */
	@Override
	public void config(String line, String folder) {
		result = "fail";
		baseUrl = "http://as9371.bgp4.jp/lg.cgi?query=34&arg=";
		super.configure(line, folder);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String launch() {
		try {
			Document doc = Jsoup.connect(baseUrl + dstIP).timeout(100000).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0").get();
			//System.out.println(doc);
			result = doc.getElementsByTag("pre").last().html();
			System.out.println(result);
			//System.out.println(result);
		} catch (IOException ex) {
			Logger.getLogger(As9371.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			return result;
		}
	}
}
