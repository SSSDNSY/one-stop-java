package reactor.features;

import java.util.List;

public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);

    void processComplete();
}