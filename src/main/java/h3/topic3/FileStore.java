package h3.topic3;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileStore<E> implements Store {
    final String DATA_BASE_PATH = "accounts.json";
    List<E> dataBase;

    public FileStore() {
    }

    public void setDataBase(List<E> dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void write(Object item) throws IOException {
        String json = new Gson().toJson(dataBase);
        Files.writeString(Path.of(DATA_BASE_PATH), json);
    }

    @Override
    public List<E> read() throws IOException{
        return this.dataBase;
    }
}