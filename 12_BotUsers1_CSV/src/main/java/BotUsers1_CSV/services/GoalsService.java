package BotUsers1_CSV.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BotUsers1_CSV.models.GoalsModel;
import BotUsers1_CSV.repositories.GoalsRepository;

@Service
public class GoalsService {
	@Autowired
	GoalsRepository goalRepository;
	
	//método para consultar todos los objetivos de los usuarios
	public ArrayList<GoalsModel> findAllGoals(){
		return (ArrayList<GoalsModel>) goalRepository.findAll();
	}
	
	//método para agregar un nuevo objetivo
	public GoalsModel addGoal (GoalsModel goalModel) {
		return goalRepository.save(goalModel);
	}
	
	//método para consultar por el ID del objetivo
	public Optional<GoalsModel> findById(Long Id){
		return goalRepository.findById(Id);
	}
	
	//método para eliminar Objetivo por Id
	public boolean deleteGoal(Long id) {
		try {
			goalRepository.deleteById(id);
			return true;
		}catch (Exception err){
			return false;
		}
	}
	
	//método para buscar objetivos por usernameBot
	public ArrayList<GoalsModel> findByUserNameD(String usernameD){
		return (ArrayList<GoalsModel>) goalRepository.findByUsernameD(usernameD);
	}
	
	//método para buscar objetivos por fecha de todos los usuarios
	public ArrayList<GoalsModel> findByCreatedDate(LocalDate date){
		return (ArrayList<GoalsModel>) goalRepository.findByDateCreated(date);
	}
	
	//método para buscar logro por usuario y fecha
	public ArrayList<GoalsModel> findByUsernameDAndDateCreated(String usernameD, LocalDate date){		
		return (ArrayList<GoalsModel>) goalRepository.findByUsernameDAndDateCreated(usernameD, date);
	}
	
	//método para actualizar el estado de un logro identificado por id
	public GoalsModel updateGoal (GoalsModel goal) {
		return goalRepository.save(goal);
	}
	
	public boolean updateGoalStat (GoalsModel goal) {
		try {
			goalRepository.save(goal);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}
}
