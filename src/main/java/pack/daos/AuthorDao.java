package pack.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.models.Author;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface AuthorDao extends CrudRepository<Author, Integer> {

    public Author findAuthorById(int id);
    public Author findAuthorByFirstNameAndAndLastName(String firstName, String lastName);
}
