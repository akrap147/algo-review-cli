package dev.akrap.algobot.comands.review;

import dev.akrap.algobot.comands.service.ReviewService;
import dev.akrap.algobot.model.review.ReviewData;
import dev.akrap.algobot.model.review.ReviewItem;
import picocli.CommandLine.Command;

import java.time.LocalDate;
import java.util.List;

@Command(name = "list", description = "오늘 리뷰해야 할 문제들을 출력합니다")
public class ReviewListCommand implements Runnable {

    private final ReviewService reviewService = new ReviewService();

    @Override
    public void run() {
        ReviewData data = reviewService.getData();
        List<ReviewItem> items = data.getItems();

        if (items == null || items.isEmpty()) {
            System.out.println("(등록된 복습 문제가 없습니다)");
            return;
        }

        System.out.println("==== REVIEW 목록 ====");
        LocalDate today = LocalDate.now();

        for (ReviewItem r : items) {
            boolean due = !r.getNextReviewDate().isAfter(today);
            String status = due ? "[오늘 복습]" : "[" + r.getNextReviewDate() + "]";

            System.out.println("- 문제 " + r.getProblemId() + " / stage=" + r.getStage() + " / next=" + r.getNextReviewDate() + "  " + status);
        }

        System.out.println();
    }
}