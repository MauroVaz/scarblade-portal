package com.unip.tcc.scarblade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.unip.tcc.scarblade.entity.UserEntity;
import com.unip.tcc.scarblade.factory.HikariCP3ConnectionFactory;

public class FacesDAO {
	
	public void insertFaces(List<String> encodingImages,String id) {
		try (Connection connection = HikariCP3ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = null;
			
			final String transaction = "INSERT INTO imagens VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			try {
				pstmt = connection.prepareStatement(transaction);
	
				pstmt.setInt(1, Integer.parseInt(id));
				pstmt.setString(2, encodingImages.get(0));
				pstmt.setString(3, encodingImages.get(1));
				pstmt.setString(4, encodingImages.get(2));
				pstmt.setString(5, encodingImages.get(3));
				pstmt.setString(6, encodingImages.get(4));
				pstmt.setString(7, encodingImages.get(5));
				pstmt.setString(8, encodingImages.get(6));
				pstmt.setString(9, encodingImages.get(7));
				pstmt.setString(10, encodingImages.get(8));
				pstmt.setString(11, encodingImages.get(9));
				pstmt.setString(12, encodingImages.get(10));
				pstmt.setString(13, encodingImages.get(11));
				pstmt.setString(14, encodingImages.get(12));
				pstmt.setString(15, encodingImages.get(13));
				pstmt.setString(16, encodingImages.get(14));
						
				
				pstmt.executeUpdate();
				pstmt.getConnection().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if (connection != null) {
					connection.close();
					pstmt.close();
				}
			}
		} catch (SQLException e1) {
		e1.printStackTrace();
		}
	}
	
}
