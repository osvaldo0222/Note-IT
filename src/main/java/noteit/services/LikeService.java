package noteit.services;

import noteit.blog.LikeArticle;

public class LikeService extends GenericCRUD<LikeArticle> {
    private static LikeService instance;

    private LikeService(){ super(LikeArticle.class); }

    public static LikeService getInstance() {
        if (instance == null) {
            instance = new LikeService();
        }
        return instance;
    }
}
