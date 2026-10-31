package com.gateway.connectors;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;

import com.gateway.interfaces.IImageHandler;

import com.soap.Publisher;
import com.soap.PublisherService;

import jakarta.servlet.http.Part;

public class ImageHandlerConnector implements IImageHandler {
	private final PublisherService service;
    private final Publisher port;
	
	public ImageHandlerConnector() {
		service = new PublisherService();
        port = service.getPublisherPort();
	}

	@Override
	public String getImg64(String key) {
		// TODO Auto-generated method stub
		return port.getImg64(key);
	}

	@Override
	public void addImg(String b64, String key) {
		port.addImg(b64, key);
	}

	@Override
	public boolean addImg(Part prt, String key) {
		try {
			byte[] bytes = prt.getInputStream().readAllBytes();
			addImg(Base64.getEncoder().encodeToString(bytes), key);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
