class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

         //"/"(view:"home/org3")
		
		"/"(controller:'home', action:'index')
		
        "500"(view:'/error')
	}
}
