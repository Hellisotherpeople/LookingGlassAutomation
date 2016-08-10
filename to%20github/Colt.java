/*
 * Fails due to some SSL shenanigians 
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
public class Colt extends Launcher implements LauncherInterface {

	/**
	 *
	 * @param line
	 * @param folder
	 */
	@Override
	public void config(String line, String folder) {
		result = "fail";
		baseUrl = "https://portal.colt.net/lg/private/lookingGlassExec.do?action=doExecCommand&";
		super.configure(line, folder);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String launch() {
		try {
			Document doc = Jsoup.connect(baseUrl + "cityId=" + locVar + "&commandId=TracertV4&ipAddress=" + dstIP).timeout(100000).referrer(baseUrl).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0").get();


			result = doc.getElementsByTag("pre").last().html();
			System.out.println(result);
		} catch (IOException ex) {
			Logger.getLogger(Colt.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			return result;
		}
	}
}
