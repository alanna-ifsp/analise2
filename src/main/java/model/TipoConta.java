package model;

public enum TipoConta {
	
	COMUM("C"),
	ESPECIAL("E"),
	POUPANCA("P");

	String valor;
	TipoConta(String valor) {
		this.valor = valor;
	}
	
	public String tipoConta()
	{
		return valor;
	}
	

}
