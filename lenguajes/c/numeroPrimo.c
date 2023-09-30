#include <stdio.h>
#include <math.h>

int main(void) {
    int numero = 0;
    int esPrimo = 1;
    printf("Introduce un numero\n");
    scanf("%d", &numero);

    if (numero <= 1) {
        esPrimo = 0;
    } else {
        for (int i = 2; i <= numero / 2; i++) {
            if (numero % i == 0) {
                esPrimo = 0;
            }
        }
    }

    if (esPrimo) {
        printf("%d es un numero primo \n", numero);
    } else {
        printf("%d no es un numero primo \n", numero);
    }
    return 0;
}