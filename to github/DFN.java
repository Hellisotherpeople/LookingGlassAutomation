// TODO: Try to see if one can strip out the unneeded [AS 15169] stuff it adds to the results. 
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


public class DFN extends Launcher implements LauncherInterface {

	/**
	 *
	 * @param line
	 * @param folder
	 */
	@Override
	public void config(String line, String folder) {
		result = "fail";
		baseUrl = "https://www.noc.dfn.de/lg/";
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
                        c.data("protocol","IPv4");
                        c.data("addr", dstIP);
			c.data("router", locVar);

			c.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0");
			c.referrer(baseUrl);
			c.timeout(100000);
//			c.followRedirects(true);
//			c.header("Content-Type", "application/x-www-form-urlencoded");

			Document doc = c.post();
			//System.out.println(doc);
			result = doc.getElementsByTag("pre").first().html();
			//System.out.println(result); // 
		} catch (IOException ex) {
			Logger.getLogger(DFN.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			return result;
		}
	}
}
