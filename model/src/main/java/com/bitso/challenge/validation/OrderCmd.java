package com.bitso.challenge.validation;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderCmd {
    //todo javax validation
     long userId;
     String status;
     Date created;
     String major;
     String minor;
     BigDecimal amount;
     BigDecimal price;
     boolean buy;
}
