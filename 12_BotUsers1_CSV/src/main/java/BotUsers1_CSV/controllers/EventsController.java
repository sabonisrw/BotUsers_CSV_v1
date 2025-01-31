package BotUsers1_CSV.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BotUsers1_CSV.models.EventsModel;
import BotUsers1_CSV.services.EventsService;

@RestController
@RequestMapping("/Eventv1")
public class EventsController {
	@Autowired
	EventsService eventsService;
	
	//regresa todos los eventos
	@GetMapping()
	public ArrayList<EventsModel> queryAllEvents(){
		return eventsService.findAllEvents();
	}

	//agregar evento
	@PostMapping()
	public EventsModel addEvent(@RequestBody EventsModel eventModel) {
		return this.eventsService.addEvent(eventModel);
	}
	
	//borrar evento por id
	@DeleteMapping(path="/delevent/{id}")
	public String deleteById (@PathVariable("id") Long Id) {
		boolean ok= this.eventsService.deleteEventById(Id);
		if (ok) {
			return "Se eliminó el Evento con el ID " +Id;
		}else {
			return "No se pudo eliminar el Evento con el ID " +Id;
		}			
	}
	
	//consultar por Id del evento
	@GetMapping(path="/EventId/{id}")
	public Optional<EventsModel> queryById (@PathVariable("id") Long Id){
		return this.eventsService.findById(Id);
	}
	
	//consultar eventos por fecha
	@GetMapping(path="/dateEvents/{dateEvent}")
	public ResponseEntity<?> queryByDate (@PathVariable("dateEvent") @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss") LocalDateTime dateEvent){
		try {
			ArrayList<EventsModel> events = eventsService.findByStarDate(dateEvent);
			return new ResponseEntity<>(events, HttpStatus.OK); 
		}catch (Exception e){
			return new ResponseEntity<>("Error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}