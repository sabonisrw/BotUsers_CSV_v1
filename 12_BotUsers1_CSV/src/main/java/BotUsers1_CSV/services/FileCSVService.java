package BotUsers1_CSV.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileCSVService {

	boolean hasCsvFormat(MultipartFile file);
	
	void processAndSaveData (MultipartFile file);
}
