package BotUsers1_CSV.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import BotUsers1_CSV.models.EventsModel;
import BotUsers1_CSV.repositories.EventsRepository;

@Service
public class FileCSVEventsServiceImpl implements FileCSVEventsService{
	@Autowired
	private EventsRepository eventsRepository;
	@Override
	public boolean hasCsvFormat (MultipartFile file) {
		String type = "text/csv";
		if (!type.equals(file.getContentType()))
			return false;
		return true;
	}
	
	@Override
	public void processAndSaveEventData (MultipartFile file) {
		try {
			List<EventsModel> events = csvToEvents(file.getInputStream());
			eventsRepository.saveAll(events);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<EventsModel> csvToEvents (InputStream inputStream) throws UnsupportedEncodingException, IOException{
		DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try (BufferedReader fileReader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord())){
					List<EventsModel> eventsModels = new ArrayList<EventsModel>();
					List<CSVRecord> records = csvParser.getRecords();
					for (CSVRecord csvRecord : records ) {
						LocalDateTime dateStarDateTime = LocalDateTime.parse(csvRecord.get("startDate"), dateTimeFormatter1);
						LocalDateTime dateEndDateTime = LocalDateTime.parse(csvRecord.get("endDate"), dateTimeFormatter2);
						EventsModel eventsModel = new EventsModel(
							Long.parseLong(csvRecord.get("Id")),
							csvRecord.get("eventName"),
							csvRecord.get("description"),
							csvRecord.get("urlEvent"),							
							dateStarDateTime,
							dateEndDateTime
							);
						eventsModels.add(eventsModel);
					}
					return eventsModels;
		}catch (IOException e) {
			e.printStackTrace();
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
}
