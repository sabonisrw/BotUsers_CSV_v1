package BotUsers1_CSV.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BotUsers1_CSV.models.UsersModel;

@Repository

public interface UsersRepository extends JpaRepository<UsersModel, Long>{
	
	public abstract ArrayList<UsersModel> findByDateBirth(LocalDate sBirthday);	
	
}
