package dev.akrap.algobot.comands.service;

import dev.akrap.algobot.model.review.ReviewData;
import dev.akrap.algobot.model.review.ReviewItem;
import dev.akrap.algobot.util.FileStorageUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    private static final String REVIEW_FILE = "review.json";

    public ReviewData getData() {
        ReviewData data = FileStorageUtil.readObject(REVIEW_FILE, ReviewData.class);
        return data != null ? data : new ReviewData();
    }

    public String updateOrCreate(int problemId) {
        ReviewData data = getData();
        ReviewItem item = data.find(problemId);

        if (item == null) {
            item = new ReviewItem(problemId);
            data.getItems().add(item);
            FileStorageUtil.writeObject(REVIEW_FILE, data);
            return "Review 등록: 문제 " + problemId + " / stage=1 / next=" + item.getNextReviewDate() + "\n";
        } else {
            item.completeReview();
            FileStorageUtil.writeObject(REVIEW_FILE, data);
            return "Review 갱신: 문제 " + problemId + " / stage=" + item.getStage() + " / next=" + item.getNextReviewDate() + "\n";
        }
    }

    public List<Integer> getDueProblems(LocalDate today) {
        List<ReviewItem> items = getData().getItems();

        List<Integer> result = new ArrayList<>();

        for (ReviewItem r : items) {
            if (!r.getNextReviewDate().isAfter(today)) {
                result.add(r.getProblemId());
            }
        }

        return result;
    }
}