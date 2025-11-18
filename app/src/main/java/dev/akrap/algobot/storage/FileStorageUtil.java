package dev.akrap.algobot.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;


public class FileStorageUtil {
    private static final String DATA_DIR = ".algo-data";
    private static final ObjectMapper mapper = new ObjectMapper();


    static {
        //DATA_DIR에 폴더가 없을 경우 만들어주는게 필요하다.
        try {
            Files.createDirectories(Path.of(DATA_DIR));
        } catch (IOException e) {
            throw new RuntimeException("폴더만드는데 실패하였습니다. ", e);
        }

        // 나는 직접 손으로 추가했지만 없는경우
        String[] files = {"pool.json", "review.json", "backlog.json"};

        // 존재하지 않으면 빈 JSON 배열로 초기 생성
        for (String fileName : files) {
            File file = Path.of(DATA_DIR, fileName).toFile();
            if (!file.exists()) {
                try {
                    // 빈 리스트([]) 생성
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, Collections.emptyList());
                } catch (IOException e) {
                    throw new RuntimeException("기본 JSON 파일 생성 실패: " + fileName, e);
                }
            }
        }
    }

    public static <T> List<T> readList(String fileName, TypeReference<List<T>> type) {

        // 해당 경로의 파일을 추출한다.
        File file = Path.of(DATA_DIR, fileName).toFile();

        // 파일이 존재하면
        if (!file.exists()) {
            return Collections.emptyList();
        }

        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException("Json 파일 읽기 실패 ", e);
        }
    }

    public static <T> void writeList(String fileName, List<T> data) {

        // 읽기 기능
        File file = Path.of(DATA_DIR, fileName).toFile();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Json 파일 쓰기 실패 : " + fileName, e);
        }
    }
}
