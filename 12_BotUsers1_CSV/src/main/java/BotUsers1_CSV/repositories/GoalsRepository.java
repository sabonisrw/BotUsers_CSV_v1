package BotUsers1_CSV.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BotUsers1_CSV.models.GoalsModel;

@Repository
public interface GoalsRepository extends JpaRepository<GoalsModel, Long>{
	
	public abstract ArrayList<GoalsModel> findByUsernameD(String usernameD);
	
	public abstract ArrayList<GoalsModel> findByDateCreated(LocalDate date);
	
	public abstract ArrayList<GoalsModel> findByUsernameDAndDateCreated(String usernameD, LocalDate dateCreated);
}
