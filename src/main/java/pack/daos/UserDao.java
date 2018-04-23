package pack.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.models.Author;

import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDao extends CrudRepository<User, Integer> {
}
