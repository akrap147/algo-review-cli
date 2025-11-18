package dev.akrap.algobot.comands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "list",
        description = "현재 저장된 문제 목록을 출력합니다"
)
public class PoolListCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("[pool list] 문제 목록 출력 (아직 저장 기능 연결 전)");
    }
}