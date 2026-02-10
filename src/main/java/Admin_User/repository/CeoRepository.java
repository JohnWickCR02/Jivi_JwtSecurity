package Admin_User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Admin_User.entity.Ceo;

@Repository
public interface CeoRepository extends JpaRepository<Ceo, Integer> {

	public Ceo findByName(String name);

}
