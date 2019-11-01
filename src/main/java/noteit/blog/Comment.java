package noteit.blog;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
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
    @OneToMany(orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
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

    public PubLike getUserLike(String username){
        PubLike like = null;
        for (PubLike likeArticle : likeList) {
            if (likeArticle.getUserLike().getUsername().equalsIgnoreCase(username)){
                like = likeArticle;
                break;
            }
        }
        return like;
    }

    public void addLike(PubLike pubLike) {
        likeList.add(pubLike);
    }

    public long getNumbersOfLikes(){
        long likes = 0;
        if (likeList == null) {
            likeList = new ArrayList<>();
        }
        for (PubLike pubLike : likeList) {
            if (pubLike.isLiked()) {
                likes++;
            }
        }
        return likes;
    }

    public long getNumbersOfDislikes(){
        long dislikes = 0;
        if (likeList == null) {
            likeList = new ArrayList<>();
        }
        for (PubLike pubLike : likeList) {
            if (!pubLike.isLiked()) {
                dislikes++;
            }
        }
        return dislikes;
    }
}
