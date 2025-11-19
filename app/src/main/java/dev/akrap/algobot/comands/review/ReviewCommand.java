package dev.akrap.algobot.comands.review;

import picocli.CommandLine;

@CommandLine.Command(
        name = "review",
        description = "오늘 복습해야할 문제들을 보여줍니다.",
        subcommands = {
                ReviewListCommand.class
        }
)

// 제일 핵심 Logic -> 내가 오늘 푼 문제를
// 1일 3일 7일 14일 뒤에 다시 소개시켜준다.

public class ReviewCommand implements Runnable {

    @Override
    public void run() {
        System.out.println();
    }
}
