package logging;

public interface ILogPolicy {
    boolean openOutputStream(String filePath);
    void closeOutputStream();
    void write(String msg);
}
