#include <stdio.h>
#include <string.h>

#define LONGITUDARRAY 5

int main() {
    double arrayNotas[LONGITUDARRAY];
    double sumaNumeros = 0.0;
    double nota = 0.0;
    double notaMasAlta = 0.0;
    double notaMasBaja = 10.0; 

    for (int i = 0; i < LONGITUDARRAY; i++) {
        printf("Introduce las 5 notas del curso: ");
        scanf("%lf", &arrayNotas[i]);
        while (arrayNotas[i] <= 0 || arrayNotas[i] > 10) { 
            printf("Valor no válido, introduce una nota entre 0 y 10: ");
            scanf("%lf", &arrayNotas[i]);
        }
    }

    for (int i = 0; i < LONGITUDARRAY; i++) {
        if (arrayNotas[i] >= 5 ? printf("Nota introducida: %.2lf Aprobado\n", arrayNotas[i]) : printf("Nota introducida: %.2lf Suspenso\n", arrayNotas[i]));
        
        sumaNumeros += arrayNotas[i];
        nota = arrayNotas[i];

        if (nota > notaMasAlta) {
            notaMasAlta = nota;
        }
        if (nota < notaMasBaja) {
            notaMasBaja = nota;
        }
    }

    double media = sumaNumeros / LONGITUDARRAY;
    printf("Nota media: %.2lf\n", media);
    printf("Nota Mas alta: %.2lf\n", notaMasAlta);
    printf("Nota Mas baja: %.2lf\n", notaMasBaja);
    return 0;
}
