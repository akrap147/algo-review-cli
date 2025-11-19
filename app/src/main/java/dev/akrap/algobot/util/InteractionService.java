package dev.akrap.algobot.util;


import dev.akrap.algobot.model.daily.DailyTodo;
import dev.akrap.algobot.model.daily.DailyTodoItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InteractionService {

    public int askSelectProblem(DailyTodo todo) {
        if (todo == null || todo.getItems().isEmpty()) {
            return -1;
        }

        int size = todo.getItems().size();
        System.out.print("\n열 문제 번호 입력 (1~" + size + ", Enter = skip): ");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();

            if (input == null || input.trim().isEmpty()) {
                return -1;
            }

            int choice = Integer.parseInt(input.trim());
            if (choice < 1 || choice > size) {
                System.out.println("잘못된 입력입니다.");
                return -1;
            }

            return choice - 1;

        } catch (Exception e) {
            System.out.println("입력 오류: " + e.getMessage());
            return -1;
        }
    }

    public void openProblem(DailyTodo todo, int index) {
        if (index < 0 || index >= todo.getItems().size()) return;

        DailyTodoItem item = todo.getItems().get(index);
        int pid = item.getProblemId();

        String url = "https://www.acmicpc.net/problem/" + pid;
        System.out.println("열기: " + url);

        BrowseUtil.openUrl(url);
    }
}