package h1.topic2;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileTaskRepository implements TaskRepository {
    final static String TASK_REPOSITORY_PATH = "repository.json";
    List<Task>listTaskRepository;

    public void setListTaskRepository(List<Task> listTaskRepository) {
        this.listTaskRepository = listTaskRepository;
    }

    @Override
    public void save(Task task) {
        Stream<Task> stream = listTaskRepository.stream().filter(x->x.getId().compareTo(task.getId()) == 0);
        if (stream.count() == 0)
            listTaskRepository.add(task);
        else{
            Task taskTo = stream.findAny().get();
            taskTo = task;
        }
        writeToFile();
    }

    @Override
    public void update(Task task) {
        save(task);
    }

    @Override
    public void delete(String id) {
        Stream<Task> stream = listTaskRepository.stream().filter(x->x.getId().compareTo(id) == 0);
        if (stream.count() != 0)
            listTaskRepository.remove(stream.findAny().get());
        writeToFile();
    }

    @Override
    public List<Task> get() {
        return listTaskRepository;
    }

    @Override
    public Task get(String id) {
        Stream<Task> stream = listTaskRepository.stream().filter(x->x.getId().compareTo(id) == 0);
        return stream.findAny().get();
    }

    private void writeToFile()  {
        String json = new Gson().toJson(listTaskRepository);
        try {
            Files.writeString(Path.of(TASK_REPOSITORY_PATH), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}