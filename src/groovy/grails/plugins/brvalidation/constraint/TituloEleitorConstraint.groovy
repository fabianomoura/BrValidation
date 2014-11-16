package grails.plugins.brvalidation.constraint

import org.codehaus.groovy.grails.validation.AbstractConstraint
import org.springframework.validation.Errors

/**
 * Created by fabianomoura on 15/11/14.
 * @author Fabiano Moura (fabiano@fabianomoura.com.br)
 *
 * Based on https://github.com/insula/opes
 */
class TituloEleitorConstraint extends AbstractConstraint{

    private static final String DEFAULT_NOT_TITLE_OF_VOTER_MESSAGE_CODE = "default.not.voter.message"
    public static final String TITLE_OF_VOTER = "Título de Eleitor"

    private boolean cns;

    @Override
    boolean supports(Class type) {
        return type != null && String.isAssignableFrom(type)
    }

    void setParameter(Object constraintParameter) {
        if(!(constraintParameter instanceof Boolean)) {
            throw new IllegalArgumentException("Parameter for constraint ["
                    +TITLE_OF_VOTER+"] of property ["
                    +constraintPropertyName+"] of class ["
                    +constraintOwningClass+"] must be a boolean value")
        }

        cns = (Boolean)constraintParameter
        super.setParameter(constraintParameter)
    }

    @Override
    String getName() {
        return TITLE_OF_VOTER
    }

    protected void processValidate(Object target, Object propertyValue, Errors errors) {
        if (! isvalid((propertyValue as String))) {
            def args = (Object[]) [constraintPropertyName, constraintOwningClass, propertyValue]
            super.rejectValue(target, errors, DEFAULT_NOT_TITLE_OF_VOTER_MESSAGE_CODE,
                    "not." + TITLE_OF_VOTER, args)
        }
    }

    static boolean isValid(String digits) {
        String padded = String.format("%013d", Long.parseLong(digits));
        return primeiraValidacao(padded) && segundaValidacao(padded);
    }

    static boolean segundaValidacao(String padded) {
        if (segundoDigito(padded) == 0) {
            return (segundaSoma(padded) + segundoDigito(padded)) % 11 < 2;
        }
        else {
            return (segundaSoma(padded) + segundoDigito(padded)) % 11 == 0;
        }
    }

    static boolean primeiraValidacao(String padded) {
        if (primeiroDigito(padded) == 0) {
            return (primeiraSoma(padded) + primeiroDigito(padded)) % 11 < 2;
        }
        else {
            return (primeiraSoma(padded) + primeiroDigito(padded)) % 11 == 0;
        }
    }

    static int segundaSoma(String padded) {
        return somaPonderada(padded.substring(9, 12));
    }

    static int segundoDigito(String padded) {
        return Integer.parseInt(padded.substring(12, 13));
    }

    static int primeiraSoma(String padded) {
        return somaPonderada(padded.substring(0, 9));
    }

    static int primeiroDigito(String padded) {
        return Integer.parseInt(padded.substring(11, 12));
    }

    static int somaPonderada(String s) {
        char[] cs = s.toCharArray();
        int soma = 0;
        for (int i = 0; i < cs.length; i++) {
            soma += Character.digit(cs[cs.length - i - 1], 10) * (i % 8 + 2);
        }
        return soma;
    }
}
