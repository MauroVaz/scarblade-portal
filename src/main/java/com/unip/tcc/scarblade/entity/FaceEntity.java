package com.unip.tcc.scarblade.entity;

import java.util.List;

public class FaceEntity {
	
	private List<String> imagem;

	public List<String> getImagem() {
		return imagem;
	}

	public void setImagem(List<String> imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaceEntity other = (FaceEntity) obj;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FaceEntity [imagem=" + imagem + "]";
	}

	public FaceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
