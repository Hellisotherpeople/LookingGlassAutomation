 
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


public class RCN extends Launcher implements LauncherInterface {

	/**
	 *
	 * @param line
	 * @param folder
	 */
	@Override
	public void config(String line, String folder) {
		result = "fail";
		baseUrl = "http://lg.rcn.net/lg.cgi";
		super.configure(line, folder);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String launch() {
		try {
			Connection c = Jsoup.connect(baseUrl);
			c.method(Connection.Method.POST);
			c.data("query", "trace");
                        c.data("submit","Submit");
                        c.data("args", dstIP);
			c.data("router", locVar);
			c.data(".cgifields","query");
			c.data(".cgifields","router");

			c.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0");
			c.referrer(baseUrl);
			c.timeout(100000);
//			c.followRedirects(true);
//			c.header("Content-Type", "application/x-www-form-urlencoded");

			Document doc = c.post();
			System.out.println(doc);
			//System.out.println(doc);
			result = doc.getElementsByTag("pre").first().html();
			//System.out.println(result); // 
		} catch (IOException ex) {
			Logger.getLogger(RCN.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			return result;
		}
	}
}
