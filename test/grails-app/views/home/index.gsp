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
	                				                
			                   'url' : '${request.contextPath + '/home/jsontree'}',
			                    data : function(data){
				                    return data  
				                }
	                	}     
	              },
	              "plugins" : [ "themes", "json_data" ]
	        });
         });
        
</script>
    </head>
    <body>
        <div id="treeViewDiv">
        </div>
    </body>
</html>