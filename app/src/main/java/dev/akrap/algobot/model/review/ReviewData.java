package dev.akrap.algobot.model.review;

import java.util.ArrayList;
import java.util.List;

public class ReviewData {

    private List<ReviewItem> items = new ArrayList<>();

    public ReviewData() {}

    public ReviewData(List<ReviewItem> items) {
        this.items = items;
    }

    public List<ReviewItem> getItems() {
        return items;
    }

    public void setItems(List<ReviewItem> items) {
        this.items = items;
    }

    // 문제 ID로 ReviewItem을 찾는 기능 (자주 쓰일 예정)
    public ReviewItem find(int problemId) {
        for (ReviewItem item : items) {
            if (item.getProblemId() == problemId) {
                return item;
            }
        }
        return null;
    }

    // 새로운 문제 추가
    public void addNew(int problemId) {
        this.items.add(new ReviewItem(problemId));
    }

    // 이미 존재하면 갱신 + stage 증가
    public void updateExisting(ReviewItem item) {
        item.completeReview();
    }

    @Override
    public String toString() {
        return "ReviewData{" +
                "items=" + items +
                '}';
    }
}