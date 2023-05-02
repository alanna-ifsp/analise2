package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "conta_poupanca")
@PrimaryKeyJoinColumn(name = "id_conta")
public class ContaPoupanca extends Conta {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@Column(name = "dia_aniversario")
	private Integer diaAniversario;

	public Integer getDiaAniversario() {
		return diaAniversario;
	}

	public void setDiaAniversario(Integer diaAniversario) {
		this.diaAniversario = diaAniversario;
	}

	@Override
	public String toString() {
		return "ContaPoupanca [id=" + id + "diaAniversario=" + diaAniversario + ", saldo=" + saldo
				+ ", limiteTransferencia=" + limiteTransferencia + "]";
	}

}
