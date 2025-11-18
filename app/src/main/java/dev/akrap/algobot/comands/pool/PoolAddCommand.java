package dev.akrap.algobot.comands.pool;


import com.fasterxml.jackson.core.type.TypeReference;
import dev.akrap.algobot.storage.FileStorageUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.List;

@Command(
        name = "add",
        description = "새 문제를 pool에 추가합니다"
)
public class PoolAddCommand implements Runnable {

    @Parameters(paramLabel = "PROBLEM_ID", description = "추가할 백준 문제 번호")
    private int problemId;

    private static final String FILE_NAME = "pool.json";

    // add를하면 문제가 추가되는 것
    @Override
    public void run() {

        List<Integer> pool = FileStorageUtil.readList(
                FILE_NAME,
                new TypeReference<>() {
                }
                // TpyeReference는 추상 클래스라 -> 인스턴스 생성하려면 익명 클래스로 가야함 그래서 {} 빈 람다(익명클래스) 깡통이 있는거임
        );

        if (pool.contains(problemId)) {
            System.out.println("이미 존재하는 문제입니다: " + problemId);
        }

        // 다시 새로운 깡통 ArrayList를 만들어서 추가한다.
        pool = new ArrayList<>();
        pool.add(problemId);

        FileStorageUtil.writeList(FILE_NAME, pool);
        System.out.println("추가 완료: " + problemId);
    }
}