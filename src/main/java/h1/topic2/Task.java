package h1.topic2;

public class Task {
    private String id;
    private String description;
    private String author;
    private String name;
    private int storyPoint;

    public Task(String id, String description, String author, String name, int storyPoint) {
        this.id = id;
        this.description = description;
        this.author = author;
        this.name = name;
        this.storyPoint = storyPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }
}
