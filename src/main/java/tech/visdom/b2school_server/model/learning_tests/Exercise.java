package tech.visdom.b2school_server.model.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.learning_tests.ExerciseDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "COMPLEXITY", nullable = false)
    private Integer complexity;

    @Column(name = "LEVEL_ID", nullable = false)
    private Long levelId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID")
    private List<Answer> answers;

    public ExerciseDto toDto() {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(this.id);
        exerciseDto.setName(this.name);
        exerciseDto.setText(this.text);
        exerciseDto.setComplexity(this.complexity);
        exerciseDto.setAnswers(answers.stream().map(Answer::toDto).collect(Collectors.toList()));
        return exerciseDto;
    }
}
