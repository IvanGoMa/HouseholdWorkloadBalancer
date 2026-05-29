package ivangoma.wlbalancer.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskCompleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_occurrence_id", nullable = false)
    private TaskOccurrence taskOccurrence;

    @ManyToOne
    @JoinColumn(name = "completed_by")
    private User completedBy;

    private Timestamp completedAt;

    public TaskCompleted() {}

    public TaskCompleted(Long id, TaskOccurrence taskOccurrence, User completedBy, Timestamp completedAt) {
        this.id = id;
        this.taskOccurrence = taskOccurrence;
        this.completedBy = completedBy;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskOccurrence getTaskOccurrence() {
        return taskOccurrence;
    }

    public void setTaskOccurrence(TaskOccurrence taskOccurrence) {
        this.taskOccurrence = taskOccurrence;
    }

    public User getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(User completedBy) {
        this.completedBy = completedBy;
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }

    
    
}
