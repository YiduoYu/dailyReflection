🎓 Student Emotion Logging & Sentiment Analysis System

一个基于 Spring Boot 的学生情绪日记记录与情绪分析平台

📌 项目简介 | Introduction

本项目旨在构建一个集 自然语言处理 (NLP) 与 情绪趋势可视化 于一体的学生情绪监测系统。学生每日提交一条短句式心情记录，系统将自动完成情绪分析并生成心理建议，帮助教师或系统及时发现潜在风险，辅助心理健康管理。

This system allows students to submit short daily reflections, analyzes their sentiment using AI/NLP, and visualizes emotional trends. It aims to support student mental health by identifying prolonged negative sentiment and generating alerts if necessary.

⸻

✨ 项目功能 | Features

模块	描述
📝 情绪记录提交	学生可每日提交一句话心情内容
💡 情绪分析	接入百度 NLP API 返回情感分类、情绪强度
📈 趋势可视化	查询最近一周情绪分数趋势
🚨 消极情绪预警	若连续两次为负面情绪，将标记提醒（开发中）
✅ 表单校验	内容非空、学号合法、每天可多次提交
🔐 权限管理	基于 auth 表的 studentId 与情绪日志建立外键关联


⸻

🗂️ 项目结构 | Project Structure

src/
├── controller/         # 情绪日志控制器
├── service/            # 业务逻辑层
├── mapper/             # MyBatis 映射接口
├── domain/             # 实体类定义 EmotionLog, Auth 等
└── util/               # 工具类（如趋势图点）


⸻

📊 数据库设计 | Database Design

表：auth（学生注册信息）

字段名	类型	描述
student_id	VARCHAR(20)	学号（唯一）
name	VARCHAR	姓名
…	…	其他注册信息

表：emotion_log（情绪日志）

字段名	类型	描述
id	BIGINT	主键，自增
student_id	VARCHAR(20)	外键，关联 auth.student_id
content	TEXT	学生提交的情绪文本
sentiment	VARCHAR	情绪分类（positive/negative/neutral）
sentiment_score	DOUBLE	情绪强度分数
suggestion	TEXT	系统建议
log_date	DATE	日志日期
create_time	DATETIME	创建时间


⸻

🚀 使用说明 | Getting Started

✅ 运行步骤
1.	克隆本项目
git clone https://github.com/your-username/student-emotion-log.git
2.	配置数据库（MySQL）
•	创建数据库 daily_reflection
•	导入建表 SQL（已提供）
3.	修改 application.yml 数据库配置
4.	启动 Spring Boot 项目
5.	使用 Apifox/Postman 调试接口：
•	POST /api/log/submit
•	GET /api/log/history/{studentId}
•	GET /api/log/trend/{studentId}

⸻

📌 示例提交接口

POST /api/log/submit

{
"studentId": "20240001",
"content": "我今天感觉有点疲惫，但还是完成了任务。"
}


⸻

📈 示例返回

{
"success": true,
"message": "记录成功，系统已分析情绪并生成建议",
"data": {
"sentiment": "positive",
"score": 0.67,
"suggestion": "继续保持积极的态度，注意适当休息。"
}
}


⸻

🎯 项目目标与意义

本项目可作为申研项目成果展示，具备完整的后端系统设计能力、AI 接口集成能力、数据处理与逻辑编写能力，适合方向包括：
•	情绪识别 / 心理健康监测
•	教育数据挖掘
•	人机交互 / 智能推荐系统

⸻

## 开发环境

- JDK: 1.8（Amazon Corretto）
- Spring Boot: 2.7
- MySQL: 9.3.0
- MyBatis: 2.2
- Apifox（API 测试工具）
- IDE: IntelliJ IDEA

