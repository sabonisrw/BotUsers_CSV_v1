package BotUsers1_CSV.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BotUsers1_CSV.models.EventsModel;
import BotUsers1_CSV.repositories.EventsRepository;

@Service
public class EventsService {
	@Autowired
	
	EventsRepository eventsRepository;
	
	//método para consultar todos los eventos registrados
	public ArrayList<EventsModel> findAllEvents(){
		return (ArrayList<EventsModel>) eventsRepository.findAll();
	}
	
	//método para agregar un nuevo evento
	public EventsModel addEvent (EventsModel eventsModel) {
		return eventsRepository.save(eventsModel);
	}

	//método para consultar por id del evento
	public Optional<EventsModel> findById (Long Id){
		return eventsRepository.findById(Id);
	}
	
	//método para eliminar Evento por Id
	public boolean deleteEventById(Long Id) {
		try {
			eventsRepository.deleteById(Id);
			return true;
		}catch (Exception err) {
			return false;
		}
	}
	
	//método para buscar por fecha de evento
	public ArrayList<EventsModel> findByStarDate (LocalDateTime dateEvent){
		return (ArrayList<EventsModel>) eventsRepository.findByStartDate(dateEvent);
	}
}
 