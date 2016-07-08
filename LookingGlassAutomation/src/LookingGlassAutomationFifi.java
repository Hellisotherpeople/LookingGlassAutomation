//TODO: talk to GTF's about this project and the other one to see what I need to do
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class LookingGlassAutomationFifi {
    public static void main(String[] args) {
        try {
            String traceroute = "8.8.8.8";
            String tracescount = "&nprobes=1";
            String resolvehostnames = "&resolved=yes";
            Document doc = Jsoup.connect("http://www.fifi.org/services/traceroute?hostname=" + traceroute + tracescount + resolvehostnames + "&submit=Traceroute").get();
            Elements table = doc.getElementsByTag("tbody").last().getElementsByTag("tr"); // gets the html code for table elements
            //System.out.println(table);

            String result = ("Results of Fifi LookingGlass:" + "\n" + "\n" + "\n"); // where I will store the data recieved
            boolean isfirstelement = true; // have to skip first row of the table
            for (Element rows : table) { // get the table elements
                if (isfirstelement == false) {
                    //System.out.println(rows);
                    Elements columns = rows.getElementsByTag("td"); // each column uses the td tag
                    //System.out.plsrintln(columns);
                    int count = 0;
                    for (Element column : columns) {
                        //System.out.println(column);


                        if ((count == 0 || 3 < count)) {
                            result += (column.text() + " ");
                            //System.out.println(result);
                        }
                        //System.out.println(result);
                        count++;

                    }
                    result += ("\n");


                }
                isfirstelement = false;

            }
            PrintWriter writer = new PrintWriter("FifiResults", "UTF-8");
            writer.println(result);
            writer.close();
            //System.out.println(result); // final print
        }
        catch (IOException x) {
            System.out.println("Uh oh, spaghettios!");
        }
    }
}
