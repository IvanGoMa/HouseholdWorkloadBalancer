package ivangoma.wlbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ivangoma.wlbalancer.model.TaskOccurrence;

@Repository
public interface TaskOccurrenceRepository extends JpaRepository <TaskOccurrence, Long> {

}
