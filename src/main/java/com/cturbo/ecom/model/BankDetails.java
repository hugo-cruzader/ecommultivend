package com.cturbo.ecom.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDetails {
    private String accountNumber;
    private String accountHolderName;
    //private String bankName;
    private String ifscCode;
}
