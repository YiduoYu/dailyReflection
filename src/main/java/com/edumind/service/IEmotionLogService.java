package com.edumind.service;

import com.edumind.domain.EmotionLog;
import com.edumind.util.EvaluationTrendPoint;

import java.util.List;

public interface IEmotionLogService {

    // 提交一句话记录 + 情绪分析
    boolean submitEvaluation(EmotionLog evaluation);

    // 获取最近 N 条记录
    List<EmotionLog> getRecentEvaluations(String studentId, int limit);

    // 获取所有记录（用于图表）
    List<EmotionLog> getAllEvaluations(String studentId);

    // 获取趋势点（情绪值+时间）
    List<EvaluationTrendPoint> getTrendPoints(String studentId);

    List<EmotionLog> getAllLogsByAuth(String studentId);
}