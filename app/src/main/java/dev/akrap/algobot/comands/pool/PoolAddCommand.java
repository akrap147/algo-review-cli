package dev.akrap.algobot.comands.pool;

import dev.akrap.algobot.comands.service.PoolService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "add",
        description = "새 문제를 pool에 추가합니다"
)
public class PoolAddCommand implements Runnable {

    @Parameters(paramLabel = "PROBLEM_ID", description = "추가할 백준 문제 번호")
    private int problemId;

    private final PoolService poolService = new PoolService();

    @Override
    public void run() {
        String message = poolService.add(problemId);
        System.out.print(message);
    }
}