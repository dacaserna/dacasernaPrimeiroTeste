import java.util.HashMap;

public class GeraTestesUnitariosGetSet {
	private static HashMap<String, String> atributos = new HashMap<String, String>();

	private static String aux = "private Date datMinimaFimVigencia;"
			+ "    private Date datFimVigencia;"
			+ "    private ConsultaCreditoPublicoAlvo consultaCreditoPublicoAlvo;"
			+ "    private EntidadeOrigem entidadeOrigem;"
			+ "    private Date datIniVigencia;"
			+ "    private String flgEnvio;"
			+ "    private String flgAceite;"
			+ "    private Integer numReenvio;"
			+ "    private String flgExclusao;"
			+ "    private Long oidCnsCrePalvoDtl;"
			+ "    private Date datAceite;"
			+ "    private Date datExclusao;"
			+ "    private Date datUltEnvio;";
	
	public static void main(String[] args) {
		populaAtributos();
		String classeTestada = "ConsultaCreditoPalvoDtl";
		criaMetodo(classeTestada);
	}
	
	public static void populaAtributos(){
		while(aux.length()>5){
			aux = aux.replace("private ", "");
			aux = aux.replace("protected ", "");
			aux = aux.replace(";    ", ";");
			atributos.put(aux.substring(aux.indexOf(" ")+1, aux.indexOf(";")), aux.substring(0, aux.indexOf(" "))); 
			aux = aux.substring(aux.indexOf(";")+1);
		}
	}
	
	public static void criaMetodo(String classe){
		boolean temList = false;
		boolean temBigDecimal = false;
		boolean temDate = false;
		for (String valor: atributos.values()){
			if (valor.contains("List") && temList == false){
				System.out.println("import java.util.List;\n"
						+ 		   "import java.util.ArrayList;");
				temList = true;
			}
			if (valor.contains("BigDecimal") && temBigDecimal == false){
				System.out.println("import java.math.BigDecimal;\n");
				temBigDecimal = true;
			}
			if (valor.contains("Date") && temDate == false){
				System.out.println("import java.util.Date;\n");
				temDate = true;
			}
		}
		
		/*if (atributos.containsValue("BigDecimal")){
			System.out.println("import java.math.BigDecimal;\n");
		}*/
		/*if (atributos.containsValue("Date")){
			System.out.println("import java.util.Date;\n");
		}*/
		System.out.println("import org.junit.Test;\n"
				+ "import static org.junit.Assert.assertEquals;\n"
				+ "\n"
				+ "public class " + classe + "Test {\n");
		for(String atributo: atributos.keySet()){
			String tipo = atributos.get(atributo);
			String primeiraLetra = atributo.substring(0, 1).toUpperCase();
			String atributoIn = atributo.replaceFirst(atributo.substring(0, 1), primeiraLetra);
			
			String metodos = "	@Test\n"
							+ "	public void testGet" + atributoIn + "(){\n"
							+ "		" + classe + " classeTestada = new " + classe + "();\n"
							+ "		" + tipo + " " + getNomVariavelAuxPorTipo(tipo) + " = " + getValorVariavelPorTipo(tipo) + ";\n"
							+ "		classeTestada.set" + atributoIn + "(" + getNomVariavelAuxPorTipo(tipo) + ");\n"
							+ "		assertEquals(" + getNomVariavelAuxPorTipo(tipo) + ", classeTestada.get" + atributoIn + "());\n"
							+ "	}\n"
							+ "		\n"
							+ "	@Test\n"
							+ "	public void testSet" + atributoIn + "(){\n"
							+ "		" + classe + " classeTestada = new " + classe + "();\n"
							+ "		" + tipo + " " + getNomVariavelAuxPorTipo(tipo) + " = " + getValorVariavelPorTipo(tipo) + ";\n"
							+ "		classeTestada.set" + atributoIn + "(" + getNomVariavelAuxPorTipo(tipo) + ");\n"
							+ "		assertEquals(" + getNomVariavelAuxPorTipo(tipo) + ", classeTestada.get" + atributoIn + "());\n"
							+ "	}\n";
			System.out.println(metodos);
		}
		System.out.println("}");
	}
	
	public static String getValorVariavelPorTipo(String value){
		if(value.contains("Long")||value.contains("long")){
			return "123l";
		}else if(value.contains("char")){
			return "'c'";
		}else if(value.contains("boolean")||value.contains("Boolean")){
			return "true";
		}else if(value.contains("Date")){
			return "new Date()";
		}else if(value.contains("Integer")){
			return "123";
		}else if(value.contains("int")){
			return "123";
		}else if(value.contains("Double")){
			return "123.22";
		}else if (value.contains("ArrayList")){
			return "new " + value+"()";
		}else if (value.contains("List")){
			return "new " + value.replaceFirst("List", "ArrayList")+"()";
		}else if(value.contains("String")){
			return "\"txt\"";
		}else if(value.contains("BigDecimal")){
			return "BigDecimal.valueOf(123.22)";
		}
		return "new " + value + "()";
	}
	
	public static String getNomVariavelAuxPorTipo(String value){
		if(value.contains("Long")){
			return "l";
		}else if(value.contains("char")){
			return "ch";
		}else if(value.contains("boolean")||value.contains("Boolean")){
			return "b";
		}else if(value.contains("Date")){
			return "data";
		}else if(value.contains("Integer")){
			return "i";
		}else if(value.contains("int")){
			return "i";
		}else if(value.contains("Double")){
			return "d";
		}else if(value.contains("String")){
			return "txt";
		}else if (value.contains("List")){
			return "lista";
		}else if(value.contains("BigDecimal")){
			return "bd";
		}
		return value.substring(0, 1).toLowerCase();
	}
	
	
}
