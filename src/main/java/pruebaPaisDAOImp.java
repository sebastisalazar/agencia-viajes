import java.util.ArrayList;

import modelo.Pais;
import modelo.PaisDAOImp;

public class pruebaPaisDAOImp {

	public static void main(String[] args) throws Exception {
		
		
		PaisDAOImp dao= PaisDAOImp.getInstance();
		ArrayList<Pais> lista=dao.getAll();
		
		for (Pais pais : lista) {
			System.out.println(pais);
		}

	}

}
