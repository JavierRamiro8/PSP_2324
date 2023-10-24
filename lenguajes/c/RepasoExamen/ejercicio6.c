#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char const *argv[]) {

    int numero1 = atoi(argv[1]);
    char simbolo = argv[2][0];
    int numero2 = atoi(argv[3]);
    int resultado = 0;
    int accion[2];

    pid_t hijo = fork();

    if (hijo == 0) {
                close(accion[0]);  // Cerramos el descriptor de lectura en el proceso padre
        write(accion[1], &numero1, sizeof(numero1));
        write(accion[1], &numero2, sizeof(numero2));
        write(accion[1], &simbolo, sizeof(simbolo));
        close(accion[1]);  // Cerramos el descriptor de escritura en el proceso padre
        wait(NULL);
        }else{
        close(accion[1]);  // Cerramos el descriptor de escritura en el proceso hijo
        read(accion[0], &numero1, sizeof(numero1));
        read(accion[0], &numero2, sizeof(numero2));
        read(accion[0], &simbolo, sizeof(simbolo));

        switch (simbolo) {
            case '+':
                resultado = numero1 + numero2;
                break;
            case '-':
                resultado = numero1 - numero2;
                break;
            default:
            printf("No ha pasado nada");
                break;
        }
        read(accion[0], &resultado, sizeof(resultado));
        close(accion[0]);
        exit(0);
    }
        printf("%d %c %d es igual a %d\n", numero1, simbolo, numero2, resultado);

    return 0;
}
