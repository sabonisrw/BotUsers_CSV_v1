package BotUsers1_CSV.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BotUsers1_CSV.models.EventsModel;

@Repository
public interface EventsRepository extends JpaRepository<EventsModel, Long> {
	
	public abstract ArrayList<EventsModel> findByStartDate(LocalDateTime dateEvent);  
}
