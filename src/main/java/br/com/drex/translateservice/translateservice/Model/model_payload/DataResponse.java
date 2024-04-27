package br.com.drex.translateservice.translateservice.Model.model_payload;

import lombok.Getter;

import java.util.List;

@Getter
public class DataResponse {
    private List<TranslationsResponse> translations;
}
