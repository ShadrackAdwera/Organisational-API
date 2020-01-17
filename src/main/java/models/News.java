package models;

import java.util.Objects;

public abstract class News {
    private String title;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(getTitle(), news.getTitle()) &&
                Objects.equals(getContent(), news.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getContent());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
