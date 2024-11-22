package BotUsers1_CSV.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import BotUsers1_CSV.models.UsersModel;
import BotUsers1_CSV.repositories.UsersRepository;

@Service
public class FileCSVServiceImpl implements FileCSVService {

	@Autowired

	private UsersRepository usersRepository;
	@Override
	public boolean hasCsvFormat (MultipartFile file) {
		String type = "text/csv";
		if (!type.equals(file.getContentType()))
			return false;
		return true;
	}
	
	@Override
	public void processAndSaveData (MultipartFile file) {
		try {
			List<UsersModel> users = csvToUsers(file.getInputStream());
			usersRepository.saveAll(users);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private List<UsersModel> csvToUsers (InputStream inputStream) throws UnsupportedEncodingException, IOException{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato si es necesario
		try (BufferedReader fileReader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord())){
			List<UsersModel> usersModels = new ArrayList<UsersModel>();
			List<CSVRecord> records = csvParser.getRecords();
			for (CSVRecord csvRecord : records) {
				LocalDate dateBirth = LocalDate.parse(csvRecord.get("dateBirth"), dateFormatter);
				UsersModel usersModel = new UsersModel(
						Long.parseLong(csvRecord.get("Id")), 
						csvRecord.get("name"), 
						csvRecord.get("username"),
						csvRecord.get("generation"),
						csvRecord.get("specialty"),
						dateBirth
						);			
				usersModels.add(usersModel);
			}
			return usersModels;
		}catch (IOException e) {
			e.printStackTrace();
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
}
