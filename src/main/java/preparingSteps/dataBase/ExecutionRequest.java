package preparingSteps.dataBase;

import entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class ExecutionRequest {
    Session session;

    public ExecutionRequest() {
        session = ExecutionConnection.openSession();
    }

    public void deleteAll() {
        String hql = "DELETE FROM Book";

        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql)
                .executeUpdate();
        tr.commit();
    }

    public List<Book> findAll() {
        String hql = "FROM Book";

        return session.createQuery(hql, Book.class)
                .getResultList();
    }

    public void insertBook(String bookTitle, Long authorId, Timestamp updated) {
        String hql = "INSERT INTO Book (book_title, author_id, updated) VALUES(:bookTitle, :authorId, :updated)";

        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql, Book.class)
                .setParameter("bookTitle", bookTitle)
                .setParameter("authorId", authorId)
                .setParameter("updated", updated)
                .executeUpdate();
        tr.commit();

    }

    public List<Book> findBookByTitle(String bookTitle) {
        String hql = "FROM Book WHERE book_title = :bookTitle";

        return session.createQuery(hql, Book.class)
                .setParameter("bookTitle", bookTitle)
                .getResultList();

    }

    public void deleteBookByTitle(String bookTitle) {
        String hql = "DELETE FROM Book WHERE book_title = :bookTitle";

        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql)
                .setParameter("bookTitle", bookTitle)
                .executeUpdate();
        tr.commit();
    }
}
