package dev.akrap.algobot.comands.service;


import dev.akrap.algobot.model.daily.DailyTodo;
import dev.akrap.algobot.model.daily.DailyTodoItem;
import dev.akrap.algobot.util.FileStorageUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoService {

    private static final String DAILY_FILE = "daily-todo.json";
    private final ReviewService reviewService = new ReviewService();
    private final PoolService poolService = new PoolService();

    public DailyTodo getTodayTodo() {
        DailyTodo dto = FileStorageUtil.readObject(DAILY_FILE, DailyTodo.class);
        LocalDate today = LocalDate.now();

        // 없으면 새로 생성
        if (dto == null || dto.getDate() == null || !dto.getDate().equals(today)) {
            dto = createNew(today);
            FileStorageUtil.writeObject(DAILY_FILE, dto);
        }

        return dto;
    }

    private DailyTodo createNew(LocalDate today) {
        Integer newProblem = poolService.pickRandom();
        List<Integer> reviewProblems = reviewService.getDueProblems(today);

        List<DailyTodoItem> result = new ArrayList<>();

        if (newProblem != null)
            result.add(new DailyTodoItem(newProblem, false));

        for (int pid : reviewProblems)
            result.add(new DailyTodoItem(pid, false));

        return new DailyTodo(today, result);
    }

    // ---- 문제 완료 처리 ----
    public boolean markDone(int problemId) {
        DailyTodo dto = getTodayTodo();

        boolean found = false;
        for (DailyTodoItem item : dto.getItems()) {
            if (item.getProblemId() == problemId) {
                item.setDone(true);
                found = true;
                break;
            }
        }

        if (found) {
            FileStorageUtil.writeObject(DAILY_FILE, dto);
        }

        return found;
    }


}
