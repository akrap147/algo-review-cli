package dev.akrap.algobot.model.review;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ReviewItem {

    private int problemId;                 // 문제 번호
    private int stage;                     // 현재 복습 단계(1, 2, 3...)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextReviewDate;      // 다음 복습 날짜

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;           // 처음 공부한 날짜

    public ReviewItem() {

    }

    public ReviewItem(int problemId) {
        this.problemId = problemId;
        this.stage = 1;
        this.createdAt = LocalDate.now();
        this.nextReviewDate = LocalDate.now().plusDays(1); // stage=1 => +1일
    }

    public void completeReview() {
        stage++;
        int nextGap = getGapByStage(stage);
        this.nextReviewDate = LocalDate.now().plusDays(nextGap);
    }


    // stage별로 며칠뒤에 하게 될지 정한다.
    private int getGapByStage(int stage) {
        return switch (stage) {
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 7;
            case 4 -> 14;
            case 5 -> 30;
            default -> 30;
        };
    }

    public int getProblemId() {
        return problemId;
    }

    public int getStage() {
        return stage;
    }

    public LocalDate getNextReviewDate() {
        return nextReviewDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setNextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}