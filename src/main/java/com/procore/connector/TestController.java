package com.procore.connector;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.procore.connector.models.webhook.Event;
import com.procore.connector.service.SyncService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

	@Autowired
	SyncService serviceSync;

	@PostMapping("/hooks")
	public ResponseEntity<String> tryIt(@RequestBody Event body) throws IOException {
		System.err.println(body.toString());
		serviceSync.processEvent(body);
		return new ResponseEntity<String>(body.toString(), HttpStatus.OK);

	}
	
	@PostMapping("/try")
	public ResponseEntity<String> test(@RequestBody Object body) throws IOException {
		System.out.println(body.toString());
		return new ResponseEntity<String>(body.toString(), HttpStatus.OK);

	}
	@PostMapping("/tryit")
	public ResponseEntity<String> test2() throws IOException {
		System.out.println("test");
		return new ResponseEntity<String>("test", HttpStatus.OK);

	}
}
