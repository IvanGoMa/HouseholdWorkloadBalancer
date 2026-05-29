package ivangoma.wlbalancer.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
@Entity
public class Household {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToMany
    @JoinTable(
        name = "household_member",
        joinColumns = @JoinColumn(name = "household_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    @OneToMany(mappedBy = "household")
    private List<TaskOccurrence> taskOccurrences;

    @OneToMany(mappedBy = "household")
    private List<RecurringTask> recurringTasks;

    public Household() {
    }

    public Household(Long id, String name, Timestamp createdAt, Timestamp updatedAt, List<User> members,
            List<TaskOccurrence> taskOccurrences, List<RecurringTask> recurringTasks) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.members = members;
        this.taskOccurrences = taskOccurrences;
        this.recurringTasks = recurringTasks;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public List<User> getMembers() {
        return members;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }
    public List<TaskOccurrence> getTaskOccurrences() {
        return taskOccurrences;
    }
    public void setTaskOccurrences(List<TaskOccurrence> taskOccurrences) {
        this.taskOccurrences = taskOccurrences;
    }
    public List<RecurringTask> getRecurringTasks() {
        return recurringTasks;
    }
    public void setRecurringTasks(List<RecurringTask> recurringTasks) {
        this.recurringTasks = recurringTasks;
    }

    public void addMember(User user) {
        this.members.add(user);
    }

    public void removeMember(User user) {
        this.members.remove(user);
    }

    public void addTaskOccurrence(TaskOccurrence taskOcurrence) {
        this.taskOccurrences.add(taskOcurrence);
        taskOcurrence.setHousehold(this);
    }

    public void addRecurringTask(RecurringTask recurringTask) {
        this.recurringTasks.add(recurringTask);
        recurringTask.setHousehold(this);
    }
}