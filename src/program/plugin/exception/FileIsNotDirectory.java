package program.plugin.exception;

public class FileIsNotDirectory extends RuntimeException {
    public FileIsNotDirectory(String message) {
        super(message);
    }
}
