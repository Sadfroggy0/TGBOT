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

    public  RssParser (String url)throws Exception {

            URL website = new URL(url);
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

