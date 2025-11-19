package dev.akrap.algobot.comands;

import dev.akrap.algobot.comands.service.TodoService;
import dev.akrap.algobot.model.daily.DailyTodoItem;
import dev.akrap.algobot.util.InteractionService;
import dev.akrap.algobot.model.daily.DailyTodo;
import picocli.CommandLine.Command;

@Command(
        name = "todo",
        description = "오늘 풀어야 할 문제 추천 (Pool 신규 1개 + 리뷰 3개)"
)
public class TodoCommand implements Runnable {

    private final TodoService todoService = new TodoService();
    private final InteractionService interaction = new InteractionService();

    @Override
    public void run() {
        DailyTodo dto = todoService.getTodayTodo();

        print(dto);   // <- 여기서 바로 출력

        int index = interaction.askSelectProblem(dto);
        if (index != -1) {
            interaction.openProblem(dto, index);
        }
    }

    private void print(DailyTodo todo) {
        System.out.println("==== TODAY TODO ====");
        System.out.println("날짜: " + todo.getDate());

        if (todo.getItems().isEmpty()) {
            System.out.println("(오늘 할당된 문제가 없습니다)");
            return;
        }

        System.out.println();

        int i = 1;
        for (DailyTodoItem item : todo.getItems()) {
            String status = item.isDone() ? "[완료]" : "[미완료]";
            System.out.println(i + ") 문제 " + item.getProblemId() + " " + status);
            i++;
        }
    }
}