package BotUsers1_CSV.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BotUsers1_CSV.models.GoalsModel;
import BotUsers1_CSV.services.GoalsService;

@RestController
@RequestMapping("Goalsv1")
public class GoalsController {
	@Autowired
	GoalsService goalsService;
	
	//regresa todos los objetivos
	@GetMapping()
	public ArrayList<GoalsModel> queryAllGoals(){
		return goalsService.findAllGoals();
	}
	
	//agregar objetivo
	@PostMapping()
	public GoalsModel addGoals(@RequestBody GoalsModel goalsModel) {
		return this.goalsService.addGoal(goalsModel);
	}
	
	//borrar objetivo por id
	@DeleteMapping(path="deleteGoal/{id}")
	public String deleteById (@PathVariable("id") Long id) {
		boolean ok = this.goalsService.deleteGoal(id);
		if (ok) {
			return "Se eliminó el objetivo con id " +id;
		}else{
			return "No se pudo eliminar el objetivo con id " +id;
		}
	}
	
	//consultar objetivo por id
	@GetMapping(path="/GoaldById/{id}")
	public Optional<GoalsModel> queryGoalById(@PathVariable("id") Long id){
		return this.goalsService.findById(id);
	}
	
	//consultar objetivo por usuario
	@GetMapping(path="/GoaldByUser/{userNameD}")
	public ArrayList<GoalsModel> queryByUsernameD(@PathVariable("userNameD") String userNameD){
		return this.goalsService.findByUserNameD(userNameD);
	}
	
	//consultar objetivos por fecha
	@GetMapping(path="/GoalByDate/{date}")
	public ResponseEntity<?> queryByDate(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date){
		try {
			ArrayList<GoalsModel> goals = goalsService.findByCreatedDate(date);
			return new ResponseEntity<> (goals, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//método para consultar objetivo por usuario y semana en curso (fecha)
	@PostMapping(path="/GoaldByUserDate")
	public ArrayList<GoalsModel> queryByUsernameDAndDateCreated(@RequestBody GoalsModel goalsModel){
		String usernameD = goalsModel.getUsernameD();
		LocalDate date = goalsModel.getDateCreated();
		return this.goalsService.findByUsernameDAndDateCreated(usernameD, date);
	}	
	
	//método para actualizar el campo Estado en Cpmpletado del Objetivo indicado por el id
	@PatchMapping(path="/UpdComplete/{id}")
	public GoalsModel updateGoal (@PathVariable Long id) {
		GoalsModel goalId = goalsService.findById(id).get();
		String status = "Completado";
		goalId.setStatus(status);
		return goalsService.updateGoal(goalId);		
	}
	
	//metodo para actualizar el campo Estado recibiendo como parámetros el id del objetivo y el valor del campo estado
	@PatchMapping(path="/UpdStat")
	public boolean updateStatGoal(@RequestBody GoalsModel goalsModel) {
		Long idgoal = goalsModel.getId();
		String status = goalsModel.getStatus();
		GoalsModel goalId = goalsService.findById(idgoal).get();
		goalId.setStatus(status);
		return goalsService.updateGoalStat(goalId);		
	}
}
