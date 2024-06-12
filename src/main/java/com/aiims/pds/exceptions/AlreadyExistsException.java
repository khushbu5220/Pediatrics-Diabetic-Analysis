package com.aiims.pds.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AlreadyExistsException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	
	String fields;
	
	public AlreadyExistsException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s already exists with %s : %s",resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	public AlreadyExistsException(String resourceName, String fieldName, String fields) {
		super(String.format("%s already exists with %s : %s",resourceName, fieldName, fields));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fields = fields;
	}
	
	
}
