package ru.donik.t1.service;

import ru.donik.t1.annotation.WeylandWatchingYou;
import ru.donik.t1.model.Command;
import org.springframework.stereotype.Service;
import ru.donik.t1.model.Priority;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class  DefaultCommandService {

    private final Deque<Command> commandQueue = new ArrayDeque<>();
    private final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    @WeylandWatchingYou(topic = "bishop-command-audit")
    public void executeCommand(Command command) {
        if (command.getPriority() == Priority.CRITICAL) {
            immediateExecute(command);
        } else {
            scheduleTask(command);
        }
    }

    private void immediateExecute(Command command) {
        System.out.println("Немедленное выполнение команды: " + command.getDescription());
    }

    private void scheduleTask(Command command) {
        if (commandQueue.size() >= 100) {
            throw new IllegalStateException("Очередь команд переполнена.");
        }
        commandQueue.add(command);
        threadPoolExecutor.execute(this::processQueue);
    }

    private void processQueue() {
        while (!commandQueue.isEmpty()) {
            Command task = commandQueue.pop();
            System.out.println("Выполнение отложенной команды: " + task.getDescription());
        }
    }
}
