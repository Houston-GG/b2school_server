package tech.visdom.b2school_server.model.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.learning_tests.AnswerDto;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "IS_CORRECT", nullable = false)
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "EXERCISE_ID", nullable = false)
    private Exercise exercise;

    public AnswerDto toDto() {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(this.id);
        answerDto.setText(this.text);
        answerDto.setIsCorrect(this.isCorrect);
        return answerDto;
    }
}
