package com.edumind.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationTrendPoint {
    private String date;
    private Double sentimentScore;
}
