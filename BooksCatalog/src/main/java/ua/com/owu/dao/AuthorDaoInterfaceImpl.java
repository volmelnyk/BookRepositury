package ua.com.owu.dao;

import ua.com.owu.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AuthorDaoInterfaceImpl implements DaoInterface<Author> {

    EntityManagerFactory factory;
    EntityManager manager;

    public AuthorDaoInterfaceImpl() {
        factory = Persistence.createEntityManagerFactory("owu");
        manager = factory.createEntityManager();

    }

    public void save(Author object) throws Exception {
        manager.getTransaction().begin();
        if (!object.getFistName().equals(null)&&
                !object.getSecondName().equals(null)) {
            manager.persist(object);
        }
        else
        {
            throw new Exception("Enter all fields!!");
        }
        manager.getTransaction().commit();
    }

    public void update(Author object, int id) throws Exception {
        manager.getTransaction().begin();
        Author author = manager.find(Author.class, id);

        if (!object.getFistName().equals(null)||
                !object.getSecondName().equals(null)) {
            author = object;
        }
        else
        {
            throw new Exception("Enter all fields!!");
        }
        manager.getTransaction().commit();

    }

    public void delete(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.find(Author.class,id));
        manager.getTransaction().commit();

    }

    public Author getById(int id) {
        return manager.find(Author.class, id);
    }

    public List<Author> getAll() {
        return manager.createQuery("select a from Author a").getResultList();
    }

    public void close() {
        manager.close();
        factory.close();
    }
}
