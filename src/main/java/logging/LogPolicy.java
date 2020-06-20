package logging;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogPolicy implements ILogPolicy {
    private OutputStream outputStream;

    @Override
    public boolean openOutputStream(String filePath) {
        try {
            outputStream = new BufferedOutputStream(
                    Files.newOutputStream(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.WRITE));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void closeOutputStream() {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String msg) {
        try {
            byte[] bytes = msg.getBytes();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            System.out.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
