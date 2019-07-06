package main;

import java.util.List;

import usuario.UsuarioDAO;
import usuario.Usuario;
import console.Console;
import console.ConsoleSingle;

public class ProgramaPrincipalUsuario {

	private final int LISTAR = 2;
	private final int CADASTRAR = 1;
	private final int APAGAR = 3;
	private final int ATUALIZAR = 4;
	private final int VOLTAR = 9;

	private Console lerTeclado;
	private UsuarioDAO gerUsuario;

	public ProgramaPrincipalUsuario() {
		lerTeclado = ConsoleSingle.getConsole();
		gerUsuario = new UsuarioDAO();
	}

	public static void main(String[] args) {
		new ProgramaPrincipalUsuario().executar();
	}

	public void executar() {
		int opcao = 0;

		do {
			System.out.println("\n--------------USUARIOS--------------");
			System.out.println(" 1 - Cadastrar Usuarios");
			System.out.println(" 2 - Listar Usuarios");
			System.out.println(" 3 - Apagar Usuarios ");
			System.out.println(" 4 - Atualizar dados do Usuario");
			System.out.println(" 9 - Sair");
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
		Usuario usuario = criarUsuario();
		gerUsuario.salvar(usuario);
		System.out.println("\nUsuario cadastrado com sucesso.");
	}

	private Usuario criarUsuario() {

		String nome = lerTeclado.readLine("Insira o nome do usuario.");
		String login = lerTeclado.readString("Insira o login.");
		String senha = lerTeclado.readString("Insira a senha.");

		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		return usuario;
	}

	private void listar() {
		List<Usuario> usuarios = gerUsuario.listar();

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usr = usuarios.get(i);
			System.out.println("\n--------------------------------");
			System.out.println("ID: " + usr.getId());
			System.out.println("Nome: " + usr.getNome());
			System.out.println("Login: " + usr.getLogin());
		}
	}

	private void apagar() {
		listar();

		int id = lerTeclado
				.readInt("\nInsira o ID do usuario que deseja apagar.");
		gerUsuario.remover(id);
		System.out.println("\nUsuario apagado com sucesso.");
	}

	private void atualizar() {
		listar();

		int id = lerTeclado.readInt("\nInsira o ID do usuario que deseja atualizar os dados.");
		Usuario usuario = criarUsuario();
		usuario.setId(id);
		gerUsuario.atualizar(usuario);
		System.out.println("\nDados atualizados com sucesso.");

	}

	public void fecharConexao() {

		gerUsuario.fecharConexao();
	}

}
