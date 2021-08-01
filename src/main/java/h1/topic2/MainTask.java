package h1.topic2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainTask {
    private static Task saveTask(Task task, FileTaskRepository repository) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        if (task != null) {
            while (true) {
                System.out.println("Введите одну из следующих команд:");
                System.out.println("Yes - сохранить текущее задание;");
                System.out.println("No - создать новое задание");
                String line = bufferedReader.readLine();
                if (line.toUpperCase().compareTo("YES") == 0){
                    repository.update(task);
                    return task;
                }
                if (line.toUpperCase().compareTo("NO") == 0)
                    break;
            }
        }
        Task newTask = newTask();
        repository.save(newTask);
        return newTask;
    }

    private static Task newTask() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("");
        System.out.print("Введите id: ");
        String id = bufferedReader.readLine();
        System.out.print("Введите description: ");
        String description = bufferedReader.readLine();
        System.out.println("Введите author: ");
        String author = bufferedReader.readLine();
        System.out.println("Введите name: ");
        String name = bufferedReader.readLine();
        System.out.println("Введите storyPoint: ");
        int storyPoint = Integer.valueOf(bufferedReader.readLine());
        Task newTask = new Task(id, description, author,name,storyPoint);
        return newTask;
    }
    private static Task getTask(FileTaskRepository repository, String command) {
        return repository.get(command);
    }

    private static void updateTask(Task task, FileTaskRepository repository) throws IOException {
        if (task == null)
            saveTask(task,repository);
        else {
            System.out.println("Внесите необходимые изменения в задание:");
            System.out.println(task);
            Task newTask = newTask();
            repository.update(newTask);
        }
    }

    private static void deleteTask(FileTaskRepository repository, String id) {
        repository.delete(id);
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("contextRepository.xml");
        FileTaskRepository repository = (FileTaskRepository) context.getBean("repository");
        repository.get().forEach(System.out::println);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Task currentTask = null;
        while (true) {
            System.out.print("Введите команду: ");
            String line = bufferedReader.readLine();
            if (line.compareTo("?") == 0 || line.toUpperCase().compareTo("HELP") == 0) {
                System.out.println("Список команд:");
                System.out.println("SAVE - создать новое задание;");
                System.out.println("UPDATE - изменить задание");
                System.out.println("DELETE <id> - удалить задание с id;");
                System.out.println("GET - напечатать список заданий;");
                System.out.println("GET <id> - выбрать задание с id");
                System.out.println("EXIT - завершение работы");
            } else if (line.toUpperCase().compareTo("EXIT") == 0)
                break;
            else {
                String[] commands = line.split(" ");
                if (commands[0].toUpperCase().compareTo("SAVE") == 0)
                    currentTask = saveTask(currentTask, repository);
                else if (commands[0].toUpperCase().compareTo("UPDATE") == 0)
                    updateTask(currentTask, repository);
                else if (commands[0].toUpperCase().compareTo("DELETE") == 0)
                    if (commands.length == 2)
                        deleteTask(repository, commands[1]);
                    else
                        System.out.println("Отсутствует обязательный аргумент в команде DELETE");
                else if (commands[0].toUpperCase().compareTo("GET") == 0)
                    if (commands.length == 1)
                        repository.get().forEach(System.out::println);
                    else {
                        currentTask = getTask(repository, commands[1]);
                        if (currentTask == null)
                            System.out.println("Задач с id="+commands[1]+" не найдено");
                        else
                            System.out.println(currentTask);
                    }
                else
                    System.out.println("Ошибка при вводе команды");
            }
        }
    }
}

