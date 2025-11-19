package dev.akrap.algobot.comands.service;

public class DoneService {
    private final PoolService poolService = new PoolService();
    private final ReviewService reviewService = new ReviewService();
    private final TodoService todoService = new TodoService();

    public String markDone(int problemId) {

        StringBuilder sb = new StringBuilder();

        // 1) Pool에서 제거
        if (poolService.remove(problemId)) {
            sb.append("Pool에서 문제 ").append(problemId).append(" 제거됨\n");
        }

        // 2) Review에 반영
        sb.append(reviewService.updateOrCreate(problemId));

        // 3) DailyTodo에 완료 처리
        if (todoService.markDone(problemId)) {
            sb.append("오늘의 Todo에서 완료 처리됨\n");
        }

        return sb.toString();
    }

}
