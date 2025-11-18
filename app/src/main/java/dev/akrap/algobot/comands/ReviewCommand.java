package dev.akrap.algobot.comands;

import picocli.CommandLine;

@CommandLine.Command(
    name = "review",
    description = "오늘 복습해야할 문제들을 보여줍니다."
)
public class ReviewCommand implements Runnable{

    @Override
    public void run() {
        System.out.println();
    }
}
