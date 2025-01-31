package BotUsers1_CSV.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileCSVEventsService {

	boolean hasCsvFormat(MultipartFile file);
	
	void processAndSaveEventData (MultipartFile file);

}
