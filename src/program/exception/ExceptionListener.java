package program.exception;

import java.io.IOException;

public interface ExceptionListener {
    void onException(IOException e);
}
