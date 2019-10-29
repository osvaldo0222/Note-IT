package noteit.services;

import noteit.blog.Tag;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TagService extends GenericCRUD<Tag> {
    private static TagService instance;

    private TagService(){ super(Tag.class); }

    public static TagService getInstance() {
        if (instance == null) {
            instance = new TagService();
        }
        return instance;
    }

    public Tag findByTag(String tag) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select t from Tag t where t.tag = :tag");
        query.setParameter("tag", tag.toLowerCase());
        List<Tag> list = query.getResultList();
        return (list.size() == 0)?null:list.get(0);
    }
}
