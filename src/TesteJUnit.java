
import junit.framework.*;

public class TesteJUnit extends TestCase{
	public void testGetTexto(){
		String t = "teste junit!!";
		assertEquals(t, Teste.getTexto(t));
	}
	
	public void testTestes(){
		System.out.println("antes");
		assertEquals(true, true);
		System.out.println("depois");
	}
	
}