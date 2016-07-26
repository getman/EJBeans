<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="getman.ejb3.jpa.relations.BookEntity" %>
<%@ page import="getman.ejb3.jpa.relations.AuthorEntity" %>
<%@ page import="getman.ejb3.jpa.relations.BookReviewEntity" %>
<%@ page import="getman.ejb3.jpa.relations.BookSpoiler" %>
<%@ page import="getman.ejb3.jpa.relations.ISBN" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TaskEE</title>
</head>
<body>
books
<% Object bookList = request.getAttribute("books");%>
<% if (bookList != null) { %>
    <table border="3">
        <tr>
            <td>id</td>
            <td>title</td>
            <td>author</td>
            <td>type</td>
            <td>spoiler</td>
            <td>review</td>
            <td>review author</td>
        </tr>
        <% for (BookEntity nextBook : (List<BookEntity>) bookList) { %>
            <tr>
                <%--id--%>
                <td><%= nextBook.getBookId() %></td>
                <%--title--%>
                <td><%= nextBook.getBookName() %></td>
                <%--book author(s)--%>

                <% Set<AuthorEntity> authorList = nextBook.getAuthorList();%>
                <% String bookAuthors = "";%>
                <% if (authorList != null) { %>
                    <% Iterator<AuthorEntity> authorIter = authorList.iterator();%>
                    <% while (authorIter.hasNext()) { %>
                        <% AuthorEntity nextAuthor = authorIter.next();%>
                        <%bookAuthors += nextAuthor.getAuthorName() + "/"; %>
                    <% } %>
                <% }%>
                <td><%= bookAuthors %></td>

                <%--type--%>
                <td><%= nextBook.getBookType() %></td>
                <% BookSpoiler spoiler = nextBook.getSpoiler();%>
                <% if (spoiler != null) { %>
                    <%--spoiler--%>
                    <td><%= spoiler.getText() %></td>
                <% } else { %>
                    no spoiler
                <% }%>
                <% Collection<BookReviewEntity> reviewList = nextBook.getBookReviews();%>
                <% Iterator<BookReviewEntity> iterator = null;%>
                <% if (reviewList != null) { %>
                <%     iterator = reviewList.iterator();%>
                <% } %>
                <% for (int i = 0; i < reviewList.size(); i++) { %>
                    <% BookReviewEntity nextReview = iterator.next();%>
                    <%--review--%>
                    <td><%= nextReview.getReviewText() %></td>
                    <%--review author--%>
                    <td><%= nextReview.getAuthor().getAuthorName() %></td>
                    <% if (iterator.hasNext()) { %>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                    <% } else if (reviewList.size() > 1) { %>
                        </tr>
                    <% } %>
                <% } %>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no books
<% } %>
<form method="POST" action="book_store">
    <p>
        <label for="item">Add book, name:</label>
        <input id="book" type="text" name="book"/>

        <label for="item">ISBN:</label>
        <input id="isbn" type="text" name="isbn"/>

        <label for="item">spoiler:</label>
        <input id="spoiler" type="text" name="spoiler"/>

        <input type="hidden" name="action" value="add"/>
        <input type="submit" value="add"/>
    </p>

</form>
<form method="POST" action="book_store">
    <p>
        <label for="item">Remove by book id:</label>
        <input id="bookIdToRemove" type="text" name="bookIdToRemove"/>
        <input type="hidden" name="action" value="remove"/>
        <input type="submit" value="remove"/>
    </p>
</form>
<p></p>

authors
<% Object authorList = request.getAttribute("authors");%>
<% if (authorList != null) { %>
    <table>
        <% for (AuthorEntity nextAuthor : (List<AuthorEntity>) authorList) { %>
            <tr>
                <td>id: <%= nextAuthor.getAuthorId() %></td>
                <td>name: <%= nextAuthor.getAuthorName() %></td>
                <td>surname: <%= nextAuthor.getAuthorSurname() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no passport
<% } %>
<form method="POST" action="book_store">
    <p>
        <label for="item">Add author, name:</label>
        <input id="author" type="text" name="author"/>
        <input type="hidden" name="action" value="add"/>
        <input type="submit" value="add"/>
    </p>

</form>
<form method="POST" action="book_store">
    <p>
        <label for="item">Remove by author id:</label>
        <input id="authorIdToRemove" type="text" name="authorIdToRemove"/>
        <input type="hidden" name="action" value="remove"/>
        <input type="submit" value="remove"/>
    </p>
</form>
<p></p>

book review
<% Object bookReviewList = request.getAttribute("bookReviews");%>
<% if (bookReviewList != null) { %>
    <table>
        <% for (BookReviewEntity nextBookReview : (List<BookReviewEntity>) bookReviewList) { %>
            <tr>
                <td>id: <%= nextBookReview.getReviewId() %></td>
                <td>text: <%= nextBookReview.getReviewText() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no passport
<% } %>
<form method="POST" action="book_store">
    <p>
        <label for="item">Add review, book id:</label>
        <input id="book_id" type="text" name="book_id"/>
        <label for="item">review text:</label>
        <input id="review_text" type="text" name="review_text"/>
        <label for="item">author id:</label>
        <input id="author_id" type="text" name="author_id"/>

        <input type="hidden" name="action" value="add"/>
        <input type="submit" value="add"/>
    </p>

</form>
<form method="POST" action="book_store">
    <p>
        <label for="item">Remove by book review id:</label>
        <input id="bookReviewIdToRemove" type="text" name="bookReviewIdToRemove"/>
        <input type="hidden" name="action" value="remove"/>
        <input type="submit" value="remove"/>
    </p>
</form>
<p></p>

book ISBN
<% Object isbnList = request.getAttribute("bookIsbn");%>
<% if (isbnList != null) { %>
    <table>
        <% for (ISBN nextBookIsbn : (List<ISBN>) isbnList) { %>
            <tr>
                <td>id: <%= nextBookIsbn.getIsbnId() %></td>
                <td>number: <%= nextBookIsbn.getIsbnNumber() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no ISBN
<% } %>
<form method="POST" action="book_store">
    <p>
        <label for="item">Remove by ISBN id:</label>
        <input id="isbnIdToRemove" type="text" name="isbnIdToRemove"/>
        <input type="hidden" name="action" value="remove"/>
        <input type="submit" value="remove"/>
    </p>
</form>
<p></p>

book spoiler
<% Object spoilerList = request.getAttribute("bookSpoilers");%>
<% if (spoilerList != null) { %>
    <table>
        <% for (BookSpoiler nextBookSpoiler : (List<BookSpoiler>) spoilerList) { %>
            <tr>
                <td>id: <%= nextBookSpoiler.getId() %></td>
                <td>number: <%= nextBookSpoiler.getText() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no spoilers
<% } %>
<form method="POST" action="book_store">
        <label for="item">Remove by spoiler id:</label>
        <input id="bookSpoilerIdToRemove" type="text" name="bookSpoilerIdToRemove"/>
        <input type="hidden" name="action" value="remove"/>
        <input type="submit" value="remove"/>
</form>
<p></p>


Запрос наличия книг в магазине
<form method="POST" action="book_store">
    <p>
        <label for="item">Идентификатор книги(book id):</label>
        <input id="book_id" type="text" name="book_id"/>
        <label for="item">author id:</label>
        <input id="author_id" type="text" name="author_id"/>

        <input type="hidden" name="action" value="request"/>
        <input type="submit" value="request"/>
    </p>
</form>

</body>
</html>

