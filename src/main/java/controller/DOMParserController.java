package controller;

import entity.Article;
import model.ArticleModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class DOMParserController {

    public static final String URL_TAG = "https://vnexpress.net/rss/tam-su.rss";
    public static final String TAG_NAME = "item";


    public static void main(String[] args) {
        ArrayList<Article> list = readXML();
        ArticleModel articleModel = new ArticleModel();
        if (list != null){
            for (Article article: list){
                articleModel.insertDB(article);
            }
        }
    }

    public static ArrayList<Article> readXML() {
        ArrayList<Article> list = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(URL_TAG);
            Element element = document.getDocumentElement();
            NodeList listItem = element.getElementsByTagName(TAG_NAME);
            for (int i = 0; i < listItem.getLength(); i++) {
                Article article = new Article();
                Node itemNode = listItem.item(i);
//                System.out.println(itemNode.getNodeName());
                NodeList itemChildNodeList = itemNode.getChildNodes();
                for (int j = 0; j < itemChildNodeList.getLength(); j++) {
                    Node childNode = itemChildNodeList.item(j);
                    if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    switch (childNode.getNodeName()) {
                        case Article.TITLE_TAG:
                            article.setTitle(childNode.getTextContent());
                            break;
                        case Article.DESCRIPTION_TAG:
                            article.setDescription(childNode.getTextContent());
                            break;
                        case Article.PUBDATE_TAG:
                            article.setPubDate(childNode.getTextContent());
                            break;
                        case Article.LINK_TAG:
                            article.setLink(childNode.getTextContent());
                            break;
                        case Article.GUID_TAG:
                            article.setGuid(childNode.getTextContent());
                            break;
                    }
                }
                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
