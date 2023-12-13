#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#define CERO 0
#define UNO 1
#define DOS 2
#define TRES 3

int main(int argc, char const *argv[])
{
    char simbolo = argv[UNO][CERO];
    int numero1 = atoi(argv[DOS]);
    int numero2 = atoi(argv[TRES]);
    pid_t hijo1;
    hijo1 = fork();
    int arrayNumerosArg[DOS];
    arrayNumerosArg[CERO] = numero1;
    arrayNumerosArg[UNO] = numero2;
    int envioNumerosArray[DOS];
    int envioSimbolo[DOS];
    pipe(envioNumerosArray);
    pipe(envioSimbolo);
    write(envioSimbolo[1], &simbolo, sizeof(simbolo));
    write(envioNumerosArray[1], arrayNumerosArg, sizeof(arrayNumerosArg));

    if (hijo1 == CERO)
    {
        int arrayNumerosRecibido[DOS];
        char simboloRecibido;
        close(envioNumerosArray[1]);
        close(envioSimbolo[1]);
        read(envioNumerosArray[0], arrayNumerosRecibido, sizeof(arrayNumerosRecibido));
        read(envioSimbolo[0], &simboloRecibido, sizeof(simboloRecibido));
        int resultado = 0;
        if (simboloRecibido=='+')
        {
            resultado = arrayNumerosRecibido[CERO] + arrayNumerosRecibido[UNO];
        }
        if (simboloRecibido=='-')
        {
            resultado = arrayNumerosRecibido[CERO] - arrayNumerosRecibido[UNO];
        }
        printf("%d\n", resultado);
    }
    return 0;
}
