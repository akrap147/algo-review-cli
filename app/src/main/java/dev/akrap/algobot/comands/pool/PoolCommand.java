package dev.akrap.algobot.comands.pool;


import picocli.CommandLine.Command;

@Command(
        name = "pool",
        description = "새 문제 저장소(pool)을 관리합니다",
        subcommands = {
                PoolListCommand.class,
                PoolAddCommand.class,
                PoolRemoveCommand.class
        }
)
public class PoolCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Try: algo pool list 또는 algo pool add <문제번호>");
    }
}