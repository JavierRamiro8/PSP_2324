#include <stdio.h>
#include <stdlib.h>

int main() {
    // Obtén dos números aleatorios, por ejemplo, utilizando rand()
    int numero1 = rand() % 10; // Número aleatorio entre 0 y 9
    int numero2 = rand() % 10; // Número aleatorio entre 0 y 9

    // Une los dos números
    int resultado = numero1 * 10 + numero2;

    // Imprime los números y el resultado
    printf("Numero 1: %d\n", numero1);
    printf("Numero 2: %d\n", numero2);
    printf("Resultado: %d\n", resultado);

    return 0;
}
