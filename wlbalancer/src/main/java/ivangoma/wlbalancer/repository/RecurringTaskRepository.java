package ivangoma.wlbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ivangoma.wlbalancer.model.RecurringTask;

@Repository
public interface RecurringTaskRepository extends JpaRepository <RecurringTask, Long> {

}
