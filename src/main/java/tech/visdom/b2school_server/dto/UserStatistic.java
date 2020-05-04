package tech.visdom.b2school_server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.learning_tests.ThemeDto;

import java.util.List;

@NoArgsConstructor
@Data
public class UserStatistic {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private Integer points;

    private List<ThemeDto> themesDto;

    public UserStatistic(Long id, String userName, String firstName, String lastName, Integer points) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }
}
