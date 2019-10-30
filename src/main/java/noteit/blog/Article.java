package noteit.blog;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Type(type = "text")
    private String body;
    @OneToOne
    private User author;
    private Date date;
    @OneToMany(mappedBy = "article")
    private List<Comment> commentList;
    @ManyToMany
    private List<Tag> tagList;
    @OneToMany(mappedBy = "article")
    @Fetch(FetchMode.JOIN)
    private List<LikeArticle> likeList;

    public Article() {}

    public Article(String title, String body, User author, Date date) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<LikeArticle> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<LikeArticle> likeList) {
        this.likeList = likeList;
    }

    public long getNumbersOfLikes(){ return (likeList == null)?0:likeList.size(); }

    public void addTag(Tag tag){
        if (tagList == null) {
            tagList = new ArrayList<>();
        }
        boolean exist = false;
        for (Tag aux : tagList) {
            if (aux.getTag().equalsIgnoreCase(tag.getTag())) {
                exist = true;
                break;
            }
        }
        if (!exist){
            tagList.add(tag);
        }
    }

    public LikeArticle getUserLike(String username){
        LikeArticle like = null;
        for (LikeArticle likeArticle : likeList) {
            if (likeArticle.getUserLike().getUsername().equalsIgnoreCase(username)){
                like = likeArticle;
                break;
            }
        }
        return like;
    }
}
