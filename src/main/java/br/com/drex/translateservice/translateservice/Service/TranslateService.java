package br.com.drex.translateservice.translateservice.Service;

import br.com.drex.translateservice.translateservice.Model.TranslateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TranslateService {

    private static final String URL_API_GOOGLE = "https://translation.googleapis.com/language/translate/v2?key=";
    private static String API_KEY = "AIzaSyCVFqrK0z8mx7luZtvdnDL3gLf9ffRlfuE";

    public static String translateText(TranslateRequest translateRequest) throws Exception {
        log.info("Trying to translate text received: " + translateRequest.getTextToTranslate());

        String url = URL_API_GOOGLE + API_KEY;

        Map<Object, Object> data = new HashMap<>();
        data.put("q", translateRequest.getTextToTranslate());
        data.put("source", translateRequest.getSourceLanguage());
        data.put("target", translateRequest.getTargetLanguage());
        data.put("format", "text");

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapToJson(data)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            String translatedAdvice = extractTranslatedText(responseBody);
            log.info("Advice translated: " + translatedAdvice);
            return translatedAdvice;
        } else {
            log.error("Error on translate text.");
            throw new RuntimeException("Error on translate text. Status code: " + response.statusCode());
        }
    }

    private static String mapToJson(Map<Object, Object> map) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":\"");
            json.append(entry.getValue()).append("\",");
        }
        json.setCharAt(json.length() - 1, '}');
        return json.toString();
    }

    private static String extractTranslatedText(String responseBody) {
        String translatedText = responseBody.split("\"")[7];
        return translatedText;
    }
}
