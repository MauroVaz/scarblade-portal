package com.unip.tcc.scarblade.entity;

public class UserEntity {

	private String uuid;
	private String nome;
	private String email;
	private String telefone;
	private CarEntity car;
	private FaceEntity imagens;
	
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String uuid, String nome, String email, String telefone, CarEntity car, FaceEntity imagens) {
		super();
		this.uuid = uuid;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.car = car;
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "UserEntity [uuid=" + uuid + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", car="
				+ car + ", imagens=" + imagens + "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

	public FaceEntity getImagens() {
		return imagens;
	}

	public void setImagens(FaceEntity imagens) {
		this.imagens = imagens;
	}
	
	
	
	
}
