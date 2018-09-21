package com.rentalbike.challenge.strategies;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rentalbike.challenge.constant.Constant;

@Component
public class StrategyFactory {
 
   private Map<String, DiscountStrategy> strategies = new HashMap<String, DiscountStrategy>();
 
   public StrategyFactory() {
       initStrategies();
   }
 
   public DiscountStrategy getStrategy(String strategy) {
       if (strategy == null || !strategies.containsKey(strategy)) {
           throw new IllegalArgumentException("Invalid " + strategy);
       }
       return strategies.get(strategy);
   }
 
   private void initStrategies() {
       strategies.put(Constant.DISCOUNT_FAMILY_TYPE, new DiscountFamilyStrategy());
   }
   
}

