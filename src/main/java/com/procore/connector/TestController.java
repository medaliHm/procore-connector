package com.procore.connector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class TestController {

   

    @PostMapping("/webhook")
    public ResponseEntity<String> tryIt( @RequestBody Object body){
    	try {
    	ObjectMapper mapper = new ObjectMapper();
    	
			String json = mapper.writeValueAsString(body);
			System.out.println(json);
	    	return new ResponseEntity<String> (json,HttpStatus.OK);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ResponseEntity<String> ("ERROR WHILE PARSING JSON",HttpStatus.OK);

    }
}
