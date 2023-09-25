#include <stdio.h>

int main(void) {
    int numero = 0;
    int factorial = 1; // Inicializar el factorial como 1

    printf("Elige un numero NO negativo: \n");
    scanf("%d", &numero);

    while (numero < 0) {
        printf("ERROR: el numero es negativo, elige otro: ");
        scanf("%d", &numero);
    }

    for (int i = 1; i <= numero; i++) {
        factorial *= i; // Multiplicar por i en lugar de sumar
    }

    printf("El factorial de %d es: %d\n", numero, factorial);

    return 0;
}
