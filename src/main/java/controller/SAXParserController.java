package controller;

import entity.Article;
import model.ArticleModel;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class SAXParserController {
    public static final String URL_TAG = "https://vnexpress.net/rss/tam-su.rss";

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            CustomerHandler customerHandler = new CustomerHandler();
            saxParser.parse(URL_TAG, customerHandler);
            ArrayList<Article> arrayList = customerHandler.getArticleList();
            for (Article currentItem :
                    arrayList) {
                ArticleModel articleModel = new ArticleModel();
                articleModel.insertDB(currentItem);
                System.out.println(currentItem.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
