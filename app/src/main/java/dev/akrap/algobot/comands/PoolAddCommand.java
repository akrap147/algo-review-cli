package dev.akrap.algobot.comands;


import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "add",
        description = "새 문제를 pool에 추가합니다"
)
public class PoolAddCommand implements Runnable {

    @Parameters(paramLabel = "PROBLEM_ID", description = "추가할 백준 문제 번호")
    private int problemId;

    @Override
    public void run() {
        System.out.println("[pool add] 추가된 문제: " + problemId);
    }
}