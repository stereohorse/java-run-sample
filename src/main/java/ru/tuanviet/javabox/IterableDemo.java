package ru.tuanviet.javabox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

public class IterableDemo {

    public static void main(String[] args) {
        for (HackerNewsArticle hnArticle : HackerNewsReader.takeFirst(3)) {
            System.out.println(hnArticle.getTitle());
        }
    }
}

class HackerNewsReader implements Iterable<HackerNewsArticle> {

    private final int articlesCount;

    HackerNewsReader(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public static HackerNewsReader takeFirst(int articlesCount) {
        return new HackerNewsReader(articlesCount);
    }

    @Override
    public Iterator<HackerNewsArticle> iterator() {
        return new HackerNewsIterator(articlesCount);
    }
}

class HackerNewsIterator implements Iterator<HackerNewsArticle> {
    private final int articlesCount;
    private final HttpClient httpClient = new HttpClient();

    private List<Long> articlesIds;
    private int currentItemIndex;

    public HackerNewsIterator(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    @Override
    public boolean hasNext() {
        if (articlesIds == null) {
            articlesIds = httpClient.fetch(
                "https://hacker-news.firebaseio.com/v0/beststories.json",
                new TypeReference<List<Long>>() {
                }
            );
        }

        return currentItemIndex < articlesIds.size() && currentItemIndex < articlesCount;
    }

    @Override
    public HackerNewsArticle next() {
        HackerNewsArticle article = httpClient.fetch(
            format("https://hacker-news.firebaseio.com/v0/item/%s.json", articlesIds.get(currentItemIndex)),
            HackerNewsArticle.class
        );
        currentItemIndex++;

        return article;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class HackerNewsArticle {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
