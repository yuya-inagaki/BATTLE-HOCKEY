package common;

import java.io.IOException;

public interface Communicator {
    GameInfo run(int playerY)throws IOException;
    void init()throws IOException;
}
