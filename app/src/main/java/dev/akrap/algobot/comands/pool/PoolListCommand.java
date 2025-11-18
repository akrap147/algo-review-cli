package dev.akrap.algobot.comands.pool;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.akrap.algobot.storage.FileStorageUtil;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "list",
        description = "현재 저장된 문제 목록을 출력합니다"
)
public class PoolListCommand implements Runnable {

    private static final String FILE_NAME = "pool.json";

    @Override
    public void run() {
        List<Integer> pool = FileStorageUtil.readList(FILE_NAME, new TypeReference<>() {
        });

        if(pool.isEmpty()){
            System.out.println("POOL이 비어있습니다. 문제를 추가해주세요");
            System.out.println("예시 : algo pool add 1000");
            return;
        }

        System.out.println("현재 문제 등록 (" + pool.size() + ")" );
        System.out.println("---------------------");

        for(Integer problem : pool){
            System.out.println("- " + problem);
        }

    }
}