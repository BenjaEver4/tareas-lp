import java.util.Random;
public class Personaje{
    private String nombre;
    private int dinero;
    public int hp_actual;
    public int hp_total;
    public int danio;
    public int defensa;
    public Personaje(String nombre,int dinero,int hp_actual,int hp_total,int danio, int defensa){ //Constructor de Personaje
        this.nombre=nombre;
        this.dinero=dinero;
        this.hp_actual=hp_actual;
        this.hp_total=hp_total;
        this.danio=danio;
        this.defensa=defensa;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setDinero(int dinero){
        this.dinero=dinero;
    }
    public void setHpActual(int hp_actual){
        this.hp_actual=hp_actual;
    }
    public void setHpTotal(int hp_total){
        this.hp_total=hp_total;
    }
    public void setDanio(int danio){
        this.danio=danio;
    }
    public void setDefensa(int defensa){
        this.defensa=defensa;
    }
    public String getNombre(){
        return nombre;
    }
    public int getDinero(){
        return dinero;
    }
    public int getHpActual(){
        return hp_actual;
    }
    public int getHpTotal(){
        return hp_total;
    }
    public int getDanio(){
        return danio;
    }
    public int getDefensa(){
        return defensa;
    }

    public void combate(Personaje p){ //Funcion  Combate, aqui jugador combate contra uno de los personajes rivales.
        Random r = new Random();
        int randomNum = r.nextInt(2); //Con un random int se decide quien partirá atacando.
        boolean terminarCombate=true;
        System.out.println("\n");
        System.out.println("COMIENZA EL COMBATE ");
        int i=0;
        if(randomNum==0){
            //EMPIEZA JUGADOR A ATACAR
            while(terminarCombate==true){
                i++;
                System.out.println("ronda:"+i);
                if(-getDanio()+p.getDefensa()>=0){ //Aqui se calcula el daño que efectua Jugador a Personaje, cuando no lo ha derribado todavia, se actualiza la vida de personaje.
                    p.setHpActual(p.getHpActual());
                    System.out.println(""+getNombre()+" ataca a "+p.getNombre()+" Y lo deja con "+p.getHpActual()+" de vida !");
                }
                else{
                    p.setHpActual(p.getHpActual()-getDanio()+p.getDefensa());//Aqui se calcula el daño que efectua Jugador a Personaje, se actualiza la vida de personaje a 0 .
                    System.out.println(""+getNombre()+" ataca a "+p.getNombre()+" Y lo deja con "+p.getHpActual()+" de vida !");
                }
                if(p.getHpActual()<=0){//Verificando si jugador ha ganado el combate.
                    System.out.println("TERMINO GANADOR JUGADOR  "+getNombre());
                    terminarCombate=false;
                    break;
                }
                if (-p.getDanio()+getDefensa()>=0){//Aqui se calcula el daño que efectua Personaje a Jugador, cuando no lo ha derribado todavia, se actualiza la vida de Jugador.
                    p.setHpActual(p.getHpActual());
                    setHpActual(getHpActual());
                    System.out.println(""+p.getNombre()+" ataca a "+getNombre()+" Y lo deja con "+getHpActual()+" de vida !");
                }
                else{
                    setHpActual(getHpActual()-p.getDanio()+getDefensa()); //Aqui se calcula el daño que efectua Personaje a Jugador, se actualiza la vida de Jugador a 0 .
                    System.out.println(""+p.getNombre()+" ataca a "+getNombre()+" Y lo deja con "+getHpActual()+" de vida !");
                }
                if(getHpActual()<=0){//Verificando si Personaje ha ganado el combate.
                    System.out.println("TERMINO GANADOR PERSONAJE: "+p.getNombre());
                    terminarCombate=false;
                    break;
                }

            }
        }
        else{
            //EMPIEZA PERSONAJE A ATACAR
            while(terminarCombate=true){
                i++;
                System.out.println("ronda:"+i);
                if (-p.getDanio()+getDefensa()>=0){//Aqui se calcula el daño que efectua Personaje a Jugador, cuando no lo ha derribado todavia, se actualiza la vida de Jugador.
                    setHpActual(getHpActual());
                    System.out.println(""+p.getNombre()+" ataca a "+getNombre()+" Y lo deja con "+getHpActual()+" de vida !");
                }
                else{
                    setHpActual(getHpActual()-p.getDanio()+getDefensa());//Aqui se calcula el daño que efectua Personaje a Jugador, se actualiza la vida de Jugador a 0 .
                    System.out.println(""+p.getNombre()+" ataca a "+getNombre()+" Y lo deja con "+getHpActual()+" de vida !");
                }
                if(getHpActual()<=0){ 
                    System.out.println("TERMINO GANADOR PERSONAJE : "+p.getNombre());//Verificando si Personaje ha ganado el combate.
                    terminarCombate=false;
                    break;
                }
                if(-getDanio()+p.getDefensa()>=0){//Aqui se calcula el daño que efectua Jugador a Personaje, cuando no lo ha derribado todavia, se actualiza la vida de personaje.
                    p.setHpActual(p.getHpActual());
                    System.out.println(""+getNombre()+" ataca a "+p.getNombre()+" Y lo deja con "+p.getHpActual()+" de vida !");
                }
                else{
                    p.setHpActual(p.getHpActual()-getDanio()+p.getDefensa()); ////Aqui se calcula el daño que efectua Jugador a Personaje, se actualiza la vida de personaje a 0 .
                    System.out.println(""+getNombre()+" ataca a "+p.getNombre()+" Y lo deja con "+p.getHpActual()+" de vida !");
                }
                if(p.getHpActual()<=0){
                    System.out.println("TERMINO GANADOR JUGADOR: "+getNombre());////Verificando si Personaje ha ganado el combate.
                    terminarCombate=false;
                    break;
                } 

            }

        }
    }

}