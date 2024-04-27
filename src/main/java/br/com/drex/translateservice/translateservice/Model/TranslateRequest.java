package br.com.drex.translateservice.translateservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TranslateRequest {
    private String textToTranslate;
    private String sourceLanguage;
    private String targetLanguage;
}
