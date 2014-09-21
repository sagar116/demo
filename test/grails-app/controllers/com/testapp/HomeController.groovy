package com.testapp
import grails.converters.JSON

class HomeController {

    def index = {
	       render(view :"index")	
	}
	
	
	def jsontree = {
		
		def map = new HashMap();
		map.put("data","Google")
		
		List<Tree> childList = new ArrayList<Tree>()
		List subChildList = new ArrayList();
		
		Tree tree1 = new Tree()
		tree1.data = "Finance"
		subChildList = new ArrayList()
		subChildList.add(createTree("Accounting", ["John","Jack"]))
		subChildList.add(createTree("Billing", ["Abc", "Bbc"]))
		tree1.children = subChildList
		childList.add(tree1)
		
		Tree tree2 = new Tree()
		tree2.data = "HR"
		subChildList = new ArrayList()
		subChildList.add(createTree("Training", ["Adam","Gilchrist"]))
		subChildList.add(createTree("Recruitment", ["Gale", "Steve"]))
		tree2.children = subChildList
		childList.add(tree2)
		
		Tree tree3 = new Tree()
		tree3.data = "R&D"
		subChildList = new ArrayList()
		subChildList.add(createTree("Scientific", ["Kalam","Amar"]))
		subChildList.add(createTree("Industrial", ["Anil","Mittal"]))
		tree3.children = subChildList
		childList.add(tree3)
		
		Tree tree4 = new Tree()
		tree4.data = "Testing"
		subChildList = new ArrayList()
		subChildList.add(createTree("Manual", ["TestM1","TestM2"]))
		subChildList.add(createTree("Automation", ["TestA1","TestA2"]))
		tree4.children = subChildList
		childList.add(tree4)
		
		map.put("children", childList);

		render map as JSON
	}
	
	private Tree createTree(String node, List list){
		def tree = new Tree()
		tree.data = node
		tree.children = list
		return tree
	}
	
}
