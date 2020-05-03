package tech.visdom.b2school_server.model.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.learning_tests.ThemeDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEME_ID")
    private List<Level> levels;

    public ThemeDto toDto() {

        ThemeDto themeDto = new ThemeDto();
        themeDto.setName(this.name);
        themeDto.setDescription(this.description);
        themeDto.setLevels(levels.stream().map(Level::toDto).collect(Collectors.toList()));
        return themeDto;
    }
}
