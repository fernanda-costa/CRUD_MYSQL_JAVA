package console;

public class ConsoleSingle{
	
	private static Console lerTeclado;

	private ConsoleSingle() {
		
	}

	public static synchronized Console getConsole() {

		if (lerTeclado == null) {
			lerTeclado = new Console();
		}

		return lerTeclado;
	}
	
}
