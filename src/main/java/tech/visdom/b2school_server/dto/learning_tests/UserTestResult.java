package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class UserTestResult {

    private Long levelId;

    private LocalDateTime dateStart;

    private LocalDateTime dateFinish;

    private List<Long> answers;
}
