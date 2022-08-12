package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.Question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Answer(String content, LocalDateTime createDate, Question question) {
        this.content=content;
        this.createDate=createDate;
        this.question=question;
    }
    public Answer(String content, LocalDateTime createDate) {
        this.content=content;
        this.createDate=createDate;
    }
}
