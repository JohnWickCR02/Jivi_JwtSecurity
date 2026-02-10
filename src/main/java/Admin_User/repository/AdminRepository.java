package Admin_User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_User.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByName(String name);

}
