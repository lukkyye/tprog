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
	
	private ManejadorImagen() {
        imgs = new HashMap<String, BufferedImage>();
    }

    public static ManejadorImagen get_instance() {
        if (instance == null) {
            instance = new ManejadorImagen();
        }
        return instance;
    }
	
    public boolean add_img(Part prt, String key) {
        try {
            BufferedImage imagen = ImageIO.read(prt.getInputStream());
            if (imagen == null) {
                return false;
            }
            imgs.put(key, imagen);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
