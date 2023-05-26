public class Item{
    private int precio;
    private int recuperar_hp;
    private int aumentar_hp_total;
    private int aumentar_danio;
    private int aumentar_defensa;
    public Item(int precio,int recuperar_hp,int aumentar_hp_total,int aumentar_danio, int aumentar_defensa){ //Constructor de Items.
        this.precio=precio;
        this.recuperar_hp=recuperar_hp;
        this.aumentar_hp_total=aumentar_hp_total;
        this.aumentar_danio=aumentar_danio;
        this.aumentar_defensa=aumentar_defensa;
    } 
    public void aplicar(Jugador j){ // funcion que aplica el Item obtenido a las estadisticas de jugador_1.
        if (getRecuperar_Hp()+j.getHpActual()>=j.getHpTotal()){ //Esta condicion es para que el jugador no sobrepase su vida actual con su Hp Total 
            j.setHpActual(j.getHpTotal());
            
        }
        else{
            j.setHpActual(j.getHpActual()+getRecuperar_Hp()); // Si no se cumple la condicion, se regenera vida sin problemas.
        }
        j.setHpTotal(j.getHpTotal()+getAumentar_HpTotal());//se le agrega el stat de HpTotal al jugador
        j.setDanio(j.getDanio()+getAumentar_Danio());//se le agrega el stat de daño al jugador
        j.setDefensa(j.getDefensa()+getAumentar_Defensa());//se le agrega el stat de defensa al jugador
    }   
    @Override
    public String toString(){
        return "precio: "+precio+" , recuperar_hp: "+recuperar_hp+" , aumentar_hp_total :"+ aumentar_hp_total+ " ,aumentar_danio: "+aumentar_danio+ " ,aumentar_defensa: "+aumentar_defensa;
    }
    public int getPrecio(){
        return precio;
    }
    public int getRecuperar_Hp(){
        return recuperar_hp;
    }
    public int getAumentar_HpTotal(){
        return aumentar_hp_total;
    }
    public int getAumentar_Danio(){
        return aumentar_danio;
    }
    public int getAumentar_Defensa(){
        return aumentar_defensa;
    }

}