package dev.akrap.algobot.model.pool;

import java.util.ArrayList;
import java.util.List;

public class PoolData {
    private List<Integer> items = new ArrayList<>();

    public List<Integer> getItems() {
        return items;
    }
    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public void add(int problemId) {
        if (!items.contains(problemId)) {
            items.add(problemId);
        }
    }

    public boolean remove(int problemId) {
        return items.remove((Integer) problemId);
    }
}