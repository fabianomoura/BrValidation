package grails.plugins.brvalidation

import grails.plugins.brvalidation.constraint.CnsConstraint
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.validation.ConstrainedProperty
import org.junit.BeforeClass

/**
 * Created by fabianomoura on 15/11/14.
 * @author Fabiano Moura (fabiano@fabianomoura.com.br)
 */
@TestFor(Person)
class CnsConstraintTest extends GroovyTestCase {
    @BeforeClass
    static void initConstraint() {
        ConstrainedProperty.registerNewConstraint(CnsConstraint.NATIONAL_HEALTH_CARD, CnsConstraint)
    }

    void testCnsBoth() {
        config.grails.plugins.brValidation.validation.type = "both"

        def person = new Person(cns: "233763263470003")
        assert person.validate()

        person.cns = "271384903090018"
        assert person.validate()

        person.cns = "171618598620009"
        assert person.validate()

        person.cns = "258682484520009"
        assert person.validate()

        person.cns = "1202198440050001"
        assert person.validate()

        person.cns = "285822668740007"
        assert person.validate()

        person.cns = "232692712030007"
        assert person.validate()

        person.cns = "168788957630006"
        assert person.validate()

        person.cns = "295443187950006"
        assert person.validate()

        person.cns = "271384903090099"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "17161859862000"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "2586824845200"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "12021984400500011"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "2858226687400072"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "2326927120300012"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "168788957630012"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "295443187950"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "84.107.697/0001-95"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "197870700880005"
        assert person.validate()

        person.cns = "00779964969"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code

        person.cns = "007.799.649-61"
        assert !person.validate()
        assert "not.cns" == person.errors["cns"].code
    }
}
