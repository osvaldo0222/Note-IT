package noteit.services;

import noteit.blog.Comment;

public class CommentService extends GenericCRUD<Comment> {
    private static CommentService instance;

    private CommentService(){ super(Comment.class); }

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }
}
