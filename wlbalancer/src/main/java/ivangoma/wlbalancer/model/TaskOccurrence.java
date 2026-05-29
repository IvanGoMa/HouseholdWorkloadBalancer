package ivangoma.wlbalancer.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TaskOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private boolean completed = false;
    private int durationMinutes;
    private Timestamp dueAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @OneToMany(mappedBy = "taskOccurrence")
    private List<TaskCompleted> completetionData;

    @ManyToOne
    @JoinColumn(name = "recurring_task_id")
    private RecurringTask recurringTask;

    public TaskOccurrence() {}

    public TaskOccurrence(Long id, String title, boolean completed, int durationMinutes, Timestamp dueAt,
            Timestamp createdAt, Timestamp updatedAt, Household household, User assignedTo,
            List<TaskCompleted> completetionData, RecurringTask recurringTask) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.durationMinutes = durationMinutes;
        this.dueAt = dueAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.household = household;
        this.assignedTo = assignedTo;
        this.completetionData = completetionData;
        this.recurringTask = recurringTask;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    public Timestamp getDueAt() {
        return dueAt;
    }
    public void setDueAt(Timestamp dueAt) {
        this.dueAt = dueAt;
    }
    public User getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
    public Household getHousehold() {
        return household;
    }
    public void setHousehold(Household household) {
        this.household = household;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TaskCompleted> getCompletionData() {
        return completetionData;
    }

    public List<TaskCompleted> getCompletetionData() {
        return completetionData;
    }

    public void setCompletetionData(List<TaskCompleted> completetionData) {
        this.completetionData = completetionData;
    }

    public RecurringTask getRecurringTask() {
        return recurringTask;
    }

    public void setRecurringTask(RecurringTask recurringTask) {
        this.recurringTask = recurringTask;
    }

    public void addCompletionData(TaskCompleted taskCompleted){
        completetionData.add(taskCompleted);
        taskCompleted.setTaskOccurrence(this);
    }

}
