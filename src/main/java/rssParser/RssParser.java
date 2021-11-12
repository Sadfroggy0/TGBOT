package rssParser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssParser {



    public  RssParser ()throws Exception {
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
        URL website = null;
        for (int i = 0; i < News.cnbcLinks.length; i++) {
            website = new URL(News.cnbcLinks[i]);

            final Path target = Paths.get("test.xml");
            try (InputStream in = website.openStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            CNBCxmlHandler handler = new CNBCxmlHandler();
            parser.parse(new File("test.xml"), handler);

        }
    }

}

