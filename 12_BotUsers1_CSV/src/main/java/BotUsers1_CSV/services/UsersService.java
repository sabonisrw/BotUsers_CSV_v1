package BotUsers1_CSV.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BotUsers1_CSV.models.UsersModel;
import BotUsers1_CSV.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired

	UsersRepository usersRepository;
	
	//método para consultar todos los usuarios del bot registrados
	public ArrayList<UsersModel> findAllUsers(){
		return (ArrayList<UsersModel>) usersRepository.findAll();
	}
	
	//método para agregar un nuevo usuario del bot
	public UsersModel addUser (UsersModel usersModel) {
		return usersRepository.save(usersModel);
	}
	
	//método para consultar por un usuario por ID
	public Optional<UsersModel> findById (Long id){
		return usersRepository.findById(id);
	}
	
	//método para eliminar Usuario por Id
	public boolean deleteUsers(Long id) {
		try {
			usersRepository.deleteById(id);
			return true;
		}catch (Exception err) {
			return false;
		}
	}
	
	//método para buscar por usuario
	/*
	public Optional<UserBotModel> findByUser(String username){
		return Optional.ofNullable(userBotRepository.findByUser(username));
	}
	*/

	//método para buscar por fecha de nacimiento
	/*
	public ArrayList<UserBotModel> findByDateBirth(Date date){
		return (ArrayList<UserBotModel>) userBotRepository.findByDateBirth(date);
	}
	*/

	//método para buscar por fecha de nacimiento
	public ArrayList<UsersModel> findByDateBirth(LocalDate sBirthday){
		return (ArrayList<UsersModel>) usersRepository.findByDateBirth(sBirthday);
	}
}
