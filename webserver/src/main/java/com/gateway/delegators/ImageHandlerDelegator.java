package com.gateway.delegators;


import com.gateway.connectors.ImageHandlerConnector;
import com.gateway.interfaces.IImageHandler;

import jakarta.servlet.http.Part;

public class ImageHandlerDelegator implements IImageHandler {
	private ImageHandlerConnector ihc;
	
	public ImageHandlerDelegator() {
		ihc=new ImageHandlerConnector();
	}

	@Override
	public boolean addImg(Part prt, String key) {
		return ihc.addImg(prt, key);
	}

	@Override
	public String getImg64(String key) {
		return ihc.getImg64(key);
	}

	@Override
	public void addImg(String b64, String key) {
		ihc.addImg(b64, key);
	}

}
