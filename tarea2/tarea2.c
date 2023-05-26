#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>
#define  BUFFER_SIZE 4096

typedef struct 
{
    char enunciado[128];
    int n_alternativas;
    char ** alternativas;
    int alternativa_correcta;
}tEnunciadoAlternativa;
typedef struct
{
    char enunciado[128];
    int n_alternativas;
    char **alternativas;
    int n_correctas;
    int *alternativa_correcta;
}tEnunciadoAlternativaMultiple;

typedef struct
{
    char enunciado[128];
    bool respuesta;
}tEnunciadoVerdaderoFalso;

typedef struct
{
    int n_textos ;
    char **textos;
    char **respuestas ;
} tEnunciadoCompletar;



typedef struct{
    char tipo[64];
    void* enunciado;
    void* respuesta;
    bool (*revisar)(void*,void*);
}tPregunta;

typedef struct{
    int n_pregutnas;
    tPregunta* preguntas;
}tCertamen;

tCertamen* crearCertamen (int n_preguntas);
tPregunta* crearPregunta(tCertamen* certamen,char *tipo,void *enunciado,bool revisar(void *,void *));
void asignarPregunta(tCertamen *certamen,int n_pregunta,tPregunta *pregunta);
tPregunta leerPregunta(tCertamen *certamen, int n_pregunta);
int n_CorrectasCertamen (tCertamen certamen );
int largoCertamen(tCertamen certamen);
bool revisarAlternativaSimple(tPregunta pregunta);
bool revisarAlternativaMultiple(tPregunta pregunta);
bool revisarVerdaderoFalso(tPregunta pregunta);
bool revisarCompletar(tPregunta pregunta);



