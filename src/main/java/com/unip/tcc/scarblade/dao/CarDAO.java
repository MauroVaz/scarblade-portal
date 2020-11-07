package com.unip.tcc.scarblade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.unip.tcc.scarblade.entity.CarEntity;
import com.unip.tcc.scarblade.factory.HikariCP3ConnectionFactory;

public class CarDAO {
		
	public void insertCar(CarEntity carEntity, String id) {
		try (Connection connection = HikariCP3ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = null;
			
			final String transaction = "INSERT INTO carros VALUES (default, ?,?,?,?,?)";

			try {
				pstmt = connection.prepareStatement(transaction);
	
				pstmt.setString(1, carEntity.getMarca());
				pstmt.setString(2, carEntity.getModelo());		
				pstmt.setInt(3, Integer.parseInt(carEntity.getAno()));		
				pstmt.setString(4, carEntity.getPlaca());		
				pstmt.setInt(5, Integer.parseInt(id));		
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
