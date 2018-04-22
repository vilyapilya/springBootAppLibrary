package pack.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.models.Book;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

    public List<Book> findAll();

    public Book findBookById(int ind);

    public void removeBookById(int id);




}
