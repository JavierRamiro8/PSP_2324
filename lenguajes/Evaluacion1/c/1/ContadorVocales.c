#include <stdio.h>
#include <string.h>
#define BUFFER 1024

int main() {
    int contadorVocales = 0;
    char frase[BUFFER];
    printf("Introduce una frase y te contaré las vocales: ");
    fgets(frase, BUFFER, stdin);

    for (int i = 0; i < strlen(frase); i++) { 
        char caracter = frase[i];
        if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u' ||
            caracter == 'A' || caracter == 'E' || caracter == 'I' || caracter == 'O' || caracter == 'U') {
            contadorVocales++;
        }
    }

    printf("Estas son las vocales registradas: %d\n", contadorVocales);

    return 0;
}
