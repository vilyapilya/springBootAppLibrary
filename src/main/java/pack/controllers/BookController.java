package pack.controllers;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.daos.AuthorDao;
import pack.models.Author;
import pack.models.Book;
import pack.daos.BookDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    public String createBook(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        String authorFirstName = request.getParameter("firstName");
        String authorLastName = request.getParameter("lastName");
        int author_id;
        Author author = authorDao.findAuthorByFirstNameAndLastName(authorFirstName, authorLastName);
        if(author != null) {
            author_id = author.getId();
            Book book = new Book(title, author_id);
            bookDao.save(book);
        } else {
            Author newAuthor = new Author(authorFirstName, authorLastName);
            authorDao.save(newAuthor);
            author_id = newAuthor.getId();
            Book book = new Book(title, author_id);
            bookDao.save(book);
        }
        model.addAttribute("title", title);
        model.addAttribute("authorFirstName", authorFirstName);
        model.addAttribute("authorLastName", authorLastName);
        return "createBook";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> showBooks(Model model) {
        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);
        return books;
    }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book showBook(@PathVariable("id") int id, Model model) {
        int bookId = id;
        Book book = bookDao.findBookById(bookId);
        if(book != null) {
            model.addAttribute("title", book.getTitle());
        } else {
            System.out.println("The book does exist");
        }
        return book;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") String id, Model model) {
        int bookId = Integer.parseInt(id);
        bookDao.removeBookById(bookId);
        return "the book is removed";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Book editBook(@PathVariable("id") int id,
                         HttpServletRequest request,
                         Model model) {
        int bookId = id;

        String newTitle = request.getParameter("title");
        String newAuthorFistName = request.getParameter("firstName");
        String newAuthorLastName = request.getParameter("lastName");
        Author author = authorDao.findAuthorByFirstNameAndLastName(newAuthorFistName, newAuthorLastName);
        Book foundBook = bookDao.findBookById(bookId);

        if(foundBook != null && foundBook.getId() == bookId) {
            if(author != null) {
                foundBook.setAuthor_id(author.getId());
                System.out.println(author.getId());

            } else {
                Author newAuthor = new Author(newAuthorFistName, newAuthorLastName);
                authorDao.save(newAuthor);
                foundBook.setAuthor_id(newAuthor.getId());
            }
            foundBook.setTitle(newTitle);
        } else {
            System.out.println("The book does not exist");
        }
        bookDao.save(foundBook);
        return foundBook;
    }
}
