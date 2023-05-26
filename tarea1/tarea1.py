import numpy as np
import re
from PIL import Image # pip install Pillow
def MatrizAImagen(matriz, filename='pixelart.PNG', factor=10):
    '''
    Convierte una matriz de valores RGB en una imagen y la guarda como un archivo png.
    Las imagenes son escaladas por un factor ya que con los ejemplos se producirian imagenes muy pequeñas.
        Parametros:
                matriz (lista de lista de tuplas de enteros): Matriz que representa la imagen en rgb.
                filename (str): Nombre del archivo en que se guardara la imagen.
                factor (int): Factor por el cual se escala el tamaño de las imagenes.
    '''
    matriz = np.array(matriz, dtype=np.uint8)
    np.swapaxes(matriz, 0, -1)

    N = np.shape(matriz)[0]

    img = Image.fromarray(matriz, 'RGB')
    img = img.resize((N*10, N*10), Image.Resampling.BOX)
    img.save(filename)
    print("se ha creado el archivo PNG :",filename)


def PalabraToRGB(frase):
    ''' CAMBIA TODOS LOS COLORES A SU RGB CORRESPONDIENTE (tupla de 3 valores entre 0 y 255 en Python).'''
    a = re.sub("Blanco", "(255,255,255)", frase)
    if a:
        stra="".join(a)
        frase=stra
        
    b = re.sub("Negro", "(0,0,0)", frase)
    if b:
        strb="".join(b)
        frase=strb
   
    c = re.sub("Rojo", "(255,0,0)", frase)
    if c:
        strc="".join(c)
        frase=strc
    d = re.sub("Verde", "(0,255,0)", frase)
    if d:
        strd="".join(d)
        frase=strd
    e = re.sub("Azul", "(0,0,255)", frase)
    if e:
        stre="".join(e)
        frase=stre

  
    return frase

def ListaStrToInt(y):
    '''AQUI SE EXTRAE EL COLOR PARA CREAR LA MATRIZ, EL COLOR VIENE ALMACENADO EN UNA LISTA DE NUMEROS EN STR LA CUAL PASARÁ A INT, POSTERIORMENTE LAS LISTAS CREADAS PASARAN A TUPLA '''

    num=0
    for elem in y:
        y[num]=int(elem)
        num+=1

    return y

def matrizInicial(y,ancho):
    '''AQUI SE CREA LA MATRIZ DE ANCHO NxN DE 1 COLOR''' 
    matriz=[]
    for fila in range(ancho):
        l=[]
        for col in range(ancho):
            l.append(y)
        matriz.append(l)

    return matriz
        


