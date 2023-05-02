package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Cliente;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-04-30T22:48:18")
@StaticMetamodel(Conta.class)
public class Conta_ { 

    public static volatile SingularAttribute<Conta, Cliente> cliente;
    public static volatile SingularAttribute<Conta, Integer> id;
    public static volatile SingularAttribute<Conta, Float> saldo;
    public static volatile SingularAttribute<Conta, Float> limiteSaque;
    public static volatile SingularAttribute<Conta, Float> limiteTransferencia;
    public static volatile SingularAttribute<Conta, String> tipoConta;
    public static volatile SingularAttribute<Conta, LocalDate> dataAbertura;

}