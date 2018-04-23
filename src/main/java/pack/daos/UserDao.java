package pack.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.models.Author;
import pack.models.User;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
}
