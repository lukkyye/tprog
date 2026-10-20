package com.gateway.connectors;

import java.awt.image.BufferedImage;

import com.gateway.interfaces.IImageHandler;
import com.models.manejadores.ManejadorImagen;
import jakarta.servlet.http.Part;

public class ImageHandlerConnector implements IImageHandler {
	private static ManejadorImagen manejadorImagen;
	
	public ImageHandlerConnector() {
		if (manejadorImagen==null) {
			manejadorImagen = ManejadorImagen.getInstance();
		}
	}

	@Override
	public boolean addImg(Part prt, String key) {
		// TODO Auto-generated method stub
		return manejadorImagen.addImg(prt, key);
	}

	@Override
	public BufferedImage getImg(String key) {
		return manejadorImagen.getImg(key);
	}

	@Override
	public String getImg64(String key) {
		// TODO Auto-generated method stub
		return manejadorImagen.getImg64(key);
	}

}
