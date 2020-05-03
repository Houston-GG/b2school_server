package tech.visdom.b2school_server.model.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_level")
public class UserLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "LEVEL_ID", nullable = false)
    private Long levelId;

    @Column(name = "DATE_START", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "DATE_FINISH", nullable = false)
    private LocalDateTime dateFinish;

    @Column(name = "SUCCESSFULLY_PASSED", nullable = false)
    private Boolean successfullyPassed;

    @Column(name = "EXERCISE_COUNT", nullable = false)
    private Integer exerciseCount;

    @Column(name = "SUCCESS_EXERCISE_COUNT", nullable = false)
    private Integer successExerciseCount;

}
