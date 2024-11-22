package BotUsers1_CSV.responses;

public class ResponseMessageCSV {
	private String message;

	public ResponseMessageCSV (String message) {
		super();
		this.message = message;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
