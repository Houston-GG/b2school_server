package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserLevelDto {

    private Boolean successfullyPassed;

    private Integer exerciseCount;

    private Integer successExerciseCount;

    private List<ResultDto> results;
}
