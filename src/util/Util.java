package util;

import java.util.UUID;

public class Util {

	public static String gerarCodigo() {
		return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		
	}

}
