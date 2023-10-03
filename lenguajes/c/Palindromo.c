#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define BUFFER 1024

int main() {
    char frase[BUFFER];
    int esPalindromo = 1;
    fprintf(stdout, "Introduce una frase: ");
    scanf(" %[^\n]", frase);

    int longitudFrase = strlen(frase);

    for (int i = 0, j = longitudFrase - 1; i < j; i++, j--) {
        while (isspace(frase[i] || frase[i]==";")) {
            i++;
        }
        while (isspace(frase[j])) {
            j--;
        }
        if (tolower(frase[i]) != tolower(frase[j])) {
            esPalindromo = 0;
            break;
        }
    }
    if (esPalindromo == 1) {
        printf("La frase \"%s\" es un palindromo.\n", frase);
    } else {
        printf("La frase \"%s\" no es un palindromo.\n", frase);
    }

    return 0;
}
