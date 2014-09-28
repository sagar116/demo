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
		println jsonTree
		
		render jsonTree
	}
	
	def createNode = {
		def nodeidToAdd = params.nodeID
		def nodeName = params.nodeName
		
		Tree newNode = new Tree()
		def attr = new Attribute()
		attr.id = nodeidToAdd +"_"+ nodeName
		newNode.attr = attr
		newNode.data = nodeName
		newNode.children = []
		def newNodeJson = newNode as JSON
		
		jsonTree = addChildNode(jsonTree, nodeidToAdd, newNodeJson)
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
		attr.id = "root_fin"
		tree1.attr = attr
		tree1.data = "Finance"
		subChildList = new ArrayList()
		subChildList.add(createTree("root_fin_acc","Accounting", ["John","Jack"]))
		subChildList.add(createTree("root_fin_bill","Billing", ["Abc", "Bbc"]))
		tree1.children = subChildList
		childList.add(tree1)
		
		Tree tree2 = new Tree()
		attr = new Attribute()
		attr.id = "root_hr"
		tree2.data = "HR"
		tree2.attr = attr
		subChildList = new ArrayList()
		subChildList.add(createTree("root_hr_training","Training", ["Adam","Gilchrist"]))
		subChildList.add(createTree("root_hr_rec","Recruitment", ["Gale", "Steve"]))
		tree2.children = subChildList
		childList.add(tree2)
		
		Tree tree3 = new Tree()
		attr = new Attribute()
		attr.id = "root_rd"
		tree3.attr = attr
		tree3.data = "R&D"
		subChildList = new ArrayList()
		subChildList.add(createTree("root_rd_scientific","Scientific", ["Kalam","Amar"]))
		subChildList.add(createTree("root_rd_industrial","Industrial", ["Anil","Mittal"]))
		tree3.children = subChildList
		childList.add(tree3)
		
		Tree tree4 = new Tree()
		attr = new Attribute()
		attr.id = "root_testing"
		tree4.attr = attr
		tree4.data = "Testing"
		subChildList = new ArrayList()
		subChildList.add(createTree("root_testing_manual","Manual", ["TestM1","TestM2"]))
		subChildList.add(createTree("root_testing_auto","Automation", ["TestA1","TestA2"]))
		tree4.children = subChildList
		childList.add(tree4)
		
		mainTree.children = childList
		
		return mainTree
	}
	
	private Tree createTree(String parentID, String node, List list){
		def tree = new Tree()
		def attr = new Attribute()
		def children = new ArrayList()
		attr.id = parentID
		tree.attr = attr
		tree.data = node
		tree.children = []
		list.each { childNode ->
			def tree2 = new Tree()
			attr = new Attribute()
			attr.id = parentID +"_"+ childNode
			tree2.attr = attr
			tree2.data = childNode
			tree2.children = []
			children.add(tree2)
		}
		tree.children = children
		return tree
	}
	
	private JSON addChildNode(def jsonTree, String nodeidToAdd, def newNodeJson){
		def jsonTreeObj = JSON.parse(jsonTree.toString())
		def newNode = JSON.parse(newNodeJson.toString())
		
		if(jsonTreeObj.attr.id == nodeidToAdd){
			jsonTreeObj.children.add(newNode)
		}else {
			getChildren(jsonTreeObj, nodeidToAdd, newNode)
		}
		
		return jsonTreeObj 
	}
	
	private JSONObject getChildren(JSONObject tree, String nodeidToAdd, JSONObject newNode){
		if(tree.children != null && tree.children != JSONObject.NULL){
			tree.children.each { it ->
				if(nodeidToAdd.contains(it.attr.id)){
					if(nodeidToAdd.equals(it.attr.id)){
					  it.children.add(newNode)
					}else{
					  getChildren(it, nodeidToAdd, newNode)
					}
				}
			}
		}
		return tree
	}
}
