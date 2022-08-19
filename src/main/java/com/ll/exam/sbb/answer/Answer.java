package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.user.SiteUser;
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

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    public Answer(String content, LocalDateTime createDate, Question question) {
        this.content=content;
        this.createDate=createDate;
        this.question=question;
        this.author=null;
    }
    public Answer(String content, LocalDateTime createDate, Question question,SiteUser author) {
        this.content=content;
        this.createDate=createDate;
        this.question=question;
        this.author=author;
    }
    public Answer(String content, LocalDateTime createDate) {
        this.content=content;
        this.createDate=createDate;
        this.author=null;
    }

    public void addAnswer(Question q) {
        q.getAnswerList().add(this);
        this.setQuestion(q);
    }
}
