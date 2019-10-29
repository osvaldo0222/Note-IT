package noteit.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;

//Generic CRUD for all objects
//Based on vacax code (Class GestionDB on sparkjavaorm)
public class GenericCRUD<T> {
    private static EntityManagerFactory emf;
    private Class<T> entityClass;

    public GenericCRUD(Class<T> entityClass) {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("Note-IT");
        }
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    /**
     * Metodo para obtener el valor del campo anotado como @ID.
     * @param entity
     * @return
     */
    private Object getCampValue(T entity){
        if(entity == null){
            return null;
        }
        //Applying reflexion class
        //Getting privates camps
        for(Field f : entity.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    Object campValue = f.get(entity);
                    //
                    System.out.println("nombre del campo: "+f.getName());
                    System.out.println("Tipo del campo: "+f.getType().getName());
                    System.out.println("Valor del campo: "+ campValue);
                    //
                    return campValue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     *
     * @param entity
     */
    public void create(T entity){
        EntityManager em = getEntityManager();

        try {
            if (em.find(entityClass, getCampValue(entity)) != null) {
                System.out.println("La entidad a guardar existe, no creada.");
                return;
            }
        }catch (IllegalArgumentException ie){
            System.out.println("Parametro ilegal.");
        }

        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            throw  ex;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param entity
     */
    public void update(T entity){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            throw  ex;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param entityID
     */
    public void delete(Object entityID){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            T entity = em.find(entityClass, entityID);
            em.remove(entity);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            throw  ex;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        EntityManager em = getEntityManager();
        try{
            return em.find(entityClass, id);
        } catch (Exception ex){
            throw  ex;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public List<T> findAll(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.select(criteriaQuery.from(entityClass));
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }
}
