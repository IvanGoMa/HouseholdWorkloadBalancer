package ivangoma.wlbalancer.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "members")
    private List<Household> households;

    @OneToMany(mappedBy = "assignedTo")
    private List<TaskOccurrence> assignedTasks;

    @OneToMany(mappedBy = "completedBy")
    private List<TaskCompleted> completedTasks;


    public User() {}

    public User(Long id, String username, String password, Timestamp createdAt, Timestamp updatedAt,
            List<Household> households, List<TaskOccurrence> assignedTasks, List<TaskCompleted> completedTasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.households = households;
        this.assignedTasks = assignedTasks;
        this.completedTasks = completedTasks;
    }

    public User(String username, String password, Timestamp createdAt, Timestamp updatedAt){
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Household> getHouseholds() {
        return households;
    }

    public void setHouseholds(List<Household> households) {
        this.households = households;
    }

    public List<TaskOccurrence> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<TaskOccurrence> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<TaskCompleted> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<TaskCompleted> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public void addAssignedTask(TaskOccurrence task) {
        assignedTasks.add(task);
        task.setAssignedTo(this);
    }

    public void addCompletedTask(TaskCompleted task) {
        completedTasks.add(task);
        task.setCompletedBy(this);
    }

    public void addHousehold(Household household) {
        households.add(household);
        household.getMembers().add(this);
    }

    public void removeHousehold(Household household) {
        households.remove(household);
        household.getMembers().remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    
}
