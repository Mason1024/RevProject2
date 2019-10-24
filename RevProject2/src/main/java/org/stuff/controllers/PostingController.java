package org.stuff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.stuff.services.PostingService;

@Component
@RestController
public class PostingController {
	// /getPostById/{ID}
	
	@Autowired
	PostingService ps;
	
}
