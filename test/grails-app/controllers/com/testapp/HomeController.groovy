package com.testapp
import grails.converters.JSON

class HomeController {

    def index = {
	       render(view :"index")	
	}
	
	def list = {
		
		List<Person> persons1= new ArrayList<Person>();
		List<Group> groups = new ArrayList<Group>();
		Organization organization = new Organization();
		def group = new Group();
		def person = new Person();
		
		organization.name = "Google"
		
		group.name = "Finance"
		person.name = "Sagar"
		persons1.add(person)
		person = new Person();
		person.name = "ABC"
		persons1.add(person)
		group.persons = persons1
		groups.add(group)
		
		List<Person> persons2= new ArrayList<Person>();
		group = new Group();
		group.name = "HR"
	    person =  new Person();
		person.name = "John"
		persons2.add(person)
		person = new Person();
		person.name = "Jack"
		persons2.add(person)
		group.persons = persons2
		groups.add(group)
		
		organization.groups = groups
		
		
		render organization as JSON
	}
	
}
