package com.socks.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserGenerationResponse{

	@JsonProperty("id")
	private String id;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"UserGenerationResponse{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}