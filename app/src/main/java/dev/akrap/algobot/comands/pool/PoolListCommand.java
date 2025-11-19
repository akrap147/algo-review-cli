package dev.akrap.algobot.comands.pool;

import dev.akrap.algobot.comands.service.PoolService;
import dev.akrap.algobot.model.pool.PoolData;
import picocli.CommandLine;

@CommandLine.Command(
        name = "list",
        description = "현재 저장된 문제 목록을 출력합니다"
)
public class PoolListCommand implements Runnable {

    private final PoolService poolService = new PoolService();

    @Override
    public void run() {
        PoolData data = poolService.getData();

        System.out.println("=== POOL 목록 ===");

        if (data.getItems().isEmpty()) {
            System.out.println("(등록된 문제가 없습니다)");
            return;
        }

        for (int pid : data.getItems()) {
            System.out.println("- " + pid);
        }
    }
}