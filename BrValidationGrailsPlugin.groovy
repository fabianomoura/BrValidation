import grails.plugins.brvalidation.constraint.CepConstraint
import grails.plugins.brvalidation.constraint.CnpjConstraint
import grails.plugins.brvalidation.constraint.CpfCnpjConstraint
import grails.plugins.brvalidation.constraint.CpfConstraint
import grails.plugins.brvalidation.constraint.CnsConstraint
import grails.plugins.brvalidation.constraint.TituloEleitorConstraint
import org.codehaus.groovy.grails.validation.ConstrainedProperty

/*
 * Copyright (c) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * BrValidation Plugin. Implements gorm validation to same documents
 * (CPF and CNPJ) and codes formats (CEP) used in Brazil
 *
 * @author Oscar Konno Sampaio (oscarks@gmail.com)
 */
class BrValidationGrailsPlugin {

    def version = "0.6"
    def grailsVersion = "1.1.1 > *"
    def dependsOn = [:]
	   
    def pluginExcludes = [
		'grails-app/domain/**'
	]

    def title = "Br Validation Plugin" // Headline display name of the plugin
    def author = "Oscar Konno Sampaio"
    def authorEmail = "oscarks@gmail.com"
    def description = '''Implements gorm validation to same documents 
(CPF and CNPJ), codes formats (CEP) used in Brazil, CNS (Cartão Nacional de Saúde - National Health Card) used in Brazil and Title of Voter (Título de Eleitor) used in Brazil
'''
    def documentation = "http://grails.org/plugin/br-validation"

    def license = "APACHE"
	
	def issueManagement = [system: "github", url: "https://github.com/oscarks/BrValidation/issues"]
	def scm = [ url: "https://github.com/oscarks/BrValidation" ]
    def developers = [ 
		[ name: "Oscar Konno", email: "oscarks@gmail.com" ],
		[ name: "Leandro G. Gehlen", email: "leandrogehlen@gmail.com" ],
		[ name: "Fabiano Moura", email: "fabiano@fabianomoura.com.br"]
	]
    
    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
		ConstrainedProperty.registerNewConstraint(CpfCnpjConstraint.CPF_CNPJ_CONSTRAINT, CpfCnpjConstraint.class)
		ConstrainedProperty.registerNewConstraint(CnpjConstraint.CNPJ_CONSTRAINT,CnpjConstraint.class)
		ConstrainedProperty.registerNewConstraint(CpfConstraint.CPF_CONSTRAINT,CpfConstraint.class)
		ConstrainedProperty.registerNewConstraint(CepConstraint.POSTAL_CODE_CONSTRAINT,CepConstraint.class)
        ConstrainedProperty.registerNewConstraint(CnsConstraint.NATIONAL_HEALTH_CARD,CnsConstraint.class)
        ConstrainedProperty.registerNewConstraint(TituloEleitorConstraint.TITLE_OF_VOTER,TituloEleitorConstraint.class)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)		
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }
}
