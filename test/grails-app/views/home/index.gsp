<html>
    <head>
        <title>Use jsTree</title>
       <g:javascript library="jquery" plugin="jquery"/>
<jsTree:resources />
        <script type="text/javascript">    
         $(document).ready(function(){
	        $('#treeViewDiv').jstree({
	                'json_data' : {
		                //url commented since it's fnot etching data in right json format
	                   //'url' : '${request.contextPath + '/home/list'}',
	                    'data' : [
	                              {
	                                  "data" : "Google",
	                                  "children" :[
	                                               {"data": "Finance",
	                                                 "children" : [
	                                                                 {
	                                                                     "data":"Accounting",
	                                                                     "children":[
	                                                                                  {"data":"Person1"},
	                                                                                  {"data" :"Person2"}
	                                                                                 ]
	                                                                 },
	                                                                 {
	                                                                     "data":"Billing",
	                                                                     "children":[
	                                                                                  {"data":"Person1"},
	                                                                                  {"data" :"Person2"}
	                                                                                 ]
	                                                                 }
	                                                               ]  
	                                               },
	                                               {"data": "HR",
	                                                   "children" : [
	                                                                   {
	                                                                       "data":"Traning",
	                                                                       "children":[
	                                                                                    {"data":"Person1"},
	                                                                                    {"data" :"Person2"}
	                                                                                   ]
	                                                                   },
	                                                                   {
	                                                                       "data":"Recruitment",
	                                                                       "children":[
	                                                                                    {"data":"Person1"},
	                                                                                    {"data" :"Person2"}
	                                                                                   ]
	                                                                   }
	                                                                 ]  
	                                                 },
	                                                 {"data": "R&D",
	                                                     "children" : [
	                                                                     {
	                                                                         "data":"Scientific",
	                                                                         "children":[
	                                                                                      {"data":"Person1"},
	                                                                                      {"data" :"Person2"}
	                                                                                     ]
	                                                                     },
	                                                                     {
	                                                                         "data":"Industrial",
	                                                                         "children":[
	                                                                                      {"data":"Person1"},
	                                                                                      {"data" :"Person2"}
	                                                                                     ]
	                                                                     }
	                                                                   ]  
	                                                   },
	                                                   {"data": "Testing",
	                                                       "children" : [
	                                                                       {
	                                                                           "data":"Manual",
	                                                                           "children":[
	                                                                                        {"data":"Person1"},
	                                                                                        {"data" :"Person2"}
	                                                                                       ]
	                                                                       },
	                                                                       {
	                                                                           "data":"Automation",
	                                                                           "children":[
	                                                                                        {"data":"Person1"},
	                                                                                        {"data" :"Person2"}
	                                                                                       ]
	                                                                       }
	                                                                     ]  
	                                                     }
	                                              ],
	                                  "state" : "open"
	                              }
	                          ]
	              },
	              "plugins" : [ "themes", "json_data", "ui" ]
	        });
         });
        
</script>
    </head>
    <body>
        <div id="treeViewDiv">
        </div>
    </body>
</html>