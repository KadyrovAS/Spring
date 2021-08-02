package h3.topic2;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;
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

    public FileTaskRepository() {
    }

    @Override
    public void save(Task task) {
        Stream<Task> stream = listTaskRepository.stream().filter(x->x.getId().compareTo(task.getId()) == 0);
        if (stream.count() == 0)
            listTaskRepository.add(task);
        else{
            Task taskTo = listTaskRepository.stream().filter(x->x.getId().compareTo(task.getId()) == 0).findAny().get();
            copyTask(task, taskTo);
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
        if (stream.count() != 0) {
            Task task = listTaskRepository.stream().filter(x -> x.getId().compareTo(id) == 0).findAny().get();
            listTaskRepository.remove(task);
        }
        writeToFile();
    }

    @Override
    public List<Task> get() {
        return listTaskRepository;
    }

    @Override
    public Task get(String id) {
        if (listTaskRepository.stream().filter(x->x.getId().compareTo(id) == 0).count() == 0)
            return null;
        return listTaskRepository.stream().filter(x->x.getId().compareTo(id) == 0).findAny().get();
    }

    private void copyTask(Task taskFrom, Task taskTo)  {
        for (Field fieldTo: taskTo.getClass().getDeclaredFields()){
            fieldTo.setAccessible(true);
            for (Field field: taskFrom.getClass().getDeclaredFields()) {
                if (fieldTo.getName().compareTo(field.getName()) == 0) {
                    field.setAccessible(true);
                    try {
                        fieldTo.set(taskTo, field.get(taskFrom));
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }
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