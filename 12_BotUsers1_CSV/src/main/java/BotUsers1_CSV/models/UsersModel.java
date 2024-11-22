package BotUsers1_CSV.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class UsersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	private String name;
	private String username;
	private String generation;
	private String specialty;
	@Column(name = "date_birth", columnDefinition = "DATE")
	@JsonFormat(pattern="yyyy-MM-dd")    
	private LocalDate dateBirth;
	
	public UsersModel(Long id, String name, String username, String generation, String specialty, LocalDate dateBirth) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.generation = generation;
		this.specialty = specialty;
		this.dateBirth = dateBirth;
	}	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param user the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the generation
	 */
	public String getGeneration() {
		return generation;
	}
	/**
	 * @param generation the generation to set
	 */
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}
	/**
	 * @param specialty the specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	/**
	 * @return the dateBirth
	 */
	public LocalDate getDateBirth() {
		return dateBirth;
	}
	/**
	 * @param dateBirth the dateBirth to set
	 */
	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}


	public UsersModel() {
		super();
	}	
}
