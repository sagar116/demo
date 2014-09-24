package com.testapp
import com.google.gson.Gson
import com.google.gson.JsonArray;

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class HomeController {

    def index = {
	       render(view :"index")	
	}
	
	
	def jsontree = {
		
		Tree mainTree = new Tree()
		mainTree.id = "root"
		mainTree.data = "Google"
		
		List<Tree> childList = new ArrayList<Tree>()
		List subChildList = new ArrayList();
		
		Tree tree1 = new Tree()
		tree1.id = "Finance"
		tree1.data = "Finance"
		subChildList = new ArrayList()
		subChildList.add(createTree("Accounting", ["John","Jack"]))
		subChildList.add(createTree("Billing", ["Abc", "Bbc"]))
		tree1.children = subChildList
		childList.add(tree1)
		
		Tree tree2 = new Tree()
		tree2.data = "HR"
		tree2.id = "idHR"
		subChildList = new ArrayList()
		subChildList.add(createTree("Training", ["Adam","Gilchrist"]))
		subChildList.add(createTree("Recruitment", ["Gale", "Steve"]))
		tree2.children = subChildList
		childList.add(tree2)
		
		Tree tree3 = new Tree()
		tree3.id = "R&D"
		tree3.data = "R&D"
		subChildList = new ArrayList()
		subChildList.add(createTree("Scientific", ["Kalam","Amar"]))
		subChildList.add(createTree("Industrial", ["Anil","Mittal"]))
		tree3.children = subChildList
		childList.add(tree3)
		
		Tree tree4 = new Tree()
		tree4.data = "Testing"
		tree4.id = "testingID"
		subChildList = new ArrayList()
		subChildList.add(createTree("Manual", ["TestM1","TestM2"]))
		subChildList.add(createTree("Automation", ["TestA1","TestA2"]))
		tree4.children = subChildList
		childList.add(tree4)
		
		mainTree.children = childList
		def jsonTree = mainTree as JSON
		
		jsonTree = addChildNode(jsonTree, "R&D", "Technology")
		
		render jsonTree
	}
	
	private Tree createTree(String node, List list){
		def tree = new Tree()
		def children = new ArrayList()
		tree.id = node
		tree.data = node
		tree.children = []
		list.each { childNode ->
			def tree2 = new Tree()
			tree2.id = childNode
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
		subNode.id = "subNode"
		subNode.data = nodeName
		subNode.children = []
		
		if(jsonTreeObj.id == nodeidToAdd){
			jsonTreeObj.children.add(subNode)
		}else {
			getChildren(jsonTreeObj, nodeidToAdd, subNode)
		}
		
		return jsonTreeObj 
	}
	
	private JSONObject getChildren(JSONObject tree, String nodeidToAdd, Tree subNode){
		if(tree.children != null && tree.children != JSONObject.NULL){
			tree.children.each { it ->
				if(it.id == nodeidToAdd){
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
