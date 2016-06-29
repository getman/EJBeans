package getman.servlet3;

import getman.ejb.logger.EJBLogger;
import getman.ejb3.jpa.relations.AuthorEntity;
import getman.ejb3.jpa.relations.BookEntity;
import getman.ejb3.jpa.relations.BookReviewEntity;
import getman.ejb3.jpa.relations.ISBN;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Parfenov Artem on 27.06.2016.
 */
@WebServlet(urlPatterns = "/book_store")
public class BookStoreServlet extends HttpServlet {
    //-----------------Fields--------------------
    @PersistenceContext(unitName = "bookStore-unit")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    private Logger logger = EJBLogger.getLogger(getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet(...) performed");
        selectEntities(request, response);
//        selectEntityProperties(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        logger.info("Action is:" + action);
        if (action != null) {
            if ("add".equals(action)) {
                try {
                    handleAddition(request);
                } catch (Throwable t) {
                    throw new RuntimeException(t);
                }
            } else if ("remove".equals(action)) {
                try {
                    handleRemoval(request);
                } catch (Throwable t) {
                    throw new RuntimeException(t);
                }
            }
        }
        response.sendRedirect("book_store");
    }

    private void selectEntities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Query booksQuery = entityManager.createQuery("SELECT m from BookEntity as m");
        List<BookEntity> books = booksQuery.getResultList();
        Query isbnQuery = entityManager.createQuery("SELECT i from ISBN as i");
        List<ISBN> isbn = isbnQuery.getResultList();
        Query authorsNativeQuery = entityManager.createNativeQuery("SELECT a.id, a.name, a.surname FROM author a", "SelectAuthors");
        List<AuthorEntity> authors = authorsNativeQuery.getResultList();
        Query bookReviewNativeQuery = entityManager.createNativeQuery("SELECT * FROM book_review", BookReviewEntity.class);
        List<BookReviewEntity> bookReviews = bookReviewNativeQuery.getResultList();
        if (!books.isEmpty()) {
            request.setAttribute("books", books);
        }
        if (!isbn.isEmpty()) {
            request.setAttribute("bookIsbn", isbn);
        }
        if (!authors.isEmpty()) {
            request.setAttribute("authors", authors);
        }
        if (!bookReviews.isEmpty()) {
            request.setAttribute("bookReviews", bookReviews);
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/entityBean.jsp").forward(request, response);
    }

    private void handleAddition(HttpServletRequest request) throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        String bookNameParameter = request.getParameter("book");
        String isbnNumber = request.getParameter("isbn");
        String authorParameter = request.getParameter("author");
        String bookReviewParameter = request.getParameter("book_review");
        if (bookNameParameter != null && !bookNameParameter.isEmpty()) {
            transaction.begin();
            BookEntity newBook = new BookEntity();
            newBook.setBookName(bookNameParameter);
            ISBN newIsbn = new ISBN();
            newIsbn.setIsbnNumber(isbnNumber);
            newBook.setIsbn(newIsbn);
//            entityManager.merge(newBook);
            entityManager.persist(newBook);
            transaction.commit();
        } else if (authorParameter != null && !authorParameter.isEmpty()) {
            transaction.begin();
            AuthorEntity newAuthor = new AuthorEntity();
            newAuthor.setAuthorName(authorParameter);
            entityManager.persist(newAuthor);
            transaction.commit();
        } else if (bookReviewParameter != null && !bookReviewParameter.isEmpty()) {
            transaction.begin();
            BookReviewEntity newBookReview = new BookReviewEntity();
            newBookReview.setReviewText(bookReviewParameter);
            entityManager.persist(newBookReview);
            transaction.commit();
        }
    }

    private void handleRemoval(HttpServletRequest request) throws IOException, SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        String bookIdToRemoveStr = request.getParameter("bookIdToRemove");
        Integer bookIdToRemove = ((bookIdToRemoveStr == null) || (bookIdToRemoveStr.isEmpty())) ? null : Integer.valueOf(bookIdToRemoveStr.trim());
        String isbnIdToRemoveStr = request.getParameter("isbnIdToRemove");
        Integer isbnIdToRemove = ((isbnIdToRemoveStr == null) || (isbnIdToRemoveStr.isEmpty())) ? null : Integer.valueOf(isbnIdToRemoveStr.trim());
        String authorIdToRemoveStr = request.getParameter("authorIdToRemove");
        Integer authorIdToRemove = ((authorIdToRemoveStr == null) || (authorIdToRemoveStr.isEmpty())) ? null : Integer.valueOf(authorIdToRemoveStr.trim());
        String bookReviewIdToRemoveStr = request.getParameter("bookReviewIdToRemove");
        Integer bookReviewIdToRemove = ((bookReviewIdToRemoveStr == null) || (bookReviewIdToRemoveStr.isEmpty())) ? null : Integer.valueOf(bookReviewIdToRemoveStr.trim());
        int id;
        if (bookIdToRemove != null) {
            transaction.begin();
            id = bookIdToRemove;
            BookEntity book = entityManager.find(BookEntity.class, id);
            if (book != null) {
                entityManager.remove(book);
            }
            transaction.commit();
        } else if (authorIdToRemove != null) {
            transaction.begin();
            id = authorIdToRemove;
            AuthorEntity author = entityManager.find(AuthorEntity.class, id);
            if (author != null) {
                entityManager.remove(author);
            }
            transaction.commit();
        } else if (bookReviewIdToRemove != null) {
            transaction.begin();
            id = bookReviewIdToRemove;
            BookReviewEntity bookReviewEntity = entityManager.find(BookReviewEntity.class, id);
            if (bookReviewEntity != null) {
                entityManager.remove(bookReviewEntity);
            }
            transaction.commit();
        } else if (isbnIdToRemove != null) {
            transaction.begin();
            id = isbnIdToRemove;
            ISBN isbnEntity = entityManager.find(ISBN.class, id);
            if (isbnEntity != null) {
                entityManager.remove(isbnEntity);
            }
            transaction.commit();
        }
    }
}
