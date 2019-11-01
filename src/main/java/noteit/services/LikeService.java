package noteit.services;

import noteit.blog.Article;
import noteit.blog.PubLike;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LikeService extends GenericCRUD<PubLike> {
    private static LikeService instance;

    private LikeService(){ super(PubLike.class); }

    public static LikeService getInstance() {
        if (instance == null) {
            instance = new LikeService();
        }
        return instance;
    }

    public List<PubLike> findByUser(String username){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select distinct pl from PubLike pl where pl.userLike.username = :user");
        query.setParameter("user", username);
        List<PubLike> pubLikes = query.getResultList();
        em.close();
        return pubLikes;
    }
}
