package ivangoma.wlbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ivagoma.wlbalancer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
