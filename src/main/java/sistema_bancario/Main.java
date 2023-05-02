package sistema_bancario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import dao.ClienteDAO;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;
import model.ContaEspecial;
import model.ContaPoupanca;
import model.TipoConta;

public class Main {

	public static void main(String[] args) throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ClienteDAO clienteDAO = new ClienteDAO();
		ContaDAO contaDAO = new ContaDAO();
		Scanner in = new Scanner(System.in);
		String opcao = "X";
		Conta contaPessoa;


		do {
			opcao = "1";
			System.out.println("Seja bem vindo ao sistema bancário. Escolha abaixo o que deseja fazer");
			System.out.println("1 - Cadastros");
			System.out.println("2 - Operações");
			System.out.println("3 - Alterar o limite de crédito");
			System.out.println("S - Sair");
			opcao = in.nextLine();

			if (opcao.equals("1")) {
				String opcaoCadastro;

				System.out.println("----------CADASTROS----------");
				System.out.println("1- Cadastro de cliente");
				System.out.println("2- Cadastro de conta");
				opcaoCadastro = in.nextLine();

				if (opcaoCadastro.equals("1")) {
					try {

						Cliente cliente = new Cliente();
						System.out.print("Nome:");
						cliente.setNome(in.nextLine());
						System.out.println("CPF:");
						cliente.setCpf(in.next());
						System.out.println("Data de Nascimento");
						cliente.setDataNascimento(LocalDate.parse(in.next(), formatter));
						clienteDAO.persist(cliente);
						System.out.println("Cliente cadastrado com sucesso!");
					} catch (Exception e) {
						System.out.println("Ocorreu um erro ao cadastrar o cliente.");
					}
				}

				else if (opcaoCadastro.equals("2")) {

					Float limiteSaque = 0.0f;
					Float limiteTransferencia = 0.0f;
					Integer idCliente;
					String tipoConta;

					Conta conta = new Conta();
					conta.setSaldo(0.00f);
					conta.setDataAbertura(LocalDate.now());

					System.out.println("Para qual cliente deseja abrir a conta?");

					for (Cliente cliente : clienteDAO.getAll()) {
						System.out.println(cliente.getId() + "-" + cliente.getNome());
					}
					idCliente = in.nextInt();

					Cliente clienteConta = clienteDAO.getById(idCliente);

					System.out.println("Insira o valor do limite de saque");
					limiteSaque = in.nextFloat();
					System.out.println("Insira o valor do limite de transferência");
					limiteTransferencia = in.nextFloat();

					System.out.println("Qual o tipo de conta que deseja abrir?");
					System.out.println(TipoConta.COMUM.tipoConta() + "- Conta Comum");
					System.out.println(TipoConta.ESPECIAL.tipoConta() + "- Conta Especial");
					System.out.println(TipoConta.POUPANCA.tipoConta() + "- Conta Poupança");
					tipoConta = in.next();

					if (tipoConta.equals(TipoConta.ESPECIAL.tipoConta())) {
						ContaEspecial contaEspecial = new ContaEspecial();
						contaEspecial.setCliente(clienteConta);
						contaEspecial.setLimiteSaque(limiteSaque);
						contaEspecial.setLimiteTransferencia(limiteTransferencia);
						contaEspecial.setSaldo(0.0f);
						contaEspecial.setDataAbertura(LocalDate.now());
						contaEspecial.setTipoConta(TipoConta.ESPECIAL.tipoConta());

						System.out.println("Digite o valor do limite especial");
						contaEspecial.setLimiteCredito(in.nextFloat());

						contaDAO.persist(contaEspecial);
					}

					else if (tipoConta.equals(TipoConta.POUPANCA.tipoConta())) {
						ContaPoupanca contaPoupanca = new ContaPoupanca();
						contaPoupanca.setCliente(clienteConta);
						contaPoupanca.setLimiteSaque(limiteSaque);
						contaPoupanca.setLimiteTransferencia(limiteTransferencia);
						contaPoupanca.setSaldo(0.0f);
						contaPoupanca.setDataAbertura(LocalDate.now());
						contaPoupanca.setTipoConta(TipoConta.ESPECIAL.tipoConta());

						System.out.println("Insira o dia de aniversário da conta");
						contaPoupanca.setDiaAniversario(in.nextInt());
						contaDAO.persist(contaPoupanca);
					} else if (tipoConta.equals(TipoConta.COMUM.tipoConta())) {
						Conta contaComum = new Conta();
						contaComum.setCliente(clienteConta);
						contaComum.setDataAbertura(LocalDate.now());
						contaComum.setLimiteSaque(limiteSaque);
						contaComum.setLimiteTransferencia(limiteTransferencia);
						contaComum.setSaldo(0.0f);
						contaComum.setTipoConta(TipoConta.COMUM.tipoConta());
						contaDAO.persist(contaComum);
					} else {
						System.out.println("Tipo de conta inválido");
					}
				}

			}

			else if (opcao.equals("2")) {
				Integer clienteId;
				Integer idConta;
				Float valorDeposito;
				String escolhaOperacao;
				List<Cliente> clientes = clienteDAO.getAll();
				System.out.println("Digite o código do cliente que deseja realizar operações");
				System.out.println("---------------------------CLIENTES----------------------------");
				System.out.println(StringUtils.center("ID", 10, "") + StringUtils.center("NOME", 70, ""));
				System.out.println("---------------------------------------------------------------");
				for (Cliente cliente : clientes) {

					System.out.println(StringUtils.center(cliente.getId().toString(), 10, "")
							+ StringUtils.center(cliente.getNome(), 70, ""));
					System.out.println("---------------------------------------------------------------");
				}
				clienteId = in.nextInt();

				List<Conta> contas = contaDAO.getByClientId(clienteDAO.getById(clienteId));

				System.out.println("Escolha a conta que deseja realizar a operação");

				System.out.println("---------CONTAS----------");
				System.out.println("|   ID    |" + "|  SALDO  |");
				System.out.println("---------------------------");
				for (Conta c : contas) {
					if (c.getId() != null) {

						System.out.println(StringUtils.center(c.getId().toString(), 10, "")
								+ StringUtils.center("R$" + c.getSaldo().toString(), 10, ""));
						System.out.println("---------------------------");
					}
				}
				idConta = in.nextInt();
				contaPessoa = contaDAO.getById(idConta);

				System.out.println("O que deseja fazer?");
				System.out.println("1- Depósito");
				System.out.println("2- Saque");
				System.out.println("3- Transferência");
				escolhaOperacao = in.next();

				if (escolhaOperacao.equals("1")) {
					System.out.println("Digite o valor que deseja depositar");
					valorDeposito = in.nextFloat();

					contaPessoa.setSaldo(contaPessoa.getSaldo() + valorDeposito);
					contaDAO.persist(contaPessoa);

					System.out.println("Valor da conta atualizado: " + contaPessoa.getSaldo());
				} 
				
					else if (escolhaOperacao.equals("2")) {
					Float valorSaque;
					System.out.println("Digite o valor que deseja sacar");
					valorSaque = in.nextFloat();

					if (valorSaque > contaPessoa.getLimiteSaque()) {
						throw new Exception("Valor ultrapassa o limtie de saque permitido");
					} else if ((contaPessoa.getSaldo() - valorSaque) < 0
							&& contaPessoa.getTipoConta().equals(TipoConta.COMUM.tipoConta()))
						throw new Exception("Uma Conta Comum não pode ter saldo menor que 0");

					contaPessoa.setSaldo(contaPessoa.getSaldo() - valorSaque);
					contaDAO.persist(contaPessoa);

					System.out.println("Valor da conta atualizado: " + contaPessoa.getSaldo());
				} 	
					else if (escolhaOperacao.equals("3")) {
					Integer idContaDestino;
					contas = contaDAO.getAll();
					Float valorTransferencia;
					Conta contaDestino;

					System.out.println("Escolha a conta de destino");

					System.out.println("---------CONTAS----------");
					System.out.println("|   ID    |" + "|  SALDO  |");
					System.out.println("---------------------------");

					for (Conta c : contas) {
						if (c.getId() != null) {
							System.out.println(StringUtils.center(c.getId().toString(), 10, "")
									+ StringUtils.center("R$" + c.getSaldo().toString(), 10, ""));
							System.out.println("---------------------------");
						}
					}
					idContaDestino = in.nextInt();
					contaDestino = contaDAO.getById(idContaDestino);

					System.out.println("Digite o valor que deseja transferir");
					valorTransferencia = in.nextFloat();

					if (valorTransferencia > contaPessoa.getLimiteTransferencia())
						throw new Exception("Valor ultrapassa o limtie de transfêrencia permitido");

					contaPessoa.setSaldo(contaPessoa.getSaldo() - valorTransferencia);
					contaDestino.setSaldo(contaDestino.getSaldo() + valorTransferencia);

					contaDAO.persist(contaPessoa);
					contaDAO.persist(contaDestino);

					contas = contaDAO.getAll();

					for (Conta c : contas) {
						if (c.getId() != null) {
							System.out.println(StringUtils.center(c.getId().toString(), 10, "")
									+ StringUtils.center("R$" + c.getSaldo().toString(), 10, ""));
							System.out.println("---------------------------");
						}

					}
				}
			} else if (opcao.equals("3")) {
				List<ContaEspecial> contasEspeciais = contaDAO.getAllContaEspecial();

				System.out.println("---------CONTAS----------");
				System.out.println("|   ID    |" + "|  LIMITE  |");
				System.out.println("---------------------------");

				for (ContaEspecial c : contasEspeciais) {
					if (c.getId() != null) {
						System.out.println(StringUtils.center(c.getId().toString(), 10, "")
								+ StringUtils.center("R$" + c.getLimiteCredito(), 10, ""));
						System.out.println("---------------------------");
					}
				}

				System.out.println("Digite o id da conta que deseja alterar o limite de crédito");
				Integer idConta = in.nextInt();
				System.out.println("Novo Valor de crédito");
				Float valorCredito = in.nextFloat();
				ContaEspecial conta = contaDAO.getByIdEspecial(idConta);

				conta.setLimiteCredito(valorCredito);

				contaDAO.persist(conta);

				contasEspeciais = contaDAO.getAllContaEspecial();

				System.out.println("---------CONTAS----------");
				System.out.println("|   ID    |" + "|  LIMITE  |");
				System.out.println("---------------------------");

				for (ContaEspecial c : contasEspeciais) {
					if (c.getId() != null) {
						System.out.println(StringUtils.center(c.getId().toString(), 10, "")
								+ StringUtils.center("R$" + c.getLimiteCredito(), 10, ""));
						System.out.println("---------------------------");
					}
				}
			}
			else 
			{
				System.out.print("Opção: " + opcao);
				System.out.println("Opção Inválida!");
			}
		
		}while(!opcao.equals("S"));

	}
}
