package rssParser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssParser {
    final static String cnbcLink = "https://www.cnbc.com/id/10001147/device/rss/rss.html";
    private static String news;

    public String getNews(){
        return this.news;
    }

    public  RssParser ()throws Exception{

      /*  URL oracle = new URL(cnbcLink);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        FileWriter fw = new FileWriter("test.xml");

        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            fw.append(inputLine);
        }
        in.close();
        */
        final URL website = new URL(cnbcLink);
        final Path target = Paths.get("test.xml");
        try (InputStream in = website.openStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();

        news="";
        parser.parse(new File("test.xml"), handler);
        news+=handler.getPubDate()+"\n"+
                handler.getTitle()+"\n"+
                handler.getDescription()+"\n"+
                handler.getLink()
        ;




    }

}

