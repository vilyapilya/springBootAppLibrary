package pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.models.Book;
import pack.daos.BookDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createBook(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        String authorIdStr = request.getParameter("author_id");
        int author_id = Integer.parseInt(authorIdStr);
        Book book = new Book(title, author_id);
        bookDao.save(book);

        model.addAttribute("title", title);
        model.addAttribute("author_id", author_id);
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Book> showBooks(Model model) {
        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);
        return books;
    }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book showBook(@PathVariable("id") String id, Model model) {
        int bookId = Integer.parseInt(id);
        return bookDao.findBookById(bookId);
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
