package ua.com.owu.dao;

import ua.com.owu.entity.Author;
import ua.com.owu.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class BookDaoInterfaceImpl implements DaoInterface<Book> {

    EntityManagerFactory factory;
    EntityManager manager;

    public BookDaoInterfaceImpl() {
        factory = Persistence.createEntityManagerFactory("owu");
        manager = factory.createEntityManager();

    }
    public void setAuthors(int book,List<Author> a)
    {
        manager.getTransaction().begin();
        Book byId = getById(book);
        byId.setAuthors(a);
        manager.getTransaction().commit();
    }

    public void save(Book object) throws Exception {
        manager.getTransaction().begin();
        if (!object.getDescribtion().equals(null) && !object.getBookName().equals(null)
                && !object.getDate().equals(null)) {

            manager.persist(object);
            object.setAuthors(object.getAuthors());
        } else {
            throw new Exception("Enter all fields!");
        }
        manager.getTransaction().commit();
    }

    public void update(Book object, int id) throws Exception {
        manager.getTransaction().begin();
        Book book = manager.find(Book.class, id);

        if (!object.getAuthors().equals(null) && !object.getBookName().equals(null)
                && !object.getDate().equals(null)) {
            book.setBookName( object.getBookName());
            book.setDate( object.getDate());
            book.setDescribtion( object.getDescribtion());
        } else {
            throw new Exception("Enter all fields!!");
        }
        manager.getTransaction().commit();

    }

    public void delete(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.find(Book.class, id));
        manager.getTransaction().commit();

    }

    public Book getById(int id) {
        return manager.find(Book.class, id);
    }

    public List<Book> getAll() {
        return manager.createQuery("select b from Book b").getResultList();
    }

    public List<Book> getAllByAuthor(Author author) {
        List<Book> resultList = new ArrayList<>();
        List<Book> resultList1 = manager.createQuery("select b from Book  b", Book.class).getResultList();
        for (Book book : resultList1) {
            if(book.getAuthors().contains(author))
            {
                resultList.add(book);
            }
        }
        return resultList;
    }

    public void close() {
        manager.close();
        factory.close();
    }
}
