package logging;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    private static volatile Logger instance;
    private int lineNumber;
    private List<String> logBuffer;
    private AtomicBoolean isStillRunning;
    private ILogPolicy policy;
    private Thread loggingDaemon;
    private final ReentrantLock lock = new ReentrantLock();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Logger() {
        if (instance != null) {
            throw new IllegalStateException("Logger already constructed");
        }
    }

    public void initialize(ILogPolicy policy, String path) {
        lineNumber = 0;
        logBuffer = new ArrayList<>();
        isStillRunning = new AtomicBoolean(false);
        try {
            FileChannel.open(Paths.get(path), StandardOpenOption.WRITE).truncate(0).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.policy = policy;
        if (this.policy.openOutputStream(path)) {
            isStillRunning.set(true);
            loggingDaemon = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        do {
                            Thread.sleep(50);
                            if (logBuffer.size() > 0) {
                                if (!lock.tryLock(50, TimeUnit.MILLISECONDS)) {
                                    continue;
                                } else {
                                    for (String log : logBuffer) {
                                        policy.write(log);
                                    }
                                    logBuffer.clear();
                                    lock.unlock();
                                }
                            }
                        } while (isStillRunning.get() || logBuffer.size() > 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            loggingDaemon.start();
        }
        this.print("Logger service started", SeverityType.INFO);
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void deinitialize() {
        this.print("Logger service shutting down", SeverityType.INFO);
        isStillRunning.set(false);
        try {
            loggingDaemon.join();
            policy.closeOutputStream();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print(String msg, SeverityType severity) {
        Date currentDate = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(lineNumber++).append(" ");
        sb.append(currentDate.toString()).append(" ");
        sb.append(Thread.currentThread().getName()).append(" ");

        switch (severity) {
            case INFO:
                sb.append("INFO: ");
                break;
            case DEBUG:
                sb.append("DEBUG: " + ANSI_CYAN);
                break;
            case ERROR:
                sb.append("ERROR: " + ANSI_RED);
                break;
            case FATAL:
                sb.append("FATAL: " + ANSI_PURPLE);
                break;
            case WARNING:
                sb.append("WARNING: " + ANSI_YELLOW);
                break;
            case PERFORMANCE:
                sb.append("PERFORMANCE: " + ANSI_GREEN);
                break;
        }
        sb.append(msg).append(ANSI_RESET);
        sb.append("\n");
        lock.lock();
        logBuffer.add(sb.toString());
        lock.unlock();
    }
}
