import java.util.Scanner;
public class NodoEvento extends Nodo{
    private String descripcion;
    private String alternativa1;
    private String alternativa2;
    Item resultado1;
    Item resultado2;
    public NodoEvento(int id, String descripcion,String alternativa1, String alternativa2, Item resultado1 ,Item resultado2 ){ //Constructor de NodoEvento
        super(id);  
        this.descripcion=descripcion;
        this.alternativa1=alternativa1;
        this.alternativa2=alternativa2;
        this.resultado1=resultado1;
        this.resultado2=resultado2;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public void setAlternativa1(String alternativa1){
        this.alternativa1=alternativa1;
    }
    public void setAlternativa2(String alternativa2){
        this.alternativa2=alternativa2;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getAlternativa1(){
        return alternativa1;
    }
    public String getAlternativa2(){
        return alternativa2;
    }
    public void interactuar(Jugador jugador_1){  //En esta funcion el item regalado al jugador se le aplica a sus estadisticas.
        System.out.println(descripcion);
        System.out.println("Presiona 1 para recibir la Alternativa1 :"+alternativa1);
        System.out.println("Presiona 2 para recibir la Alternativa2 :"+alternativa2);
        Scanner numalternativas =new Scanner(System.in);
        int numalt = numalternativas.nextInt(); //decision si el jugador quiere tener el item 1 o el item 2.
        resultado1.aplicar(jugador_1);
        if(numalt==1){
            resultado1.aplicar(jugador_1);
            jugador_1.items_aplicados.add(resultado1); 
        }
        if(numalt==2){
            resultado2.aplicar(jugador_1);
            jugador_1.items_aplicados.add(resultado2); 
        }
        jugador_1.verEstado(); //se revisan las stats del jugador_1 luego de haber sido aplicado el item.

    }
}