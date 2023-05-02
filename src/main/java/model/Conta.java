package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Conta")
@Inheritance(strategy = InheritanceType.JOINED)
public class Conta {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id 
	private Integer id;
	@Column(name = "data_abertura")
	private LocalDate dataAbertura; 
	Float saldo;
	@Column(name = "limite_saque")
	private Float limiteSaque; 
	@Column(name = "limite_transferencia")	
	Float limiteTransferencia;
	@ManyToOne 
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	@Column(name = "tipo_conta")
	private String tipoConta;
	

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	public Float getLimiteSaque() {
		return limiteSaque;
	}
	public void setLimiteSaque(Float limiteSaque) {
		this.limiteSaque = limiteSaque;
	}
	public Float getLimiteTransferencia() {
		return limiteTransferencia;
	}
	public void setLimiteTransferencia(Float limiteTransferencia) {
		this.limiteTransferencia = limiteTransferencia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	public String getTipoConta() {
		return tipoConta;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public static void deposito(Conta conta, Float valor) {
		conta.setSaldo(conta.getSaldo() + valor);
	}
	
	public static void saque(Conta conta, Float valor) throws Exception
	{
		if (valor > conta.getLimiteSaque())
			throw new Exception("Limite de saque ultrapassado!");
	}
	
	public static void transferencia(Conta contaOrigem, Conta contaDestino, Float valor)
	{
		contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
		contaDestino.setSaldo(contaDestino.getSaldo() + valor);
	}
	@Override
	public String toString() {
		return "Conta [dataAbertura=" + dataAbertura + ", saldo=" + saldo + ", limiteSaque=" + limiteSaque
				+ ", limiteTransferencia=" + limiteTransferencia + ", cliente=" + cliente + ", tipoConta=" + tipoConta+"]";
	}


	
}
