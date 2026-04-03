package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * AI 求职问答接口（OpenAI 兼容协议）
 */
@RestController
@RequestMapping("/ai/career")
public class AiCareerController {

    @Value("${ai.assistant.enabled:false}")
    private boolean enabled;

    @Value("${ai.assistant.api-url:https://api.openai.com/v1/chat/completions}")
    private String apiUrl;

    @Value("${ai.assistant.api-key:}")
    private String apiKey;

    @Value("${ai.assistant.auth-header:Authorization}")
    private String authHeader;

    @Value("${ai.assistant.auth-scheme:Bearer}")
    private String authScheme;

    @Value("${ai.assistant.model:gpt-4o-mini}")
    private String model;

    @Value("${ai.assistant.timeout-ms:30000}")
    private int timeoutMs;

    @Value("${ai.assistant.system-prompt:你是一名校园招聘求职助手，请结合求职场景提供准确、可执行、结构化的建议。}")
    private String systemPrompt;

    @IgnoreAuth
    @RequestMapping(value = "/ask", method = RequestMethod.POST)
    public R ask(@RequestBody Map<String, Object> payload) {
        Object questionObj = payload == null ? null : payload.get("question");
        String question = questionObj == null ? null : questionObj.toString();
        if (StringUtils.isBlank(question)) {
            return R.error(400, "问题不能为空");
        }
        if (!enabled) {
            return R.error(400, "AI求职问答未启用，请配置 AI_ASSISTANT_ENABLED=true");
        }
        if (StringUtils.isBlank(apiUrl)) {
            return R.error(500, "AI_ASSISTANT_API_URL 未配置");
        }

        try {
            String answer = callOpenAiCompatibleApi(question);
            return R.ok().put("answer", answer).put("model", model);
        } catch (Exception e) {
            return R.error(500, "AI接口调用失败：" + e.getMessage());
        }
    }

    private String callOpenAiCompatibleApi(String question) throws IOException {
        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(timeoutMs);
            connection.setReadTimeout(timeoutMs);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            if (StringUtils.isNotBlank(apiKey)) {
                connection.setRequestProperty(authHeader, buildAuthValue());
            }

            JSONObject body = new JSONObject();
            body.put("model", model);
            body.put("temperature", 0.3);

            JSONArray messages = new JSONArray();
            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", question);
            messages.add(userMessage);
            body.put("messages", messages);

            byte[] requestBody = body.toJSONString().getBytes(StandardCharsets.UTF_8);
            outputStream = connection.getOutputStream();
            outputStream.write(requestBody);
            outputStream.flush();

            int status = connection.getResponseCode();
            inputStream = status >= 200 && status < 300 ? connection.getInputStream() : connection.getErrorStream();
            String responseBody = readAll(inputStream);
            if (status < 200 || status >= 300) {
                String error = parseErrorMessage(responseBody);
                throw new IOException("HTTP " + status + " - " + error);
            }

            String answer = parseAnswer(responseBody);
            if (StringUtils.isBlank(answer)) {
                return "暂未获得有效回复，请稍后重试。";
            }
            return answer;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String readAll(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    private String parseAnswer(String responseBody) {
        JSONObject root = JSONObject.parseObject(responseBody);
        if (root == null) {
            return "";
        }
        JSONArray choices = root.getJSONArray("choices");
        if (choices == null || choices.isEmpty()) {
            return "";
        }
        JSONObject first = choices.getJSONObject(0);
        if (first == null) {
            return "";
        }
        JSONObject message = first.getJSONObject("message");
        if (message == null) {
            return "";
        }
        return StringUtils.trimToEmpty(message.getString("content"));
    }

    private String parseErrorMessage(String responseBody) {
        if (StringUtils.isBlank(responseBody)) {
            return "empty response";
        }
        try {
            JSONObject root = JSONObject.parseObject(responseBody);
            if (root == null) {
                return responseBody;
            }
            Object errorObj = root.get("error");
            if (errorObj instanceof JSONObject) {
                String message = ((JSONObject) errorObj).getString("message");
                if (StringUtils.isNotBlank(message)) {
                    return message;
                }
            }
            String message = root.getString("message");
            if (StringUtils.isNotBlank(message)) {
                return message;
            }
        } catch (Exception ignored) {
            // ignore parse error and return raw response below
        }
        return responseBody;
    }

    private String buildAuthValue() {
        if (StringUtils.isBlank(authScheme) || "NONE".equalsIgnoreCase(authScheme)) {
            return apiKey;
        }
        return authScheme.trim() + " " + apiKey;
    }
}
