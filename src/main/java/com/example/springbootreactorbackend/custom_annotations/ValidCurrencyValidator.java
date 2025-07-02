package com.example.springbootreactorbackend.custom_annotations;

import com.example.springbootreactorbackend.modules.payment.CurrencyEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCurrencyValidator implements ConstraintValidator<ValidCurrency, Object> {  // More flexible
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true; // Combine with @NotNull

        if (value instanceof CurrencyEnum) {
            return true;  // Always valid if already enum type
        }

        if (value instanceof String) {
            String strValue = ((String) value).toUpperCase();
            for (CurrencyEnum currency : CurrencyEnum.values()) {
                if (currency.name().equals(strValue)) {
                    return true;
                }
            }
        }

        return false;
    }
}
