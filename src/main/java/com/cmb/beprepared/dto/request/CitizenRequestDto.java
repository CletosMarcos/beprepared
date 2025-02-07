package com.cmb.beprepared.dto.request;

import lombok.Data;

@Data
public class CitizenRequestDto {

    private String phone;
    private String deviceId;
    private Long cityId;
}
