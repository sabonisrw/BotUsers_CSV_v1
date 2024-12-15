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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BotUsers1_CSV.models.UsersModel;
import BotUsers1_CSV.services.UsersService;

@RestController
@RequestMapping("/Usersv2")
public class UsersController {
	@Autowired
	UsersService usersService;	
	
 	//regresa todos los usuarios
	@GetMapping()
	public ArrayList<UsersModel> queryAllUsers(){
		return usersService.findAllUsers();
	}
 	
	//agregar usuario del bot
	@PostMapping()
	public UsersModel addUsers (@RequestBody UsersModel usersModel) {
		return this.usersService.addUser(usersModel);
	}
	
	//borrar usuario del bot por id
	@DeleteMapping(path="/deluser/{id}")
	public String deleteById (@PathVariable("id") Long id) {
		boolean ok = this.usersService.deleteUsers(id);
		if (ok) {
			return "Se eliminó el Usuario con id " +id;
		}else {
			return "No se pudo eliminar el Usuario con id" +id;
		}
	}

	//consultar usuario del Bot por id
	@GetMapping(path="/ById/{id}")
	public Optional<UsersModel> queryById(@PathVariable("id") Long id){
		return this.usersService.findById(id);
	}
	
	//consultar usuario del Bot por username
	/*
	 * @GetMapping(path="/{username}")
	 
	public Optional<UserBotModel> queryByUser(@PathVariable("username") String username){
		return this.userBotService.findByUser(username);
	}
	*/
	
	/*
	//consultar usuario del Bot por cumpleaños (tipo fecha)
	@GetMapping(path="/ByDate/{date}")
	public ArrayList<UserBotModel> queryByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateBirth){
	//public String Hello(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date dateBirth) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(formatoFecha.format(dateBirth));
		//return " valor de "+formatoFecha.format(dateBirth)+ ". Otro2: "+dateBirth +". fin2";
		//return userBotService.findByDateBirth(formatoFecha.format(dateBirth));
	}
	*/

	/*
	@GetMapping(path="/sBirth/{sBirthday}")
	public ArrayList<UserBotModel> queryByBirthDay(@PathVariable("sBirthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sBirthday){
	//public String Hello(@PathVariable("sBirthday")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sBirthday) {
		//return " valor de "+sBirthday+ ". ";
		return userBotService.findByDateBirth(sBirthday);
	}
	*/

	
	////consultar usuario por cumpleaños
	@GetMapping(path="/sBirth/{sBirthday}")
	public ResponseEntity<?> queryByBirthDay(@PathVariable("sBirthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sBirthday){
		try {
			ArrayList<UsersModel> users = usersService.findByDateBirth(sBirthday);
			return new ResponseEntity<>(users, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
