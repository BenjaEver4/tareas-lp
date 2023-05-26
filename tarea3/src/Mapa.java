import java.util.Scanner;
public class Mapa{
    private int profundidad;   
    NodoInicial nodo_inicial;    
    Nodo nodo_actual;     

    public Mapa(int profundidad, NodoInicial nodo_inicial, Nodo nodo_actual){ //Constructor de Mapa
        this.profundidad=profundidad;
        this.nodo_actual=nodo_actual;
    }
    public void setProfundidad(int profundidad){ 
        this.profundidad=profundidad;
    }
    public int getProfundidad(){
        return profundidad;
    }
    public void verMapa(){ //Funcion para saber en que nivel del juego se está.
        System.out.print("NIVEL ACTUAL");
        System.out.print("\n");
        for (int i=0;i<nodo_actual.siguentes_nodos.size();i++){
            System.out.print("   nodo "+"("+nodo_actual.getId()+") "+"---->"+ "nodo ("+nodo_actual.siguentes_nodos.get(i).getId()+")"+nodo_actual.siguentes_nodos.get(i)+"\n");

        }

    }
    public void setNodoActual(Nodo nodo_actual){
        this.nodo_actual=nodo_actual;
    }
    public void avanzar(Jugador jugador_1){ //Funcion para dirigrise hacia el siguente nodo. 
        System.out.println("Donde deseas avanzar:");
        for(int i=0;i<nodo_actual.siguentes_nodos.size();i++){
            System.out.println(nodo_actual.siguentes_nodos.get(i).getId()+" :"+nodo_actual.siguentes_nodos.get(i));
        }
        Scanner entradita =new Scanner(System.in);  
        boolean t=true;
        while(t==true){ //Si el usuario ingresa un dato int que no pertenece a uno de los id´s de los nodos a los que se puede llegar, el proceso se repetirá. 
            int numsig = entradita.nextInt(); //Se le pide al usuario un dato int, que simboliza a cual nodo se quiere dirigir.
            for(int i=0;i<nodo_actual.siguentes_nodos.size();i++){//se recorre la lista de nodos a los que se puede dirigir
                if(nodo_actual.siguentes_nodos.get(i).getId()==numsig){
                    setNodoActual(nodo_actual.siguentes_nodos.get(i)); //El nodo elegido pasa a ser el nodo_actual.
                    nodo_actual.interactuar(jugador_1);
                    t=false;
                }
            }
        }

    }
}