def AnalizarLinea(linea,posicion,der,izq,ancho,M,archivo):
    '''PARA SEGUIR UNA SECUENCIA EN EL PROGRAMA SE REALIZA LA REESCRITURA DEL ARCHIVO Y SE VAN ENCONTRANDO LAS EXPRESIONES
        COMO AVANZAR-DERECHA-REPETIR-PINTAR ...etc
        LA POSICION SIGNIFICA UN CURSOS EN LA MATRIZ QUE NOS AYUDARA A DESPLAZARNOS EN ELLA Y PODER PINTARLA CON FACILIDAD
        LA POSICION DEPENDE DE LA DERECHA(GIRO 90° HORARIO) E IZQUIERDA (GIRO ANTIHORARIO 90°)
        LA POSICION PARTE EN [0,0] MIRANDO HACIA [0,1]
        '''

    expresion=""
    repexp=""
    frase=""
    t=False
    x=0
    flag=False
    for a in linea:
        expresion+=a
        Izq=re.findall("Izquierda",expresion)
        Der=re.findall("Derecha",expresion)
        Avance=re.findall("Avanzar [1-9][0-9]*|Avanzar \w|Avanzar\n",expresion)
        Pintar=re.findall("Pintar \([0-9]+,[0-9]+,[0-9]+\)|Pintar RGB\([0-9]+,[0-9]+,[0-9]+\)",expresion)
        Repetir=re.findall("Repetir [0-9]+ veces {",expresion)
        Abrir=re.findall("{ ",repexp)
        CierreRep=re.findall("}|\n",a)
        Cerrar=re.findall("}",a)

        if Repetir:
            expresion=""
            t=True

        if t==True:
            expresion=""
            repexp+=a
            if Cerrar:
                flag=True
                
            if CierreRep:
                linearep=Repeticion(frase,linea,archivo,flag)
                M,posicion,der,izq =AnalizarLinea(linearep,posicion,der,izq,ancho,M,archivo)
                flag=False
                repexp=""
                expresion=""
                t=False
                    
            if Abrir and t==True :
                finalizarFrase=re.findall("}|\n",a)
                if finalizarFrase:
                   frase=frase
                else:
                    frase+=a      
            
        if Izq:
            izq+=1
            expresion=""
            if izq==4:
                izq=0
        if Der:
            der+=1
            expresion=""
            if der==4:
                der=0
            
        if Avance:
            AvanceMasNum=re.findall("Avanzar [0-9]+",expresion)
            AvanceNum="".join(AvanceMasNum)
            if AvanceMasNum:
                Num=re.findall("[0-9]+",AvanceNum)
                strNum="".join(Num)
                intNum=int(strNum)
                
                expresion=""
            else:
                intNum=1
                expresion=""
                expresion+=Avance[0][-1]
                
            if der==izq:
              posicion[1]+=intNum

            if der==izq+1 or izq==der+3:
              posicion[0]+=intNum
            if der==izq+2 or izq==der+2:
              posicion[1]+=-intNum
            if der==izq+3 or izq==der+1:
              posicion[0]+=-intNum
            FuerdaDeRango=False
            FueraDeRango=RangoPosicion(posicion,ancho)
            if FueraDeRango==True:
                break

        if Pintar:
            f=0
            c=0
            Color=re.findall("\([0-9]*,[0-9]*,[0-9]*\)",expresion)
            strColor="".join(Color)
            strTupla=re.findall("[0-9]*,[0-9]*,[0-9]*",strColor)
            strTupla="".join(strTupla)
            strTupla+=","
            numsTupla=""
            listaToTupla=[]
            for a in strTupla:
                Coma=re.findall(",",a)
                if Coma:
                    numsTupla=int(numsTupla)
                    listaToTupla.append(numsTupla)
                    numsTupla=""
                else:
                    numsTupla+=a
            M[posicion[0]][posicion[1]]=tuple(listaToTupla)
            
            expresion=""
        x+=1
    return M,posicion,der,izq


def RangoPosicion(posicion, ancho):
    '''AQUI SE ANALIZA CUANDO LA POSICION DE LA MATRIZ SALE DEL RANGO ''' 
    if posicion[0]>=0 and posicion[1]>=0 and posicion[0]<ancho and posicion[1]<ancho:
        
        return False
    else:
        return True

    
def Repeticion(frase,linea,archivo,flag):
    '''TODO LO QUE ESTÁ EN EL PARENTESIS DEL REPETIR SE REPITE X VECES, Y SE ENVIA A LA FUNCION A AnalizarLinea'''
    frase+=" "   
    t=flag  #linea con  rep x vec {}  necesita True
    
    if flag == False :
        for l in archivo:

            for a in l:
                corchete=re.findall("}",a)
                if corchete:
                    t=True
                    break
                frase+=a
            if t==True:
                break
    
    Repetir=re.findall("Repetir [0-9]* veces {",linea)
    strRepetir="".join(Repetir)
    numreps=re.findall("\d*",strRepetir)
    strNumreps="".join(numreps)
    Nr=int(strNumreps)
    c2=0
    frase=PalabraToRGB(frase)
    fraserep=""
    frase = re.sub("Avanzar  |Avanzar *\n", "Avanzar 1", frase)
    while c2<Nr :
        fraserep+=frase+" "
        
        c2+=1
    return fraserep



