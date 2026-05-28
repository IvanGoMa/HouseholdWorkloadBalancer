package ivangoma.wlbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ivagoma.wlbalancer.model.TaskCompleted;

@Repository
public interface TaskCompletedRepository extends JpaRepository <TaskCompleted, Long> {

}
