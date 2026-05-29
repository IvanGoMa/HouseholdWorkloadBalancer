package ivangoma.wlbalancer.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class RecurringTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int durationMinutes;

    private int dueInDays;
    private int repeatInDays;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @OneToMany(mappedBy = "recurringTask")
    private List<TaskOccurrence> taskOccurrences;

    public RecurringTask() {}

    public RecurringTask(Long id, String title, int durationMinutes, int dueInDays, int repeatInDays,
            Timestamp createdAt, Timestamp updatedAt, Household household, List<TaskOccurrence> taskOccurrences) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.dueInDays = dueInDays;
        this.repeatInDays = repeatInDays;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.household = household;
        this.taskOccurrences = taskOccurrences;
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
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    public int getDueInDays() {
        return dueInDays;
    }
    public void setDueInDays(int dueInDays) {
        this.dueInDays = dueInDays;
    }
    public int getRepeatInDays() {
        return repeatInDays;
    }
    public void setRepeatInDays(int repeatInDays) {
        this.repeatInDays = repeatInDays;
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

    public List<TaskOccurrence> getTaskOccurrences() {
        return taskOccurrences;
    }
    public void setTaskOccurrences(List<TaskOccurrence> taskOccurrences) {
        this.taskOccurrences = taskOccurrences;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public void addTaskOccurrence(TaskOccurrence taskOccurrence){
        taskOccurrences.add(taskOccurrence);
        taskOccurrence.setRecurringTask(this);
    }
    
}
