package com.ll.exam.sbb.Question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.user.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser author;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Answer> answerList=new ArrayList<>();

    public Question(String subject, String content, LocalDateTime createDate) {
        this.subject=subject;
        this.content=content;
        this.createDate=createDate;
        this.author=null;
    }
    public Question(String subject, String content, LocalDateTime createDate,SiteUser author) {
        this.subject=subject;
        this.content=content;
        this.createDate=createDate;
        this.author=author;
    }

}
