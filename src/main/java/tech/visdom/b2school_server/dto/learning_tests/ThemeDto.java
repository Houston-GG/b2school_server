package tech.visdom.b2school_server.dto.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ThemeDto {

    private String name;

    private String description;

    private List<LevelDto> levels;
}
