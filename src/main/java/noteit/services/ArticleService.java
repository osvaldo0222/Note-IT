package noteit.services;

import noteit.blog.Article;
import noteit.blog.Tag;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        super.update(entity);
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
        Query query = em.createQuery("select distinct a from Article a inner join fetch a.tagList");
        return query.getResultList();
    }

    public Article find(long id){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Article a inner join fetch a.tagList where a.id = :id");
        query.setParameter("id", id);
        List<Article> list = query.getResultList();
        return (list.size() == 0)?null:list.get(0);
    }
}
