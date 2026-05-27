package ivagoma.wlbalancer.model;

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

    @Column(nullable = false)
    private int householdId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @OneToMany(mappedBy = "recurringTask")
    private List<TaskOcurrence> taskOccurrences;

    public RecurringTask() {}
    
    public RecurringTask(Long id, String title, int durationMinutes, int dueInDays, int repeatInDays, int householdId,
            Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.dueInDays = dueInDays;
        this.repeatInDays = repeatInDays;
        this.householdId = householdId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public int getHouseholdId() {
        return householdId;
    }
    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
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

    public List<TaskOcurrence> getTaskOccurrences() {
        return taskOccurrences;
    }
    public void setTaskOccurrences(List<TaskOcurrence> taskOccurrences) {
        this.taskOccurrences = taskOccurrences;
    }
}
