package Admin_User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Admin_User.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User  findByName(String name);

}
