import java.util.ArrayList;
import java.util.Random;
public class NombresPJ{
    private ArrayList<String> nombresPersonajes = new ArrayList<String>();
/**
 * Descripcion a: Funcion que retorna el nombre de un personaje aleatorio y despúes lo elimina de la lista, para que no se repita.
 * parametros b: ArrayList<String> nombresPersonajes
 * return c: Retorna un nombre de ArrayList<String>.
*/
    public String getNombresPJ(){ 
        Random r = new Random();
        int aleatorio=r.nextInt(nombresPersonajes.size());
        for(int i=0;i<nombresPersonajes.size();i++){
            if (i==aleatorio){
                String nombrelisto=nombresPersonajes.get(i);
                nombresPersonajes.remove(i);
                return nombrelisto;
            }
        }
        return "";
    }
/**
 * Descripcion a: Esta funcion sirve para obtener el largo de la lista nombresPersonajes.
 * parametros b: ArrayList<String> nombresPersonajes
 * return c: Retorna el largo de la lista nombresPersonajes.
*/
    public int getLargoNombresPJ(){
        return nombresPersonajes.size();
    }


/**
 * Descripcion a: Añade 51 nombres a la Lista nombresPersonajes.
 * parametros b: No tiene parametros
 * return c: No retorna nada
*/
    public void nombresPersonajesLista(){ //Funcion que agrega 51 nombre de personajes a la Lista ArrayList<String> nombresPersonajes
        nombresPersonajes.add("Goku");
        nombresPersonajes.add("Naruto");
        nombresPersonajes.add("LUFFY");
        nombresPersonajes.add("Light Yagami");
        nombresPersonajes.add("Vegeta");
        nombresPersonajes.add("Mario");
        nombresPersonajes.add("Finn el Humano");
        nombresPersonajes.add("McGregor");
        nombresPersonajes.add("Messi");
        nombresPersonajes.add("Maradona");
        nombresPersonajes.add("Bowser");
        nombresPersonajes.add("Ronaldo");
        nombresPersonajes.add("Seya");
        nombresPersonajes.add("Saitama");
        nombresPersonajes.add("Freezer");
        nombresPersonajes.add("Cell");
        nombresPersonajes.add("Majin Buu");
        nombresPersonajes.add("Broly");
        nombresPersonajes.add("Sasuke");
        nombresPersonajes.add("Madara Uchiha");
        nombresPersonajes.add("Zoro Roronoa");
        nombresPersonajes.add("Eren Jaeger");
        nombresPersonajes.add("Rock Lesnar");
        nombresPersonajes.add("L Lawliet");
        nombresPersonajes.add("Near");
        nombresPersonajes.add("Myke Tyson");
        nombresPersonajes.add("Nate Diaz");
        nombresPersonajes.add("Israel Adesanya");
        nombresPersonajes.add("The Undertaker");
        nombresPersonajes.add("Triple H");
        nombresPersonajes.add("Randy Orton");
        nombresPersonajes.add("Dwayne Johnson");
        nombresPersonajes.add("Kakashi");
        nombresPersonajes.add("Nagato");
        nombresPersonajes.add("Jiraiya");
        nombresPersonajes.add("Obito");
        nombresPersonajes.add("Rock Lee");
        nombresPersonajes.add("Usopp");
        nombresPersonajes.add("Franky");
        nombresPersonajes.add("Sanji");
        nombresPersonajes.add("Zack Efron");
        nombresPersonajes.add("Micheal Phelps");
        nombresPersonajes.add("Micheal Jordan");
        nombresPersonajes.add("Shaquille O neal");
        nombresPersonajes.add("Harry Potter");
        nombresPersonajes.add("Voldemort");
        nombresPersonajes.add("Darth Vader");
        nombresPersonajes.add("Obi-Wan Kenobi");
        nombresPersonajes.add("Han Solo");
        nombresPersonajes.add("Chewbacca");
        nombresPersonajes.add("Pelé");
    }

}