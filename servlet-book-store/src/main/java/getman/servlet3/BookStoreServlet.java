package getman.servlet3;

import getman.ejb.logger.EJBLogger;
import getman.ejb3.jpa.relations.*;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
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

    @Resource(name="jms/system/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name="jms/aparfenov/my.2nd.test.queue")
    private Destination targetQueueName;

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
            } else if ("request".equals(action)) {
                try{
                    handleRequest(request);
                } catch (Exception e) {
                    logger.error("Error while processing a book request", e);
                }
            }
        }
        response.sendRedirect("book_store");
    }

    private void selectEntities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Query booksQuery = entityManager.createQuery("SELECT m from BookEntity as m");
        List<BookEntity> books = booksQuery.getResultList();
        Query bookSpoilersQuery = entityManager.createQuery("SELECT s from BookSpoiler as s");
        List<BookSpoiler> bookSpoilers = bookSpoilersQuery.getResultList();
        Query isbnQuery = entityManager.createQuery("SELECT i from ISBN as i");
        List<ISBN> isbn = isbnQuery.getResultList();
        Query authorsNativeQuery = entityManager.createNativeQuery("SELECT a.id, a.name, a.surname FROM author a", "SelectAuthors");
        List<AuthorEntity> authors = authorsNativeQuery.getResultList();
        Query bookReviewNativeQuery = entityManager.createNativeQuery("SELECT * FROM book_review", BookReviewEntity.class);
        List<BookReviewEntity> bookReviews = bookReviewNativeQuery.getResultList();
        if (!books.isEmpty()) {
            request.setAttribute("books", books);
            for (BookEntity nextBook: books) {
                logger.info("book id: " + nextBook.getBookId() + ((nextBook.getSpoiler() != null) ?
                        nextBook.getSpoiler().getText() : null));
            }
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
        if (!bookSpoilers.isEmpty()) {
            request.setAttribute("bookSpoilers", bookSpoilers);
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/entityBean.jsp").forward(request, response);
    }

    private void handleAddition(HttpServletRequest request) throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        String bookNameParameter = request.getParameter("book");
        String isbnNumber = request.getParameter("isbn");
        String spoiler = request.getParameter("spoiler");
        String authorParameter = request.getParameter("author");

        String reviewBookId = request.getParameter("book_id");
        String reviewText = request.getParameter("review_text");
        String reviewAuthorId = request.getParameter("author_id");
        if (bookNameParameter != null && !bookNameParameter.isEmpty()) {
            transaction.begin();
            BookEntity newBook = new BookEntity();
            newBook.setBookName(bookNameParameter);
            ISBN newIsbn = new ISBN();
            newIsbn.setIsbnNumber(isbnNumber);
            BookSpoiler newBookSpoiler = new BookSpoiler();
            newBookSpoiler.setText(spoiler);
//            newBook.setSpoiler(newBookSpoiler);
            newBook.setIsbn(newIsbn);
            entityManager.persist(newBook);
            entityManager.flush();

            newBookSpoiler.setBook(newBook);
            entityManager.merge(newBookSpoiler);
            transaction.commit();
        } else if (authorParameter != null && !authorParameter.isEmpty()) {
            transaction.begin();
            AuthorEntity newAuthor = new AuthorEntity();
            newAuthor.setAuthorName(authorParameter);
            entityManager.persist(newAuthor);
            transaction.commit();
        } else if (reviewBookId != null && !reviewBookId.isEmpty()) {
            transaction.begin();
            BookReviewEntity newBookReview = new BookReviewEntity();
            newBookReview.setReviewText(reviewText);
            BookEntity book = entityManager.find(BookEntity.class, Integer.valueOf(reviewBookId));
            newBookReview.setBook(book);
            AuthorEntity author = entityManager.find(AuthorEntity.class, Integer.valueOf(reviewAuthorId));
            newBookReview.setAuthor(author);
            entityManager.merge(newBookReview);
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
        String bookSpoilerIdToRemoveStr = request.getParameter("bookSpoilerIdToRemove");
        Integer bookSpoilerIdToRemove = ((bookSpoilerIdToRemoveStr == null) || (bookSpoilerIdToRemoveStr.isEmpty())) ? null : Integer.valueOf(bookSpoilerIdToRemoveStr.trim());
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
        } else if (bookSpoilerIdToRemove != null) {
            transaction.begin();
            id = bookSpoilerIdToRemove;
            BookSpoiler spoilerEntity = entityManager.find(BookSpoiler.class, id);
            if (spoilerEntity != null) {
                entityManager.remove(spoilerEntity);
            }
            transaction.commit();
        }
    }

    private void handleRequest(HttpServletRequest request) throws JMSException {
        String bookId = request.getParameter("book_id");
        String authorId = request.getParameter("author_id");
        if ((bookId != null) && (authorId != null)) {
            RequestSender requestSender = new RequestSender(connectionFactory, targetQueueName);
            requestSender.sendBookRequest(bookId, authorId);
        }

    }
}
