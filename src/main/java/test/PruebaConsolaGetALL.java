package test;
import java.util.ArrayList;

import modelo.Pais;
import modelo.Ciudad;
import modelo.CiudadDAOImp;

public class PruebaConsolaGetALL {

	public static void main(String[] args) throws Exception {
		
		
		CiudadDAOImp dao= CiudadDAOImp.getInstance();
		ArrayList<Ciudad> lista=dao.getAll();
		
		for (Ciudad ciudad : lista) {
			System.out.println(ciudad);
		}

	}

}
