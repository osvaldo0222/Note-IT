package noteit.blog;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class LikeArticle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User userLike;
    @ManyToOne
    private Article article;

    public LikeArticle() {}

    public LikeArticle(User userLike, Article article) {
        this.userLike = userLike;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserLike() {
        return userLike;
    }

    public void setUserLike(User userLike) {
        this.userLike = userLike;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
