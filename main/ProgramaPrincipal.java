package main;


import console.Console;
import console.ConsoleSingle;

public class ProgramaPrincipal {

	private final int USUARIO = 1;
	private final int PRODUTO = 2;
	private final int SAIR = 9;

	private Console lerTeclado;
	private ProgramaPrincipalProduto progProduto;
	private ProgramaPrincipalUsuario progUsuario;
		

	public ProgramaPrincipal() {
		lerTeclado = ConsoleSingle.getConsole();
		progProduto = new ProgramaPrincipalProduto();
		progUsuario = new ProgramaPrincipalUsuario();
	}

	public static void main (String [ ] args){
		new ProgramaPrincipal().executar();
	}

	private void executar() {
		int opcao = 0;
		do {
			System.out.println("USUARIOS - PRODUTOS ");
			System.out.println(" 1 - Usuarios");
			System.out.println(" 2 - Produtos");
			System.out.println(" 9 - Sair ");
			opcao = lerTeclado.readInt();

			if(opcao == USUARIO){
				executarProgramaUsuario();
			}
			else if (opcao == PRODUTO){
				executarProgramaProduto();
			}
			else if(opcao == SAIR){
				fecharConexao();
			}

		} while (opcao != 9);
	}

	private  void executarProgramaUsuario() {
		progUsuario.executar();
	}

	private  void executarProgramaProduto() {
		progProduto.executar();

	}
	
	public void fecharConexao(){

            progProduto.fecharConexao();
            progUsuario.fecharConexao();
	}
}


