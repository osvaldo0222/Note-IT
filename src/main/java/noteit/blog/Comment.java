package noteit.blog;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Type(type = "text")
    private String comment;
    @OneToOne
    private User author;
    @ManyToOne
    private Article article;
    @OneToMany
    private List<PubLike> likeList;

    public Comment() { }

    public Comment(String comment, User author, Article article) {
        this.comment = comment;
        this.author = author;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) { this.author = author; }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<PubLike> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<PubLike> likeList) {
        this.likeList = likeList;
    }
}
