package com.models.manejadores;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import javax.imageio.ImageIO;
import jakarta.servlet.http.Part;

public class ManejadorImagen {
	private static ManejadorImagen instance;
	private static HashMap<String, BufferedImage> imgs;
	
	public static ManejadorImagen get_instance() {
		if (instance==null) {
			instance=new ManejadorImagen();
			imgs=new HashMap<String, BufferedImage>();
		}
		return instance;
	}
	
	public boolean add_img(Part prt, String key) {
		BufferedImage imagen=null;
		try {
			imagen = ImageIO.read(prt.getInputStream());
			imgs.put(key, imagen);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public BufferedImage get_img(String key) {
		if (imgs.containsKey(key)) {
			return imgs.get(key);
		}
		return null;
	}
	public String get_img64(String key) {
		if (imgs.containsKey(key)) {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try {
				ImageIO.write(imgs.get(key), "png", baos);
			} catch (IOException e) {
				
			}
			return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
		}
		return null;
	}
}
