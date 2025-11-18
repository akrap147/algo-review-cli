package dev.akrap.algobot.comands.pool;

import dev.akrap.algobot.storage.FileStorageUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Command(
        name = "remove",
        description = "Pool에서 특정 문제를 제거합니다"
)
public class PoolRemoveCommand implements Runnable {

    private static final String FILE_NAME = "pool.json";

    @Parameters(paramLabel = "문제번호", description = "Pool에서 제거할 문제 번호")
    private Integer problemNumber;

    @Override
    public void run() {
        // 1) 현재 Pool 로딩
        List<Integer> pool = FileStorageUtil.readList(FILE_NAME, new TypeReference<>() {
        });

        // 2) 비어있는지 체크
        if (pool.isEmpty()) {
            System.out.println("Pool이 비어 있습니다. 제거할 문제가 없습니다.");
            return;
        }

        // 3) 리스트에 존재하는지 검증
        if (!pool.contains(problemNumber)) {
            System.out.println("해당 문제(" + problemNumber + ")는 Pool에 없습니다.");
            return;
        }

        // 4) 문제 제거
        pool.remove(problemNumber);
        FileStorageUtil.writeList(FILE_NAME, pool);

        System.out.println("문제 (" + problemNumber + ") 제거되었습니다.");
    }
}