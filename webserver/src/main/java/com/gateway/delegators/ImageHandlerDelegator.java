package com.gateway.delegators;

import java.awt.image.BufferedImage;

import com.gateway.connectors.ImageHandlerConnector;
import com.gateway.interfaces.IImageHandler;

import jakarta.servlet.http.Part;

public class ImageHandlerDelegator implements IImageHandler {
	private ImageHandlerConnector ihc;
	
	public ImageHandlerDelegator() {
		ihc=new ImageHandlerConnector();
	}

	@Override
	public boolean add_img(Part prt, String key) {
		return ihc.add_img(prt, key);
	}

	@Override
	public BufferedImage get_img(String key) {
		return ihc.get_img(key);
	}

	@Override
	public String get_img64(String key) {
		return ihc.get_img64(key);
	}

}
