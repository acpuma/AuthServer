package gov.diski.diskisso.web.dto;

import lombok.Getter;
import lombok.Setter;

public class GenericResponse {

	@Getter
	@Setter
	private boolean status;

	@Getter
	@Setter
	private String message;
	
	@Getter
	@Setter
	private Object object;

	public GenericResponse(boolean status) {
		this.status = status;
	}

	public GenericResponse(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public GenericResponse(boolean status, String message, Object object){
		this.status = status;
		this.message = message;
		this.object = object;
	}
}
