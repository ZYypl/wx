package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 22:26
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {


    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<>();

    public NewsMessage() {
    }

    public NewsMessage(String articleCount, List<Article> articles) {
        this.articleCount = articleCount;
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> map,  List<Article> articles) {
        super(map);
        this.setMsgType("news");
        this.articleCount = articles.size()+"";
        this.articles = articles;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
