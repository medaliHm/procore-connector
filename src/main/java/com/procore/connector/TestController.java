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
		
		  //Getting the version
		  String command = "powershell.exe  $connectTestResult = Test-NetConnection -ComputerName csb100320011d345519.file.core.windows.net -Port 445\r\n"
		  		+ "if ($connectTestResult.TcpTestSucceeded) {cmd.exe /C \"cmdkey /add:`\"csb100320011d345519.file.core.windows.net`\" /user:`\"Azure\\csb100320011d345519`\" /pass:`\"TsVDIQkkGAPxH0e0wdEQx8OSu3TWv56xvJwLX8F/2RuvO2F/JDnnL9XnA36GLD9PzKZ3zgMXWDTQUXwztkgUtQ==`\"\" New-PSDrive -Name Z -PSProvider FileSystem -Root \"\\\\csb100320011d345519.file.core.windows.net\\share-procore\" -Persist } else { Write-Error -Message \"Unable to reach the Azure storage account via port 445. Check to make sure your organization or ISP is not blocking port 445, or use Azure P2S VPN, Azure S2S VPN, or Express Route to tunnel SMB traffic over a different port.\"}";
		  // Executing the command
		  Process powerShellProcess = Runtime.getRuntime().exec(command);
		  // Getting the results
		  powerShellProcess.getOutputStream().close();
		  String line;
		  System.err.println("Standard Output:");
		  BufferedReader stdout = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getInputStream()));
		  while ((line = stdout.readLine()) != null) {
		   System.err.println(line);
		  }
		  stdout.close();
		  System.err.println("Standard Error:");
		  BufferedReader stderr = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getErrorStream()));
		  while ((line = stderr.readLine()) != null) {
		   System.err.println(line);
		  }
		  stderr.close();
		  System.err.println("Done");

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
