package dev.akrap.algobot.model.daily;

public class DailyTodoItem {
    private int problemId;
    private boolean done;

    public DailyTodoItem() {}

    public DailyTodoItem(int problemId) {
        this.problemId = problemId;
        this.done = false;
    }
    public DailyTodoItem(int problemId, boolean done) {
        this.problemId = problemId;
        this.done = done;
    }
    public int getProblemId() { return problemId; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }
}