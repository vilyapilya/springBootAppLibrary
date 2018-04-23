package pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pack.daos.AuthorDao;
import pack.models.Author;
import pack.models.Book;
import pack.daos.BookDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createBook(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        String authorFirstName = request.getParameter("firstName");
        String authorLastName = request.getParameter("lastName");
        int author_id;
        Author author = authorDao.findAuthorByFirstNameAndAndLastName(authorFirstName, authorLastName);
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
    public ModelAndView showBook(@PathVariable("id") int id, Model model) {
        int bookId = id;
        Book book = bookDao.findBookById(bookId);
        model.addAttribute("title", book.getTitle());
        return new ModelAndView("showBook");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") String id, Model model) {
        int bookId = Integer.parseInt(id);
        bookDao.removeBookById(bookId);
        return "the book is removed";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Book editBook(@PathVariable("id") String id,
                         HttpServletRequest request,
                         Model model) {
        int bookId = Integer.parseInt(id);
        Book foundBook = bookDao.findBookById(bookId);
        String title = request.getParameter("title");
        String authorIdStr = request.getParameter("author_id");
        int author_id = Integer.parseInt(authorIdStr);
        if(foundBook.getId() == bookId) {
            foundBook.setAuthor_id(author_id);
            foundBook.setTitle(title);
        }
        bookDao.save(foundBook);
        return foundBook;
    }
}
