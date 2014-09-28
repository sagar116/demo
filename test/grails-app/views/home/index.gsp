<html>
    <head>
        <title>Use jsTree</title>
       <g:javascript library="jquery" plugin="jquery"/>
<jsTree:resources />
        <script type="text/javascript">    
         $(document).ready(function(){
	        $('#treeViewDiv').jstree({
	                'json_data' : {
	                	"ajax" : {
	                				                
			                   'url' : '${request.contextPath + '/home/treeview'}',
			                    'success': function(data){
                                    return data
			                     }
	                	}
	                 },
	              "plugins" : [ "themes", "json_data", "ui", "crrm", "contextmenu"]
		              
	        }).bind("create.jstree", function(e, data) {
	            // data.rslt is the new node
	            if (data.rslt.parent == -1) {
	                alert("Can not create new root directory");
	                // Rollback/delete the newly created node
	                $.jstree.rollback(data.rlbk);
	                return;
	            }
	            
	            var  data = "nodeID="+data.rslt.parent[0].id+"&nodeName="+data.rslt.name; 
	            alert(data)
	            $.ajax({
		            url : "${request.contextPath + '/home/createNode'}",
		            type: "POST",
		            data : data,
		            success: function(data)
		            {
		                alert("new node created")
		            },
		            error: function (jqXHR, textStatus, errorThrown)
		            {
		            	$.jstree.rollback(data.rlbk);
		            }
		        }); 
	            
            });

         });

</script>
    </head>
    <body>
        <div id="treeViewDiv">
        </div>
    </body>
</html>