package com.edumind.config;

import com.baidu.aip.nlp.AipNlp;
import com.edumind.domain.SentimentResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class BaiduNlpService {

    private static final String APP_ID = "***REMOVED***";
    private static final String API_KEY = "***REMOVED***";
    private static final String SECRET_KEY = "***REMOVED***";

    private static final AipNlp client;

    static {
        client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public static SentimentResult analyzeSentiment(String content) {
        JSONObject response = client.sentimentClassify(content, null);

        JSONArray items = response.optJSONArray("items");
        if (items != null && items.length() > 0) {
            JSONObject item = items.getJSONObject(0);
            int sentimentInt = item.getInt("sentiment"); // 0: negative, 1: neutral, 2: positive
            double confidence = item.getDouble("confidence");

            String sentiment;
            switch (sentimentInt) {
                case 0: sentiment = "negative"; break;
                case 2: sentiment = "positive"; break;
                default: sentiment = "neutral"; break;
            }

            double score = (sentimentInt - 1) * confidence / 100.0;
            return new SentimentResult(sentiment, score);
        }

        return new SentimentResult("neutral", 0.0);
    }
}