package noteit.blog;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PubLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User userLike;
    private boolean liked; //True is like, false is Dislike
    @Transient
    private String action; //updated - was updated / deleted to delete /active is a idle state

    public PubLike() {
        this.action = "active";
        this.id = 0;
    }

    public PubLike(User userLike, boolean liked) {
        this.id = 0;
        this.action = "active";
        this.liked = liked;
        this.userLike = userLike;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
