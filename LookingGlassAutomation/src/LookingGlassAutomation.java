/**
 * Created by lain on 7/7/16.
 */
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class LookingGlassAutomation {
    public static void main(String[] args) {
        try {
            String sitename = "bear1.xrs2"; // use tamperdata to get sitenames from post query
            String address = "8.8.8.8"; // IP address that one will traceroute to

            Document doc = Jsoup.connect("http://lookingglass.level3.net/traceroute/lg_tr_output.php").method(Connection.Method.POST
            ).userAgent("Mozilla").data("sitename", sitename).data("address", address).timeout(100000).post(); // without a long timeout it throws an exception
            //System.out.println(doc); // prints the HTML that the page is made from


            String input = doc.getElementsByTag("pre").html();
            String stripped = "Results of Level3 LookingGlass:" + "\n" + "\n" + "\n";
            for (String result : input.split("\n")){
                stripped += (Jsoup.parse(result).text() + "\n");
                //System.out.println(stripped);
            }
            //System.out.println(stripped);
            PrintWriter writer = new PrintWriter("Level3Results", "UTF-8");
            writer.println(stripped);
            writer.close();





        }
        catch (IOException x) {
            System.out.println("Uh oh, spaghettios!" + x);
        }
    }
}
