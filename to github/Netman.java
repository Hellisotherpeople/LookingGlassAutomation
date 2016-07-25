/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 *
 * @author Allen
 */
public class Netman extends Launcher implements LauncherInterface {

	/**
	 *
	 * @param line
	 * @param folder
	 */
	@Override
	public void config(String line, String folder) {
		result = "fail";
		baseUrl = "https://netman.cac.washington.edu/lookingglass/";
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
			c.data("node", locVar);
                        c.data("command", "traceroute");
			c.data("parameter", dstIP);
                        c.data("vrf", "hp");
                        c.data("execute command", "execute command");

			c.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0");
			c.referrer(baseUrl);
			c.timeout(100000);
//			c.followRedirects(true);
//			c.header("Content-Type", "application/x-www-form-urlencoded");

			Document doc = c.post();
			
			String toParse = doc.getElementsByTag("pre").html();
			
			//additional parsing logic to omit unneeded information 
			
			String[] lines = StringUtils.split(toParse, "\r\n");
			int counter = 0; 
			String answer = ""; // without this it appends "fail" to the beginning of the result string 
			for(String s : lines ){
				if (counter > 1) {
					//System.out.println(s);
					answer += (s + "\n"); 
				}
				result = (answer);
				counter++;

			}
			System.out.println(result);

			/*
			result = doc.getElementsByTag("pre").html();
			System.out.println(result);
			*/ 
		} catch (IOException ex) {
			Logger.getLogger(Netman.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(result);
		} finally {
			super.store();
			return result;
		}
	}
}
