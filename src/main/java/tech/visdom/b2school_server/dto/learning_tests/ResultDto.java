package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultDto {

    private String exercise;

    private String answer;

    private Boolean is_correct;
}
