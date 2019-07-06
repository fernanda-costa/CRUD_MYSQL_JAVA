package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import produto.ProdutoDAO;
import produto.Produto;
import console.Console;
import console.ConsoleSingle;

public class ProgramaPrincipalProduto {

	private final int LISTAR = 2;
	private final int CADASTRAR = 1;
	private final int APAGAR = 3;
	private final int ATUALIZAR = 4;
	private final int VOLTAR = 9;

	private Console lerTeclado;
	private ProdutoDAO gerProduto;
	private SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

	public ProgramaPrincipalProduto() {
		lerTeclado = ConsoleSingle.getConsole();
		gerProduto = new ProdutoDAO();
	}

	public static void main(String[] args) {
		new ProgramaPrincipalProduto().executar();
	}

	public void executar() {
		int opcao = 0;

		do {
			System.out.println("\n--------------PRODUTOS--------------");
			System.out.println(" 1 - Cadastrar produtos");
			System.out.println(" 2 - Listar Produtos");
			System.out.println(" 3 - Apagar Produtos");
			System.out.println(" 4 - Atualizar dados do produto");
			System.out.println(" 9 - Sair ");
			opcao = lerTeclado.readInt();

			if (opcao == CADASTRAR) {
				cadastrar();
			} else if (opcao == LISTAR) {
				listar();
			} else if (opcao == APAGAR) {
				apagar();
			} else if (opcao == ATUALIZAR) {
				atualizar();
			}

		} while (opcao != VOLTAR);

	}

	private void cadastrar() {
		Produto produto = criarProduto();
		gerProduto.salvar(produto);
		System.out.println("\nProduto cadastrado com sucesso.");
	}

	private Produto criarProduto() {
		String nome = lerTeclado.readLine("\nInsira o nome do produto.");
		int quantidade = lerTeclado.readInt("Insira a quantidade.");
		Date dataValidade = lerTeclado.readDate("Insira a data de validade.");

		Produto produto = new Produto();

		produto.setDataValidade(dataValidade);
		produto.setNome(nome);
		produto.setQuantidade(quantidade);

		return produto;
	}

	private void listar() {
		List<Produto> produtos = gerProduto.listar();

		for (int i = 0; i < produtos.size(); i++) {
			Produto prod = produtos.get(i);
			System.out.println("\n---------------------------------");
			System.out.println("ID: " + prod.getId());
			System.out.println("Nome: " + prod.getNome());
			System.out.println("Data de validade: "
					+ formatoData.format(prod.getDataValidade()));
			System.out.println("Quantidade: " + prod.getQuantidade());
		}

	}

	private void apagar() {
		listar();
		System.out.println("\n---------------------------------");
		int id = lerTeclado
				.readInt("\nInsira o ID do produto que deseja deletar.");
		gerProduto.remover(id);
		System.out.println("\nProduto apagado com sucesso.");
	}

	private void atualizar() {
		System.out.println("\n---------------------------------");
		System.out.println();
		int id = lerTeclado
				.readInt("\nInsira o ID do produto que deseja atualizar as informações.");
		Produto produto = criarProduto();
		produto.setId(id);
		gerProduto.atualizar(produto);
		System.out.println("\nDados atualizados com sucesso.");
	}

	public void fecharConexao() {

		gerProduto.fecharConexao();
	}

}
