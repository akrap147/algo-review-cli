package dev.akrap.algobot.model.daily;

import java.time.LocalDate;
import java.util.List;

public class DailyTodo {
    private LocalDate date;
    private List<DailyTodoItem> items;

    public DailyTodo() {
    }

    public DailyTodo(LocalDate date, List<DailyTodoItem> items) {
        this.date = date;
        this.items = items;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<DailyTodoItem> getItems() {
        return items;
    }
}