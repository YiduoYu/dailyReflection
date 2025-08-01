package com.edumind.controller;

import com.edumind.common.AjaxResult;
import com.edumind.domain.EmotionLog;
import com.edumind.service.IEmotionLogService;
import com.edumind.util.EvaluationTrendPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
public class EmotionLogController {

    @Autowired
    private IEmotionLogService emotionLogService;

    /**
     * 学生提交每日一句心情记录（系统自动分析情绪并保存）
     */
    @PostMapping("/submit")
    public AjaxResult submitLog(@RequestBody @Validated EmotionLog log) {
        boolean success = emotionLogService.submitEvaluation(log);
        if (!success) {
            return AjaxResult.error("提交失败，请稍后再试");
        }
        return AjaxResult.success("记录成功，系统已分析情绪并生成建议");
    }

    /**
     * 查询某学生最近7天的情绪趋势（用于前端图表展示）
     */
    @GetMapping("/trend/{studentId}")
    public AjaxResult getTrend(@PathVariable String studentId) {
        List<EvaluationTrendPoint> trendPoints = emotionLogService.getTrendPoints(studentId);
        return AjaxResult.success(trendPoints);
    }

    /**
     * 获取某学生的全部心情记录（用于学生个人回顾）
     */
    @GetMapping("/history/{studentId}")
    public AjaxResult getAllLogs(@PathVariable String studentId) {
        List<EmotionLog> logs = emotionLogService.getAllLogsByAuth(studentId);
        return AjaxResult.success(logs);
    }
}