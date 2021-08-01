package h1.topic2;

import java.util.List;

public interface TaskRepository {
    void save(Task task);
    void update(Task task);
    void delete(String id);
    List<Task> get();
    Task get(String id);
}