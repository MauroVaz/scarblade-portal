package com.unip.tcc.scarblade.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Named;

import org.apache.batik.css.engine.value.Value;
import org.primefaces.event.CaptureEvent;

import com.unip.tcc.scarblade.dao.CarDAO;
import com.unip.tcc.scarblade.dao.FacesDAO;
import com.unip.tcc.scarblade.dao.UserDAO;
import com.unip.tcc.scarblade.entity.CarEntity;
import com.unip.tcc.scarblade.entity.FaceEntity;
import com.unip.tcc.scarblade.entity.UserEntity;
import com.unip.tcc.scarblade.utils.ImagemUtils;

@Named
@SessionScoped
public class PhotoCamBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserEntity user = new UserEntity();
	private CarEntity car = new CarEntity();
	private FaceEntity face = new FaceEntity();
	private UserDAO userDao = new UserDAO();
	private CarDAO carDao = new CarDAO();
	private FacesDAO faceDao = new FacesDAO();
	private PhotoDesktopController photo = new PhotoDesktopController();

	public PhotoCamBean() {

	}

	

	public List<String> encoding(String id) {
		String path = "D:\\DEV\\Projetos\\scarblade-portal\\Imagem\\";
		List<String> f = new ArrayList<String>();
		try {
			for (int i = 1; i <= 25; i++) {
				String newFileName = path + "pessoa." + id + "." + i
						+ ".jpg";
				f.add(ImagemUtils.imageEncoding(newFileName));
			}
			return f;
		} catch (Exception e) {
			throw new FacesException("Error in writing captured image.");
		}
	}

	public void persistFormSigUp() {
		try {
			userDao.insertUser(user);
			String id = userDao.selectUserId(user.getNome());
			photo.takeImages(id);
			List<String> a = encoding(id);
			

			carDao.insertCar(car, id);
			faceDao.insertFaces(encoding(id), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

	public FaceEntity getFace() {
		return face;
	}

	public void setFace(FaceEntity face) {
		this.face = face;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
