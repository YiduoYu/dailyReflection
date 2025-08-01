package com.edumind.mapper;

import com.edumind.domain.EmotionLog;
import com.edumind.util.EvaluationTrendPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmotionLogMapper {

    // 插入一条新的情绪记录
    void insertEvaluation(EmotionLog evaluation);

    // 查询某学生当天是否已有记录（防止重复提交）
    List<EmotionLog> selectTodayByStudent(@Param("studentId") String studentId);

    // 查询某学生全部记录（按时间倒序）
    List<EmotionLog> selectAllEvaluations(@Param("studentId") String studentId);

    // 查询最近7天的情绪趋势图数据
    List<EvaluationTrendPoint> selectTrendPointsByStudent(String studentId);

    List<EmotionLog> selectByContentAndStudent(@Param("studentId") String studentId,
                                               @Param("content") String content);

    List<EmotionLog> selectRecentEvaluations(String studentId, int limit);
}