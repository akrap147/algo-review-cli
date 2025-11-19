package dev.akrap.algobot.comands.service;

import dev.akrap.algobot.model.pool.PoolData;
import dev.akrap.algobot.util.FileStorageUtil;

import java.util.List;

public class PoolService {

    private static final String POOL_FILE = "pool.json";

    public PoolData getData() {
        PoolData data = FileStorageUtil.readObject(POOL_FILE, PoolData.class);
        return data != null ? data : new PoolData();
    }

    public boolean remove(int problemId) {
        PoolData data = getData();
        boolean removed = data.getItems().removeIf(i -> i == problemId);

        if (removed) {
            FileStorageUtil.writeObject(POOL_FILE, data);
        }
        return removed;
    }

    public String add(int problemId) {
        PoolData data = FileStorageUtil.readObject(POOL_FILE, PoolData.class);
        if (data == null) data = new PoolData();

        if (data.getItems().contains(problemId)) {
            return "이미 Pool에 존재하는 문제입니다: " + problemId + "\n";
        }

        data.getItems().add(problemId);
        FileStorageUtil.writeObject(POOL_FILE, data);
        return "Pool에 추가되었습니다: " + problemId + "\n";
    }

    public Integer pickRandom() {
        PoolData data = getData();
        if (data.getItems().isEmpty()) return null;

        List<Integer> items = data.getItems();
        return items.get(new java.util.Random().nextInt(items.size()));
    }
}