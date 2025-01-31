package BotUsers1_CSV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import BotUsers1_CSV.responses.ResponseMessageCSV;
import BotUsers1_CSV.services.FileCSVEventsService;

@RestController
@RequestMapping("/ImportEventsCSV")
public class CSVEventsController {
	@Autowired
	private FileCSVEventsService fileCSVEventsService;
	
	@PostMapping("uploadEvents")
	public ResponseEntity<ResponseMessageCSV> uploadFileCSV(@RequestParam("fileEvents") MultipartFile fileEvents){
		if (fileCSVEventsService.hasCsvFormat(fileEvents)) {
			fileCSVEventsService.processAndSaveEventData(fileEvents);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageCSV("Upload the events' file successfully: " +fileEvents.getOriginalFilename()));
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageCSV("Please upload CSV Events file"));
	}
}