int main() {
    tEnunciadoAlternativa AS[100];
    tEnunciadoAlternativaMultiple AM[100];
    tEnunciadoVerdaderoFalso VF[100];
    tEnunciadoCompletar TC[100];
    tPregunta tP[100];
    //strcpy(VF[0].enunciado,"hola");
    //printf("ACA %s",VF[0].enunciado);
    FILE *fp = fopen("certamenTDA.txt","r");
    if ( fp == NULL ){ /* error */ }
    
    char buf[128];char buffer[BUFFER_SIZE];char***strings;int*correctas;char enunc[128];bool ValorVoF;char *ret;
    strings = malloc(128*sizeof(char**));int n_preguntas;
    //strings[0]=malloc(128*sizeof(char*));
    //strings[0][0]=malloc(128*sizeof(char));
    //strings[0][0]="hola";
    //strings[1][0]=malloc(128*sizeof(char));
    //strings[1][0]="adeus";
    int i=1,i2=1,i3=1,i4=1;int c=0;int c2=0;int n_correc,n_alt;int lenght=0;int nTextCompl;
    int k=0,kAS=0,kAM=0,kVF=0,kC=0;int r=0;int z=0;
    char alts[3];
    bool t1,t2,t3,t4,pase=false;
    while (fgets(buf, 128, fp) != NULL){
        printf("%s\n", buf);
        if(r==0){
            int n_preguntas=atoi(buf);
            r++;
        }

        if (t1==true){ // aca se guardan los datos en tEnunciadoAlternativa AS
            if (i==1){
                strcpy(enunc,buf);  //enunciado
            }
            if (i==2){
                n_alt=atoi(buf); //numero de alternativas
            }
            if (i==3){
                if (c!=n_alt-1)i--; //arreglos de strings alternativas
                lenght=strlen(buf);
                buf[lenght]='\0';
                strings[k][c]=malloc(lenght * sizeof(char));
                strcpy(strings[k][c],buf);
                c++;
            }
            
            if (i==4){
                int n_correc=atoi(buf); //alternativa correcta
                strcpy(AS[kAS].enunciado,enunc);
                AS[kAS].n_alternativas=n_alt;  
                AS[kAS].alternativas=strings[k];
                AS[kAS].alternativa_correcta=n_correc;

                t1=false;
                kAS++;
                k++;

            }
            i++;

        }
        if (t2==true){// tEnunciadoVerdaderoFalso VF
            if(i2==1){
                strcpy(enunc,buf); //enunciado
            }
            if(i2==2){
                n_alt=atoi(buf);//numero de alternativas
            }
            if (i2==3){
                if (c!=n_alt-1)i2--; //arreglos de strings alternativas
                lenght=strlen(buf);
                buf[lenght-1]='\0';
                strings[k][c]=malloc(lenght * sizeof(char));
                strcpy(strings[k][c],buf);
                c++;
            }
            if (i2==4){
                for (int i=0; i<n_alt;i++){ 
                }
                n_correc=atoi(buf); //numero de alternativas correctas
                correctas = malloc(n_correc * sizeof(int));
                c=0;
            }
            if (i2==5){
                if (c!=n_correc-1)i2--;       
                correctas[c]=atoi(buf); //arreglos de int de numero de alternativas correctas
                c++;
                if (c==n_correc){
                    strcpy(AM[k].enunciado,enunc);
                    AM[kAM].n_alternativas=n_alt;
                    AM[kAM].alternativas=strings[k];
                    AM[kAM].alternativa_correcta=correctas;
                    AM[kAM].n_correctas=n_correc;
                    kAM++;
                    k++;
                    t2=false;
                }
            }
            i2++;
        }
        if(t3==true){// aca se guardan los datos en  tEnunciadoVerdaderoFalso VF
            if(i3==1){
                strcpy(enunc,buf);
                strcpy(VF[kVF].enunciado,enunc); //enunciado
            }
            if(i3==2){
                ret=strstr(buf, "V");
                if(ret){
                    ValorVoF=true;
                    VF[kVF].respuesta=ValorVoF; //respuesta tipo bool, Falsa o Verdadera
                }
                else{
                    ValorVoF=false;
                    VF[kVF].respuesta=ValorVoF;
                }
                kVF++;
                t3=false;
                
            }

            i3++;
        }
        if (t4==true){// aca se guardan los datos en  tEnunciadoCompletar TC
            if(i4==1){
                n_alt=atoi(buf); //numero de alternativas
            }
            if(i4==2){
                if (c!=n_alt-1){ //arreglo de strings del texto
                    i4--;
                }
                lenght=strlen(buf);
                buf[lenght-1]='\0';
                strings[k][c] = malloc(lenght * sizeof(char));
                strcpy(strings[k][c],buf);
                c++;
                if(c==n_alt){
                    TC[kC].n_textos=n_alt; 
                    TC[kC].textos=strings[k];
                    k++;c2=0;
                    strings[k]=malloc(128*sizeof(char*));

                }
            }
            if(i4==3){
                if (c2!=n_alt-2)i4--;//arreglo de strings de las frases a completar del texto
                lenght=strlen(buf);
                buf[lenght-1]='\0';
                strings[k][c2] = malloc(lenght * sizeof(char));
                strcpy(strings[k][c2],buf);
                if(c2==n_alt-2){
                    TC[kC].respuestas=strings[k];
                    t4=false;k++;
                    kC++;

                }
                c2++;

            }

            i4++;
        }
        
        if (strstr(buf,"Simple")){
            t1=true;c=0;c2=0;i=1;strings[k]=malloc(128*sizeof(char*));strcpy(tP[z].tipo,buf);z++;
        }
        if (strstr(buf,"Multiple")){
            t2=true;i2=1;c=0;c2=0;strings[k]=malloc(128*sizeof(char*));strcpy(tP[z].tipo,buf);z++;
        }
        if (strstr(buf,"VerdaderoFalso")){
            t3=true;i3=1;c=0;c2=0;strcpy(tP[z].tipo,buf);z++;
        }
        if (strstr(buf,"Completar")){
            t4=true;i4=1;c=0;c2=0;strings[k]=malloc(128*sizeof(char*));strcpy(tP[z].tipo,buf);z++;
        }


    }
  
   
    int o=0;
    for (int i=0;i<8;i++){ //tPregunta tP
        if(AS[i].enunciado){
            tP[o].enunciado=&AS[i];
            o++;   
        }
        if(AM[i].enunciado){
            tP[o].enunciado=&AM[i];
            o++;   
        }
        if(VF[i].enunciado){
            tP[o].enunciado=&VF[i];
            o++;   
        }
        if(TC[i].n_textos){
            tP[o].enunciado=&TC[i];
            o++;   
        }

    }


    
    //liberando memoria
    free(correctas);
    for (int i=0;i<128;i++){
        free(strings[i]);
    }
    return 0;
}

