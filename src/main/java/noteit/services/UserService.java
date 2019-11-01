package noteit.services;

import noteit.blog.Article;
import noteit.blog.Comment;
import noteit.blog.PubLike;
import noteit.blog.User;

import java.util.List;

public class UserService extends GenericCRUD<User> {
    private static UserService instance;

    private UserService(){ super(User.class); }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void delete(Object entityID) {
        for (Article aux : ArticleService.getInstance().findByUser(entityID.toString())) {
            ArticleService.getInstance().delete(aux.getId());
        }
        for (Comment aux : CommentService.getInstance().findByUser(entityID.toString())) {
            CommentService.getInstance().delete(aux.getId());
        }
        List<Article> articles = ArticleService.getInstance().findAll();
        List<Comment> comments = CommentService.getInstance().findAll();
        for (Article aux: articles) {
            PubLike toDelete = aux.getUserLike(entityID.toString());
            if (toDelete != null) {
                toDelete.setAction("deleted");
                ArticleService.getInstance().update(aux);
            }
        }
        for (Comment aux: comments) {
            PubLike toDelete = aux.getUserLike(entityID.toString());
            if (toDelete != null) {
                toDelete.setAction("deleted");
                CommentService.getInstance().update(aux);
            }
        }
        for (PubLike pubLike : LikeService.getInstance().findByUser(entityID.toString())) {
            LikeService.getInstance().delete(pubLike.getId());
        }
        super.delete(entityID);
    }
}
