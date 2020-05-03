package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ExerciseDto {

    private Long id;

    private String name;

    private String text;

    private Integer complexity;

    private List<AnswerDto> answers;
}
