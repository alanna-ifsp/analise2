package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "ContaEspecial")
@Table(name = "conta_especial")
@PrimaryKeyJoinColumn(name="id_conta")
public class ContaEspecial extends Conta {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id; 
	

	@Column(name = "limite_credito")
	private Float limiteCredito;

	public Float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	public String toString() {
		return "ContaEspecial [id=" + id + ", limiteCredito=" + limiteCredito + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
