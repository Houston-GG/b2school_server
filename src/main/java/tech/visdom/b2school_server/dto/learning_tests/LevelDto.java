package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LevelDto {

    private Long id;

    private String name;

    private Integer levelNumber;

    private UserLevelDto userLevelDto;
}
