package com.projects.BnBit.strategy;

import com.projects.BnBit.entity.Inventory;

import java.math.BigDecimal;
public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
