# DailyReflection: Student Emotion Journaling with Sentiment Analysis

A Spring Boot service where students record a one-sentence daily reflection. Each entry is classified by sentiment with the Baidu NLP API and stored with a sentiment score and a short suggestion. The weekly trend of those scores can be plotted to spot prolonged negative mood.

This is a follow-up to [EduMind](https://github.com/YiduoYu/edumind-evaluation), adding student accounts and daily journaling.

## Features
- Student registration and login (`auth` table, linked to journal entries by student ID)
- Daily reflection submission with validation (non-empty content, valid student ID)
- Sentiment class, intensity score, and suggestion generated for every entry
- Seven-day sentiment trend for each student, plotted with ECharts
- Alerts after consecutive negative entries (in progress)

## API
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register a student |
| `POST` | `/api/auth/login` | Log in |
| `POST` | `/api/log/submit` | Submit a reflection |
| `GET` | `/api/log/history/{studentId}` | Reflection history |
| `GET` | `/api/log/trend/{studentId}` | Sentiment trend over the past week |

Example request and response:
```json
POST /api/log/submit
{ "studentId": "20240001", "content": "A bit tired today, but I finished everything I planned." }

{ "success": true,
  "data": { "sentiment": "positive", "score": 0.67,
            "suggestion": "Keep up the positive attitude and remember to rest." } }
```

## Data model
`emotion_log`: `id`, `student_id` (FK → `auth.student_id`), `content`, `sentiment`, `sentiment_score`, `suggestion`, `log_date`, `create_time`

## Running
Requires JDK 8+, Maven, and MySQL.
```bash
export DB_USERNAME=root
export DB_PASSWORD=your-mysql-password
export BAIDU_APP_ID=...  BAIDU_API_KEY=...  BAIDU_SECRET_KEY=...
mvn spring-boot:run
```
Create a MySQL database named `dailyreflection` first. The server starts on port 8080.

## Tech stack
Spring Boot 2.7 · MyBatis · MySQL · Baidu AI NLP SDK · ECharts

## License
MIT
