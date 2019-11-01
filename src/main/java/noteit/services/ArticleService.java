package noteit.services;

import noteit.blog.Article;
import noteit.blog.Comment;
import noteit.blog.PubLike;
import noteit.blog.Tag;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ArticleService extends GenericCRUD<Article> {
    private static ArticleService instance;

    private ArticleService(){ super(Article.class); }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    @Override
    public void create(Article entity) {
        normalizeTags(entity.getTagList());
        super.create(entity);
    }

    @Override
    public void update(Article entity) {
        normalizeTags(entity.getTagList());
        List<PubLike> toDelete = controlLikes(entity.getLikeList());
        super.update(entity);
        for (PubLike pubLike : toDelete) {
            LikeService.getInstance().delete(pubLike.getId());
        }
    }

    private List<PubLike> controlLikes(List<PubLike> likeList) {
        List<PubLike> toDelete = new ArrayList<>();
        for (PubLike aux : likeList) {
            if (aux.getId() == 0 && aux.getAction().equalsIgnoreCase("active")) {
                LikeService.getInstance().create(aux);
            } else if(aux.getId() != 0 && aux.getAction().equalsIgnoreCase("updated")){
                LikeService.getInstance().update(aux);
            }
            if (aux.getAction().equalsIgnoreCase("deleted")) {
                toDelete.add(aux);
            }
        }
        for (PubLike aux : toDelete) {
            likeList.remove(aux);
        }
        return toDelete;
    }

    private void normalizeTags(List<Tag> tags){
        for (Tag tag : tags) {
            Tag aux = TagService.getInstance().findByTag(tag.getTag());
            if (aux == null) {
                tag.setTag(tag.getTag().toLowerCase());
                TagService.getInstance().create(tag);
            } else {
                tag.setId(aux.getId());
                tag.setTag(aux.getTag());
            }
        }
    }

    @Override
    public List<Article> findAll() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select distinct a from Article a inner join fetch a.tagList order by a.date desc");
        List<Article> articles = query.getResultList();
        em.close();
        return articles;
    }

    public Article find(long id){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Article a inner join fetch a.tagList where a.id = :id");
        query.setParameter("id", id);
        List<Article> list = query.getResultList();
        em.close();
        return (list.size() == 0)?null:list.get(0);
    }

    public List<Article> findByUser(String username){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select distinct a from Article a where a.author.username = :user");
        query.setParameter("user", username);
        List<Article> articles = query.getResultList();
        em.close();
        return articles;
    }

    public List<Article> findFive(int startPosition) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select distinct a from Article a order by a.date desc");
        query.setFirstResult(startPosition);
        query.setMaxResults(5);
        List<Article> articles = query.getResultList();
        for (Article aux : articles) {
            aux.getTagList().size();
            em.detach(aux);
        }
        em.close();
        return articles;
    }

    public Integer countArticles(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select count(a) from Article a");
        Integer result = Integer.parseInt(query.getSingleResult().toString());
        em.close();
        return result;
    }
}
