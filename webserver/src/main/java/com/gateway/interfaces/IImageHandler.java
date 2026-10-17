package com.gateway.interfaces;

import java.awt.image.BufferedImage;

import jakarta.servlet.http.Part;

public interface IImageHandler {
	
	public boolean add_img(Part prt, String key);
	public BufferedImage get_img(String key);
	public String get_img64(String key);
}
