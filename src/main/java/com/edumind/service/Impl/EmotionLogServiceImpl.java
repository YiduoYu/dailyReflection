package com.edumind.service.Impl;

import com.edumind.config.BaiduNlpService;
import com.edumind.domain.SentimentResult;
import com.edumind.domain.EmotionLog;
import com.edumind.mapper.EmotionLogMapper;
import com.edumind.service.IEmotionLogService;
import com.edumind.util.EvaluationTrendPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmotionLogServiceImpl implements IEmotionLogService {

    @Autowired
    private EmotionLogMapper emotionLogMapper;

    @Autowired
    private BaiduNlpService baiduNlpService;

    @Override
    public boolean submitEvaluation(EmotionLog evaluation) {
        String studentId = evaluation.getStudentId();
        String content = evaluation.getContent();
        String suggestion = evaluation.getSuggestion();



//        if (!emotionLogMapper.selectTodayByStudent(studentId).isEmpty()) {
//            throw new RuntimeException("今日已提交过，请明天再来！");
//        }

        // 重复内容检查
        if (!emotionLogMapper.selectByContentAndStudent(studentId, content).isEmpty()) {
            throw new RuntimeException("请勿重复提交相同内容！");
        }

        // 情绪分析
        SentimentResult result = BaiduNlpService.analyzeSentiment(content);
        evaluation.setSentiment(result.getSentiment());
        evaluation.setSentimentScore(result.getScore());
        evaluation.setSuggestion(result.getSuggestion());
        evaluation.setLogDate(LocalDate.now());
        evaluation.setCreateTime(LocalDateTime.now());

        emotionLogMapper.insertEvaluation(evaluation);
        return true;
    }

    @Override
    public List<EmotionLog> getRecentEvaluations(String studentId, int limit) {
        return emotionLogMapper.selectRecentEvaluations(studentId, limit);
    }

    @Override
    public List<EmotionLog> getAllEvaluations(String studentId) {
        return emotionLogMapper.selectAllEvaluations(studentId);
    }

    @Override
    public List<EvaluationTrendPoint> getTrendPoints(String studentId) {
        return emotionLogMapper.selectTrendPointsByStudent(studentId);
    }

    @Override
    public List<EmotionLog> getAllLogsByAuth(String studentId) {
        return emotionLogMapper.selectAllEvaluations(studentId);
    }
}