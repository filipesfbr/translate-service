package br.com.drex.translateservice.translateservice.Model;

import br.com.drex.translateservice.translateservice.Model.model_payload.DataResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslateResponse {
    private DataResponse data;
}
