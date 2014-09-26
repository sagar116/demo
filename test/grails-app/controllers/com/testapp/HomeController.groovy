package com.testapp

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class HomeController {

	def jsonTree
    def index = {
	       render(view :"index")	
	}
	
	
	def treeview = {
		
		def mainTree = getTreeData()
		jsonTree = mainTree as JSON
		
		render jsonTree
	}
	
	def createNode = {
		def nodeidToAdd = params.nodeID
		def nodeName = params.nodeName
		
		jsonTree = addChildNode(jsonTree, nodeidToAdd, nodeName)
		println "new json:"+ jsonTree
		
		render jsonTree
	}
	
	private Tree getTreeData() {
		Tree mainTree = new Tree()
		def attr = new Attribute()
		attr.id = "root"
		mainTree.attr = attr
		mainTree.data = "Google"
		
		List<Tree> childList = new ArrayList<Tree>()
		List subChildList = new ArrayList();
		
		Tree tree1 = new Tree()
		attr = new Attribute()
		attr.id = "Finance"
		tree1.attr = attr
		tree1.data = "Finance"
		subChildList = new ArrayList()
		subChildList.add(createTree("Accounting", ["John","Jack"]))
		subChildList.add(createTree("Billing", ["Abc", "Bbc"]))
		tree1.children = subChildList
		childList.add(tree1)
		
		Tree tree2 = new Tree()
		attr = new Attribute()
		attr.id = "idHR"
		tree2.data = "HR"
		tree2.attr = attr
		subChildList = new ArrayList()
		subChildList.add(createTree("Training", ["Adam","Gilchrist"]))
		subChildList.add(createTree("Recruitment", ["Gale", "Steve"]))
		tree2.children = subChildList
		childList.add(tree2)
		
		Tree tree3 = new Tree()
		attr = new Attribute()
		attr.id = "R&D"
		tree3.attr = attr
		tree3.data = "R&D"
		subChildList = new ArrayList()
		subChildList.add(createTree("Scientific", ["Kalam","Amar"]))
		subChildList.add(createTree("Industrial", ["Anil","Mittal"]))
		tree3.children = subChildList
		childList.add(tree3)
		
		Tree tree4 = new Tree()
		attr = new Attribute()
		attr.id = "testingID"
		tree4.attr = attr
		tree4.data = "Testing"
		subChildList = new ArrayList()
		subChildList.add(createTree("Manual", ["TestM1","TestM2"]))
		subChildList.add(createTree("Automation", ["TestA1","TestA2"]))
		tree4.children = subChildList
		childList.add(tree4)
		
		mainTree.children = childList
		
		return mainTree
	}
	
	private Tree createTree(String node, List list){
		def tree = new Tree()
		def attr = new Attribute()
		def children = new ArrayList()
		attr.id = node
		tree.attr = attr
		tree.data = node
		tree.children = []
		list.each { childNode ->
			def tree2 = new Tree()
			attr = new Attribute()
			attr.id = childNode
			tree2.attr = attr
			tree2.data = childNode
			tree2.children = []
			children.add(tree2)
		}
		tree.children = children
		return tree
	}
	
	private JSON addChildNode(def jsonTree, String nodeidToAdd, String nodeName){
		def jsonTreeObj = JSON.parse(jsonTree.toString())
		Tree subNode = new Tree()
		def attr = new Attribute()
		attr.id = nodeName
		subNode.attr = attr
		subNode.data = nodeName
		subNode.children = []
		
		if(jsonTreeObj.attr.id == nodeidToAdd){
			jsonTreeObj.children.add(subNode)
		}else {
			getChildren(jsonTreeObj, nodeidToAdd, subNode)
		}
		
		return jsonTreeObj 
	}
	
	private JSONObject getChildren(JSONObject tree, String nodeidToAdd, Tree subNode){
		if(tree.children != null && tree.children != JSONObject.NULL){
			tree.children.each { it ->
				if(it.attr.id == nodeidToAdd){
					it.children.add(subNode)
					return;
				}else {
				   getChildren(it, nodeidToAdd, subNode)
				}
			}
		}
		return tree
	}
}
