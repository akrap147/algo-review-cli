package dev.akrap.algobot.comands.done;

import dev.akrap.algobot.comands.service.DoneService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "done",
        description = "문제를 풀었다고 표시하고 다음 복습 날짜를 업데이트합니다."
)
public class DoneCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "PROBLEM_ID", description = "완료한 문제 번호")
    private int problemId;

    private final DoneService doneService = new DoneService();

    @Override
    public void run() {
        System.out.print(doneService.markDone(problemId));   // 서비스에서 문구를 조합해서 리턴하도록 설계
    }
}