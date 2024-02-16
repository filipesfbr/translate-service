package br.com.drex.translateservice.translateservice.Model;

public class TranslateRequest {
    private String textToTranslate;
    private String sourceLanguage;
    private String targetLanguage;

    public TranslateRequest(String textToTranslate, String sourceLanguage, String targetLanguage) {
        this.textToTranslate = textToTranslate;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }
    public String getSourceLanguage() {
        return sourceLanguage;
    }
    public String getTargetLanguage() {
        return targetLanguage;
    }

}
