package com.cmb.beprepared.dto.response;

import com.cmb.beprepared.model.enums.Severity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponseDto {

    private Long id;
    private String title;
    private String message;
    private Severity severity;
    private String province;
    private String city;
    private boolean isActive;
}
