package com.cturbo.ecom.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessDetails {
    private String businessName;
    private String businessEmail;
    private String businessMobile;
    private String businessAddress;
    private String logo;
    private String banner;
}