'''EN ESTA PRIMERA APERTURA DEL ARCHIVO.txt ANALIZAREMOS SIN CONTIENE ERRORES DE SINTAXIS'''
NombreArchivo=input("Ingrese nombre del archivo.txt :")
with open(NombreArchivo,"r") as archivo :
    c=0
    error=False
    ErrorFrase=""
    for linea in archivo :
        PrimeraLinea=re.findall("Ancho [1-9][0-9]*\n",linea)
        SegundaLinea=re.findall("(Color de fondo )+(Negro|Blanco|Rojo|Azul|Verde|RGB\(([0-1]+[0-9]*[0-9]*|[0-2]+[0-5]*[0-5]*),([0-1]+[0-9]*[0-9]*|[0-2]+[0-5]*[0-5]*),([0-1]+[0-9]*[0-9]*|[0-2]+[0-5]*[0-5]*)\))",linea)
        TerceraLinea=re.findall("\n",linea)
        l=[]

        if c==0 :
            l.append(linea)
            if not PrimeraLinea==l:
                ErrorFrase+=str(c+1)+" "+linea
                error=True

        if c==1 :
            if not SegundaLinea:
                ErrorFrase+=str(c+1)+" "+linea
                error=True 

        if c==2:
            l.append(linea)
            if not TerceraLinea==l:
                ErrorFrase+=str(c+1)+" "+linea
                error=True
                            
        if c>2:
            Derecha=re.findall("Derecha [0-9]+|Derecha[0-9]+|Derecha[a-zA-Z]+|[a-zA-Z]+Derecha",linea)
            Izquierda=re.findall("Izquierda [0-9]+|Izquierda[0-9]+|Izquierda[a-zA-Z]+|[a-zA-Z]+Izquierda",linea)
            Avanzar=re.findall("Avanzar[0-9]+|Avanzar[a-zA-Z]+",linea)
            RepetirSolo=re.findall("Repetir",linea)
            Pintar=re.findall("Pintar",linea)
            PintarColor=re.findall("(Pintar )+(Negro|Blanco|Rojo|Azul|Verde|RGB\([0-2]+[0-5]*[0-5]*,[0-2]+[0-5]*[0-5]*,[0-2]+[0-5]*[0-5]*\))",linea)
            if Avanzar:
                ErrorFrase+=str(c+1)+" "+linea
                error=True
                
            if Derecha:
                ErrorFrase+=str(c+1)+" "+linea
                error=True
                
            if Izquierda:
                ErrorFrase+=str(c+1)+" "+linea
                error=True
                
            if RepetirSolo:
                RepetirEnLinea=re.findall("Repetir [0-9]+ veces {",linea)
                if not RepetirEnLinea:
                    ErrorFrase+=str(c+1)+" "+linea
                    error=True

                    
            if Pintar:
                if not PintarColor:
                    ErrorFrase+=str(c+1)+" "+linea
                    error=True  
                               
        c+=1
    
    archivo.close()
    
'''SE HAN ENCONTRADO ERRORES DE SINTAXIS'''
if error==True:
        archivoErrores=open("errores.txt","w")
        ListaErroresRepetidos=re.split("\n",ErrorFrase)
        print(ListaErroresRepetidos)
        ListaErrores=[]
        ListaErrores.append(ListaErroresRepetidos[0])
        archivoErrores.write(ListaErroresRepetidos[0]+"\n")
        k=0
        Rep=False
        for a in ListaErroresRepetidos:
            for b in ListaErrores:
               if k!=0 and a==b:
                   Rep=True
            if k!=0 and Rep==False:
                ListaErrores.append(ListaErroresRepetidos[k])
                archivoErrores.write(ListaErroresRepetidos[k]+"\n")
            k+=1
        print(ListaErrores)
            
                
        archivoErrores.close()
        print("se ha creado el archivo : errores.txt")

'''NO SE HAN ENCONTRADO ERRORES DE SINTAXIS'''        
if error==False:
    archivoErrores=open("errores.txt","w")
    NoErroresFrase="No hay errores!"
    archivoErrores.write(NoErroresFrase)
    archivoErrores.close()
    print("se ha creado el archivo : errores.txt")


''' COMIENZA LA EJECUCION DEL PROGRAMA, LUEGO DE COMRPOBAR QUE NO CONTIENE ERRORES'''

with open(NombreArchivo,"r") as archivo :
    if error==False:
        c=0
        posicion=[0,0]
        der=0
        izq=0
        for linea in archivo :
            linea=PalabraToRGB(linea)

            if(c==0):
                x = re.findall("[1-9][0-9]*", linea)
                ancho="".join(x)
                ancho=int(ancho)
                
            if (c==1):
                y = re.findall("\d+", linea)
                y=ListaStrToInt(y)
                M=matrizInicial(y,ancho)
                posicion=[0,0]


            if(c>2):
                M,posicion,der,izq=AnalizarLinea(linea,posicion,der,izq,ancho,M,archivo)
                FueraDeRango=RangoPosicion(posicion,ancho)
                if FueraDeRango==True:
                    print("ERROR : se accedio a la posicion :",posicion)
                    break


            c+=1

    archivo.close()

''' AQUI SE HACE UN CAMBIO EN LA MATRIZ, LOS COLORES RGB ESTÁN EN LISTAS, Y SE PASARÁN A TUPLAS , PARA PODER OCUPAR LA FUNCION MatrizAImagen'''

if error==False:
    fila=0
    for a in M:
        columna=0
        for b in a:
            M[fila][columna]=tuple(b)
            columna+=1
        fila+=1
    MatrizAImagen(M)
