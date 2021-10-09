package com.bitso.challenge.model.ram;

import com.bitso.challenge.entity.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderRam extends Order {
    private long userId;
}
