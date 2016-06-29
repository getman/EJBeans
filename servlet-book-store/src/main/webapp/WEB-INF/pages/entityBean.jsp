<%@ page import="java.util.List" %>
<%@ page import="getman.ejb3.jpa.relations.BookEntity" %>
<%@ page import="getman.ejb3.jpa.relations.AuthorEntity" %>
<%@ page import="getman.ejb3.jpa.relations.BookReviewEntity" %>
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
    <table>
        <% for (BookEntity nextBook : (List<BookEntity>) bookList) { %>
            <tr>
                <td>id: <%= nextBook.getBookId() %></td>
                <td>name: <%= nextBook.getBookName() %></td>
                <td>type: <%= nextBook.getBookType() %></td>
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
        <input id="book_review" type="text" name="book_review"/>
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

</body>
</html>

