package com.unip.tcc.scarblade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.unip.tcc.scarblade.factory.HikariCP3ConnectionFactory;
import com.unip.tcc.scarblade.entity.UserEntity;

public class UserDAO {
	
	
	public String selectUserId(String nome) {
		
	
		try (Connection connection = HikariCP3ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			UserEntity userEntity = new UserEntity();
			
			final String selectUser = "SELECT * FROM usuarios where NOME = ?";
			try {
				pstmt = connection.prepareStatement(selectUser);
				pstmt.setString(1, nome);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					userEntity.setUuid(rs.getString("UUID"));
				}
			return userEntity.getUuid();
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally {
				if (connection != null) {
					connection.close();
					pstmt.close();
					rs.close();
				}
			}
	} catch (SQLException e1) {
		e1.printStackTrace();
		return null;
	}
}
	
	public void insertUser(UserEntity userEntity) {
		try (Connection connection = HikariCP3ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = null;
			
			final String transaction = "INSERT INTO usuarios VALUES (default, ?,?)";

			try {
				pstmt = connection.prepareStatement(transaction);
	
				pstmt.setString(1, userEntity.getNome());
				pstmt.setString(2, userEntity.getTelefone());		
				
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