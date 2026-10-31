package com.gateway.interfaces;


import jakarta.servlet.http.Part;

public interface IImageHandler {
	
	public void addImg(String b64, String key);
	public boolean addImg(Part prt, String key);
	public String getImg64(String key);
}
