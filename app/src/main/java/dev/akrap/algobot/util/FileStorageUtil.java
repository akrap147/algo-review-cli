package dev.akrap.algobot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.akrap.algobot.model.daily.DailyTodo;
import dev.akrap.algobot.model.pool.PoolData;
import dev.akrap.algobot.model.review.ReviewData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;


public class FileStorageUtil {
    private static final String DATA_DIR = ".algo-data";
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // JackSon에서 LocalDateTime이 직렬화되지 않아서 설정을 변경
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //DATA_DIR에 폴더가 없을 경우 만들어주는게 필요하다.
        try {
            Files.createDirectories(Path.of(DATA_DIR));
        } catch (IOException e) {
            throw new RuntimeException("폴더만드는데 실패하였습니다. ", e);
        }

        initFile("pool.json", new PoolData());
        initFile("review.json", new ReviewData());
        initFile("daily-todo.json", new DailyTodo());
    }

    // 처음에는 규격에 맞는 json파일이 없기 때문에 각각 빈칸을 만들어줘야한다 .
    private static void initFile(String fileName, Object defaultObj) {
        File file = Path.of(DATA_DIR, fileName).toFile();

        if (!file.exists()) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, defaultObj);
            } catch (IOException e) {
                throw new RuntimeException("기본 JSON 파일 생성 실패: " + fileName, e);
            }
        }
    }

    //Class를 외부에서 지정해주면 해당 DTO규격에 맞게 File에 있는 Json을 들고온다.
    public static <T> T readObject(String fileName, Class<T> tClass) {
        File file = Path.of(DATA_DIR, fileName).toFile();

        if (!file.exists()) {
            return null;
        }
        try {
            // tClass로 받아서 읽는다.
            return mapper.readValue(file, tClass);
        } catch (IOException e) {
            throw new RuntimeException("JSON 읽기 실패: " + file, e);
        }

    }

    //Class를 외부에서 지정해주면 DTO규격에 맞게 File에 글을 쓴다.
    public static <T> void writeObject(String fileName, T data) {
        File file = Path.of(DATA_DIR, fileName).toFile();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("JSON 쓰기 실패: " + fileName, e);
        }

    }
}
