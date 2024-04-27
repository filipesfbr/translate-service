package br.com.drex.translateservice.translateservice.Service;

import br.com.drex.translateservice.translateservice.Model.TranslateRequest;
import br.com.drex.translateservice.translateservice.Model.TranslateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

        ObjectMapper mapper = new ObjectMapper();

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

        if (response.statusCode() == HttpStatus.OK.value()) {
            TranslateResponse translateResponse = mapper.readValue(response.body(), TranslateResponse.class);
            String translatedText = translateResponse.getData().getTranslations().get(0).getTranslatedText();
            log.info("Text translated: " + translatedText);
            return translatedText;
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

}
