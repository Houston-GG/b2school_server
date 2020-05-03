package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnswerDto {

    private Long id;

    private String text;

    private Boolean isCorrect;
}
