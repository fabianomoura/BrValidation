package grails.plugins.brvalidation.constraint

import org.codehaus.groovy.grails.validation.AbstractConstraint
import org.springframework.validation.Errors

/**
 * Created by fabianomoura on 15/11/14.
 * @author Fabiano Moura (fabiano@fabianomoura.com.br)
 * Basead on https://github.com/insula/opes
 */
class CnsConstraint extends AbstractConstraint{

    private static final String DEFAULT_NOT_NATIONAL_HEALTH_CARD_MESSAGE_CODE = "default.not.cns.message"
    public static final String NATIONAL_HEALTH_CARD = "cns"

    private boolean cns;

    @Override
    boolean supports(Class type) {
        return type != null && String.isAssignableFrom(type)
    }

    void setParameter(Object constraintParameter) {
        if(!(constraintParameter instanceof Boolean)) {
            throw new IllegalArgumentException("Parameter for constraint ["
                    +NATIONAL_HEALTH_CARD+"] of property ["
                    +constraintPropertyName+"] of class ["
                    +constraintOwningClass+"] must be a boolean value")
        }

        cns = (Boolean)constraintParameter
        super.setParameter(constraintParameter)
    }

    @Override
    String getName() {
        return NATIONAL_HEALTH_CARD
    }

    protected void processValidate(Object target, Object propertyValue, Errors errors) {
        if (! isvalid((propertyValue as String))) {
            def args = (Object[]) [constraintPropertyName, constraintOwningClass, propertyValue]
            super.rejectValue(target, errors, DEFAULT_NOT_NATIONAL_HEALTH_CARD_MESSAGE_CODE,
                    "not." + NATIONAL_HEALTH_CARD, args)
        }
    }

    static boolean isValid(String s) {
        if (s.matches("[1-2]\\d{10}00[0-1]\\d") || s.matches("[7-9]\\d{14}")) {
            return somaPonderada(s) % 11 == 0;
        }
        return false;
    }

    private static int somaPonderada(String s) {
        checkArgument(s.length() <= 15);
        char[] cs = s.toCharArray();
        int soma = 0;
        for (int i = 0; i < cs.length; i++) {
            soma += Character.digit(cs[i], 10) * (15 - i);
        }
        return soma;
    }
}
