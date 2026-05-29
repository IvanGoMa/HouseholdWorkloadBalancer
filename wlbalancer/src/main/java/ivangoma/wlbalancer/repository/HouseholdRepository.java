package ivangoma.wlbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ivangoma.wlbalancer.model.Household;

@Repository
public interface HouseholdRepository extends JpaRepository <Household, Long>{

}
