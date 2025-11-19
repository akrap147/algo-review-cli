package dev.akrap.algobot.comands.pool;

import dev.akrap.algobot.comands.service.PoolService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "remove",
        description = "Pool에서 특정 문제를 제거합니다"
)
public class PoolRemoveCommand implements Runnable {

    @Parameters(paramLabel = "PROBLEM_ID", description = "제거할 백준 문제 번호")
    private int problemId;

    private final PoolService poolService = new PoolService();

    @Override
    public void run() {
        boolean removed = poolService.remove(problemId);

        if (removed) {
            System.out.println("Pool에서 문제 " + problemId + " 제거됨\n");
        } else {
            System.out.println("Pool에 해당 문제(" + problemId + ")가 존재하지 않습니다.\n");
        }
    }
}