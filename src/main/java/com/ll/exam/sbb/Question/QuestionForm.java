package com.ll.exam.sbb.Question;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수 항목입니다.")
    @Size(min = 3,max = 10)
    private String subject;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    public Question create() {
        return new Question(subject,content, LocalDateTime.now());
    }
}
