package h3.topic3;

import java.io.IOException;
import java.util.List;

public interface Store<E>{
    void write(E item) throws IOException;
    List<E> read() throws IOException;
}