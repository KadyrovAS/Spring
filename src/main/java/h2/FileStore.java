package h2;

import java.io.IOException;
import java.util.List;

public class FileStore<E> implements Store{
    List<E> dataBase;

    public FileStore() {
    }

    public void setDataBase(List<E> dataBase) {
        this.dataBase = dataBase;
    }

    public FileStore(List<E> dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void write(Object item) throws IOException {

    }

    @Override
    public List<E> read() throws IOException{
        return this.dataBase;
    }
}