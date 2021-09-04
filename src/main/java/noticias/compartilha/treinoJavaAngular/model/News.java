package noticias.compartilha.treinoJavaAngular.model;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    private long id;
    private String title;
    private String name;
    private String date;
    private String news;

    public News(){

    }

    public News(long id, String title, String name, String date, String news){
        this.id = id;
        this.title = title;
        this.name = name;
        this.date = date;
        this.news = news;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "news", nullable = false)
    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "News [" + "id=" + id + ", title=" + title + ", name=" + name +
                ", date='" + date + ", news='" + news + ']';
    }
}
