#include <stdio.h>
#include<string.h>
#define LONGITUDARRAY 5

int main()
{
    int arrayNotas[LONGITUDARRAY];
    int sumaNumeros=0;
    int nota=0;
    int notaMasAlta=0;
    int notaMasBaja=10;
    for (int i = 0; i < LONGITUDARRAY; i++)
    {
        printf("Introduce las 5 notas del curso: ");
        scanf("%d",&arrayNotas[i]);
        while(arrayNotas[i]<=0){
            printf("No has introducido valores y tampoco se permiten negativos, vuelve a introducirlo");
            scanf("%d",&arrayNotas[i]);
        }
    }
     for (int i = 0; i < LONGITUDARRAY; i++)
    {
        if(arrayNotas[i]>=5){
            printf("Estos son los datos introducidos: %d Aprobado\n",arrayNotas[i]);
            sumaNumeros+=arrayNotas[i];
            nota=arrayNotas[i];
        }else{
            printf("Estos son los datos introducidos: %d Suspenso\n",arrayNotas[i]);
            sumaNumeros+=arrayNotas[i];
            nota=arrayNotas[i];
        }
        if(nota>notaMasAlta){
            notaMasAlta=nota;
        }
        if(nota<notaMasBaja){
            notaMasBaja=nota;
        }
    }
    int media=sumaNumeros/LONGITUDARRAY;
        printf("Nota media: %d\n",media);
        printf("Nota Mas alta: %d\n",notaMasAlta);
        printf("Nota Mas baja: %d\n",notaMasBaja);

    
    return 0;
}
