package com.cmb.beprepared.dto.request;

import com.cmb.beprepared.model.enums.Severity;
import lombok.Data;

@Data
public class AlertRequestDto {

    private String title;
    private String message;
    private Severity severity;
    private Long provinceId;
    private Long cityId;

}
