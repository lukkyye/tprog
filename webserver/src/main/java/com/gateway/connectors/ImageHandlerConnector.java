package com.gateway.connectors;

import java.awt.image.BufferedImage;

import com.gateway.interfaces.IImageHandler;
import com.models.manejadores.ManejadorImagen;
import jakarta.servlet.http.Part;

public class ImageHandlerConnector implements IImageHandler {
	public static ManejadorImagen ci;
	
	public ImageHandlerConnector() {
		if (ci==null) {
			ci = ManejadorImagen.get_instance();
		}
	}

	@Override
	public boolean add_img(Part prt, String key) {
		// TODO Auto-generated method stub
		return ci.add_img(prt, key);
	}

	@Override
	public BufferedImage get_img(String key) {
		return ci.get_img(key);
	}

	@Override
	public String get_img64(String key) {
		// TODO Auto-generated method stub
		return ci.get_img64(key);
	}

}
