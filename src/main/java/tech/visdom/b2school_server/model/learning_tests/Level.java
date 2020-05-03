package tech.visdom.b2school_server.model.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.learning_tests.LevelDto;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LEVEL_NUMBER", nullable = false)
    private Integer levelNumber;

    @Column(name = "THEME_ID", nullable = false)
    private Long themId;

    public LevelDto toDto() {
        LevelDto levelDto = new LevelDto();
        levelDto.setId(this.id);
        levelDto.setName(this.name);
        levelDto.setLevelNumber(this.levelNumber);
        return levelDto;
    }
}
