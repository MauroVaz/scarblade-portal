package com.unip.tcc.scarblade.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import com.unip.tcc.scarblade.dao.CarDAO;
import com.unip.tcc.scarblade.dao.FacesDAO;
import com.unip.tcc.scarblade.dao.UserDAO;
import com.unip.tcc.scarblade.entity.CarEntity;
import com.unip.tcc.scarblade.entity.FaceEntity;
import com.unip.tcc.scarblade.entity.UserEntity;
import com.unip.tcc.scarblade.utils.ImagemUtils;

public class PhotoDesktopController {

	private UserEntity user = new UserEntity();
	private CarEntity car = new CarEntity();
	private FaceEntity face = new FaceEntity();
	private UserDAO userDao = new UserDAO();
	private CarDAO carDao = new CarDAO();
	private FacesDAO faceDao = new FacesDAO();

	public void takeImages(String idPessoa) throws org.bytedeco.javacv.FrameGrabber.Exception {

		OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
		OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
		Frame frameCapturado = null;
		Mat imagemColorida = new Mat();
		int numeroAmostras = 25;
		int amostra = 1;
		CascadeClassifier detectorFace = new CascadeClassifier(
				"D:\\DEV\\Projetos\\scarblade-treinamento\\src\\recursos\\haarcascade_frontalface_alt.xml");
		CanvasFrame cFrame = new CanvasFrame("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());

		try {
			
			camera.start();

			while ((frameCapturado = camera.grab()) != null) {
				imagemColorida = converteMat.convert(frameCapturado);
				Mat imagemCinza = new Mat();

				cvtColor(imagemColorida, imagemCinza, COLOR_BGRA2GRAY);
				RectVector facesDetectadas = new RectVector();
				detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(250, 250),
						new Size(500, 500));

				for (int i = 0; i < facesDetectadas.size(); i++) {
					Rect dadosFace = facesDetectadas.get(0);
					rectangle(imagemColorida, dadosFace, new Scalar(0, 0, 255, 0));
					Mat faceCapturada = new Mat(imagemCinza, dadosFace);
					resize(faceCapturada, faceCapturada, new Size(160, 160));

					if (amostra <= numeroAmostras) {
						imwrite("D:\\DEV\\Projetos\\scarblade-portal\\Imagem\\pessoa." + idPessoa + "." + amostra
								+ ".jpg", faceCapturada);
						System.out.println("Foto " + amostra + " capturada\n");
						amostra++;
					}

				}

				if (cFrame.isVisible()) {
					cFrame.showImage(frameCapturado);
				}

				if (amostra > numeroAmostras) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cFrame.dispose();
			camera.stop();
		}

	}

	

}
