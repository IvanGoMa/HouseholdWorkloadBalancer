# HouseholdWorkloadBalancer

## Relational Model
recursive_task(<u>id</u>,title, time_to_complete, frequency, created_at, updated_at, household_id)

    where household_id references household(id)

task_occurrence(<u>id</u>, title, isCompleted?, time_to_complete, due_at, assigned_to, created_at, updated_at, household_id)
    
    where assigned_to references user(id), household_id references household(id)

user(<u>id</u>, username, password, weekly_hours_worked, created_at, updated_at)

household(<u>id</u>, created_at, updated_at)

task_completed(<u>id</u>, task_occurrence_id, user_id, completed_at)

    where task_occurrence_id references task_occurrence(id), user_id references user(id)

household_members(<u>household_id, user_id</u>, name, joined_at)

    where household_id references household(id), user_id references user(id)



