package com.edumind.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Data
public class EmotionLog {

    private Long id;// 评价ID（数据库主键）

    @Pattern(regexp = "^\\d{7}$", message = "Student ID must be a 7-digit positive integer")
    @NotNull(message = "Student ID cannot be empty")
    private String studentId;// 学生ID

    @NotNull(message = "The review content cannot be empty")
    @Size(min = 20, max = 5000, message = "The length of the evaluation content must be between 20 and 5000 words")
    private String content;             // 自我评价内容

    private String sentiment;           // 情绪标签（positive / negative / neutral）

    private double sentimentScore;      // 情感强度分数（例如 -1 ~ 1）

    private String suggestion;

    private LocalDate logDate;          // 每日一条

    private LocalDateTime createTime;   // 提交时间

}
