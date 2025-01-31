package BotUsers1_CSV.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="events")
public class EventsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	private String eventName;
	private String description;
	private String urlEvent;
	@Column(name = "startDate")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")    
	private LocalDateTime startDate;
	@Column(name = "endDate")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")    
	private LocalDateTime endDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrlEvent() {
		return urlEvent;
	}
	public void setUrlEvent(String urlEvent) {
		this.urlEvent = urlEvent;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public EventsModel(Long id, String eventName, String description, String urlEvent, LocalDateTime startDate,
			LocalDateTime endDate) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.urlEvent = urlEvent;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public EventsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
