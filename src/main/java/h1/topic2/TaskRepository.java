package h1.topic2;

import java.util.List;

public interface TaskRepository {
    void save(Task task) throws IllegalAccessException;
    void update(Task task) throws IllegalAccessException;
    void delete(String id);
    List<Task> get();
    Task get(String id);
}