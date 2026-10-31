package logica.manejadores;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;

public class ManejadorImagen {
	private static ManejadorImagen instance;
	private static Map<String, BufferedImage> imgs;
	
	private ManejadorImagen() {
        imgs = new HashMap<String, BufferedImage>();
    }

    public static ManejadorImagen getInstance() {
        if (instance == null) {
            instance = new ManejadorImagen();
        }
        return instance;
    }
	
    public boolean addImg(BufferedImage prt, String key) {
        try {
            if (prt==null) {
            	return false;
            }
            imgs.put(key, prt);
            return true;
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public BufferedImage getImg(String key) {
		if (imgs.containsKey(key)) {
			return imgs.get(key);
		}
		return null;
	}
	public String getImg64(String key) {
		if (imgs.containsKey(key)) {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try {
				ImageIO.write(imgs.get(key), "png", baos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
		}
		return null;
	}
	public void addImg(String b64, String key) {
		byte[] bytes = Base64.getDecoder().decode(b64);
	    try {
			imgs.put(key, ImageIO.read(new ByteArrayInputStream(bytes)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
