package model;

import entity.Article;
import util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleModel {

    public void insertDB(Article article) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            if (cnn == null) {
                System.err.println("Can't connect to database");
                return;
            }
            String sql = "insert into articles (title, description, pubDate, link, guid) values (?, ?, ?, ?, ?)";
            PreparedStatement pp = cnn.prepareStatement(sql);
            pp.setString(1, article.getTitle());
            pp.setString(2, article.getDescription());
            pp.setString(3, article.getPubDate());
            pp.setString(4, article.getLink());
            pp.setString(5, article.getGuid());
            pp.execute();
            System.out.println(article.toString());
            System.out.println("Success!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Fail!");
        }
    }

}