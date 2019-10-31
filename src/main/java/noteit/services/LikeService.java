package noteit.services;

import noteit.blog.PubLike;

public class LikeService extends GenericCRUD<PubLike> {
    private static LikeService instance;

    private LikeService(){ super(PubLike.class); }

    public static LikeService getInstance() {
        if (instance == null) {
            instance = new LikeService();
        }
        return instance;
    }
}
