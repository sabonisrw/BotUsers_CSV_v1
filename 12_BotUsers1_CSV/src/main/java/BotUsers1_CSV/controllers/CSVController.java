package BotUsers1_CSV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import BotUsers1_CSV.responses.ResponseMessageCSV;
import BotUsers1_CSV.services.FileCSVService;

@RestController
@RequestMapping("/ImportCSV")
public class CSVController {
	@Autowired
	private FileCSVService fileCSVservice;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessageCSV> uploadFileCSV(@RequestParam("file") MultipartFile file ){
		if(fileCSVservice.hasCsvFormat(file)) {
			fileCSVservice.processAndSaveData(file);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageCSV("Upload the file successfully: "+file.getOriginalFilename()));
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageCSV("Please upload CSV file"));
	}
}