package grails.plugins.brvalidation

class Person {

	String cpf
	String cnpj
	String cpfcnpj
    String cep
    String cns
    String tituloEleitor
	
    static constraints = {
		cpf ( nullable: true, cpf: true)
		cnpj ( nullable: true, cnpj: true)
		cpfcnpj (nullable: true, cpfcnpj: true)
        cep (nullable: true, cep: true)
        cns (nullable: true, cns: true)
        tituloEleitor(nullable: true, tituloEleitor: true)
    }
}
