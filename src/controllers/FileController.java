package backend.controllers;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	
	@CrossOrigin
	@PostMapping("/files")
	public String uploadFile(@RequestHeader("FileName") String filename, @RequestBody String something) {
		System.out.println(something);
		try { 
		      FileWriter myWriter = new FileWriter(filename);
		      myWriter.write(something);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return "This arrived to the server: " + something;
	}

}
