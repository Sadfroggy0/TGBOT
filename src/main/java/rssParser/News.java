package rssParser;

import java.util.ArrayList;

public class News {
    final static String cnbcLink = "https://www.cnbc.com/id/10001147/device/rss/rss.html";
    public static ArrayList<News> news = new ArrayList<>();

    private String pubDate;
    private String title;
    private String description;

    public News(String pubDate, String title, String description, String link) {
        this.pubDate = pubDate;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    private String link;
}
