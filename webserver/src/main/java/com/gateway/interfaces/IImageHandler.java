package com.gateway.interfaces;

import java.awt.image.BufferedImage;

import jakarta.servlet.http.Part;

public interface IImageHandler {
	
	public boolean addImg(Part prt, String key);
	public BufferedImage getImg(String key);
	public String getImg64(String key);
}
