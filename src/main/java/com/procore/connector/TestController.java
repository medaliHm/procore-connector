package com.procore.connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.procore.connector.models.webhook.Event;
import com.procore.connector.service.SyncService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

	@Autowired
	SyncService serviceSync;
	@Value("${shared.drive}")
	private String shareDrive;
	@PostMapping("/hooks")
	public ResponseEntity<String> tryIt(@RequestBody Event body) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.err.println(mapper.writeValueAsString(body));
		serviceSync.processEvent(body);
		return new ResponseEntity<String>(body.toString(), HttpStatus.OK);

	}
	
	
	public  void runPower() throws IOException {
		
		 ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "cmdkey", "/add:csb100320011d345519.file.core.windows.net /user:Azure\\\\csb100320011d345519 /pass:TsVDIQkkGAPxH0e0wdEQx8OSu3TWv56xvJwLX8F/2RuvO2F/JDnnL9XnA36GLD9PzKZ3zgMXWDTQUXwztkgUtQ==",
		            "net use Z: \\\\csb100320011d345519.file.core.windows.net\\share-procore /persistent:Yes\r\n");
		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            if (line == null) { break; }
		            System.err.println(line);
		        }
		       

	}
	@PostMapping("/try")
	public ResponseEntity<String> test(@RequestBody Object body) throws IOException {
		runPower();
		File f = new File("Z:\\");
		System.err.println(f.exists());
		System.err.println(body.toString());
		return new ResponseEntity<String>(body.toString(), HttpStatus.OK);

	}
	@PostMapping("/tryit")
	public ResponseEntity<String> test2() throws IOException {
		System.err.println("test");
		return new ResponseEntity<String>("test", HttpStatus.OK);

	}
	
	
}
