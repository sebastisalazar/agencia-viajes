package test;
import java.util.ArrayList;

import modelo.Pais;
import modelo.Ciudad;
import modelo.CiudadDAOImp;
import modelo.Continente;
import modelo.ContinenteDAOImp;
import modelo.PaisDAOImp;

public class PruebaConsolaGetALL {

	public static void main(String[] args) throws Exception {
		
		
		CiudadDAOImp daoCiudad= CiudadDAOImp.getInstance();
		ArrayList<Ciudad> ciudades=daoCiudad.getAll();
		
		System.out.println("\n\nCIUDAD.GETALL");
		for (Ciudad ciudad : ciudades) {
			System.out.println(ciudad);
		}
		
		System.out.println("\n\nPAISES.GETALL");
		PaisDAOImp daoPais=PaisDAOImp.getInstance();
		ArrayList<Pais> paises=daoPais.getAll();
		for (Pais pais : paises) {
			System.out.println(pais);
		}
		
		
		System.out.println("\n\nCONTINENTES.GETALL");
		ContinenteDAOImp daoContinente=ContinenteDAOImp.getInstance();
		ArrayList<Continente> continentes=daoContinente.getAll();
		for (Continente continente : continentes) {
			System.out.println(continente);
		}

	}

}
