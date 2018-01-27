package ua.com.owu.run;

import ua.com.owu.dao.AuthorDaoInterfaceImpl;
import ua.com.owu.dao.BookDaoInterfaceImpl;
import ua.com.owu.entity.Author;
import ua.com.owu.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("owu");
        EntityManager manager = entityManagerFactory.createEntityManager();
        AuthorDaoInterfaceImpl authorDaoInterface = new AuthorDaoInterfaceImpl(entityManagerFactory, manager);
        BookDaoInterfaceImpl bookDaoInterface = new BookDaoInterfaceImpl(entityManagerFactory, manager);

        try (Scanner scanner = new Scanner(System.in);) {
            int value =1;

            while (value != 0) {
                System.out.println("Choose action");
                value = scanner.nextInt();

                switch (value) {
                    case 1:
                        System.out.println("Enter book fields");
                        Book book = new Book(scanner.next(), scanner.next(), scanner.next());
                        System.out.println("Enter count of authors");
                        int n = scanner.nextInt();
                        List<Author> authors = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Enter author id");
                            authors.add(authorDaoInterface.getById(scanner.nextInt()));
                        }
                        bookDaoInterface.save(book);
                        bookDaoInterface.setAuthors(book.getId(), authors);
                        break;
                    case 2:
                        System.out.println("Enter author fields");
                        authorDaoInterface.save(new Author(scanner.next(), scanner.next()));
                        break;
                    case 3:
                        System.out.println(bookDaoInterface.getAll());
                        break;
                    case 4:
                        System.out.println(authorDaoInterface.getAll());
                        break;
                    case 5:
                        System.out.println("Enter author id");
                        List<Book> allByAuthor = bookDaoInterface.getAllByAuthor(authorDaoInterface.getById(scanner.nextInt()));
                        System.out.println(allByAuthor);
                        break;
                    case 6:
                        System.out.println("Enter author for update id");
                        int id = scanner.nextInt();
                        System.out.println("Enter new data");
                        authorDaoInterface.update(new Author(scanner.next(), scanner.next()), id);
                        break;
                    case 7:
                        System.out.println("Enter author update id");
                        id = scanner.nextInt();
                        System.out.println("Enter new data");
                        bookDaoInterface.update(new Book(scanner.next(), scanner.next(), scanner.next()), id);
                        break;
                    case 8:
                        System.out.println("Enter book for delete id");
                        bookDaoInterface.delete(scanner.nextInt());
                        break;
                    case 9:
                        System.out.println("Enter author for delete id");
                        authorDaoInterface.delete(scanner.nextInt());
                        break;
                    case 0:
                        break;
                }
            }
        }


        manager.close();
        entityManagerFactory.close();
    }
//        int value = 1;
//        try (Scanner scanner = new Scanner(System.in);) {
//            while (value != 0) {
//                System.out.println("Choose action");
//                value = scanner.nextInt();
//
//                switch (value) {
//                    case 1:
//                        System.out.println("Enter book fields");
//                        Book book = new Book(scanner.next(), scanner.next(), scanner.next());
//                        System.out.println("Enter count of authors");
//                        int n = scanner.nextInt();
//                        List<Author> authors = new ArrayList<>();
//                        for (int i = 0; i < n; i++) {
//                            System.out.println("Enter author id");
//                            authors.add(authorDaoInterface.getById(scanner.nextInt()));
//                        }
//                        bookDaoInterface.save(book);
//                        bookDaoInterface.setAuthors(book.getId(), authors);
//                        break;
//                    case 2:
//                        System.out.println("Enter author fields");
//                        authorDaoInterface.save(new Author(scanner.next(), scanner.next()));
//                        break;
//                    case 3:
//                        System.out.println(bookDaoInterface.getAll());
//                        break;
//                    case 4:
//                        System.out.println(authorDaoInterface.getAll());
//                        break;
//                    case 5:
//                        System.out.println("Enter author id");
//                        List<Book> allByAuthor = bookDaoInterface.getAllByAuthor(authorDaoInterface.getById(scanner.nextInt()));
//                        System.out.println(allByAuthor);
//                        break;
//                    case 6:
//                        System.out.println("Enter author for update id");
//                        int id = scanner.nextInt();
//                        System.out.println("Enter new data");
//                        authorDaoInterface.update(new Author(scanner.next(), scanner.next()), id);
//                        break;
//                    case 7:
//                        System.out.println("Enter author update id");
//                        id = scanner.nextInt();
//                        System.out.println("Enter new data");
//                        bookDaoInterface.update(new Book(scanner.next(), scanner.next(), scanner.next()), id);
//                        break;
//                    case 8:
//                        System.out.println("Enter book for delete id");
//                        bookDaoInterface.delete(scanner.nextInt());
//                        break;
//                    case 9:
//                        System.out.println("Enter author for delete id");
//                        authorDaoInterface.delete(scanner.nextInt());
//                        break;
//                    case 0:
//                        break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//    }
}
