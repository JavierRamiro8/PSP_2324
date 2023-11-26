#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#define NUMEROMAXGEN 20
#define NUMEROMAXRAND 100
#define DOS 2
#define CERO 0
#define UNO 1
int main(int argc, char const *argv[])
{
    pid_t hijoPar;
    pid_t hijoImpar;
    int numerosAleatorios[NUMEROMAXGEN];
    int envioHijoPar[DOS];
    int envioHijoImpar[DOS];
    pipe(envioHijoPar);
    pipe(envioHijoImpar);
    srand(time(NULL));
    for (int i = 0; i < NUMEROMAXGEN; i++)
    {
        numerosAleatorios[i] = rand() % NUMEROMAXRAND;
    }

    write(envioHijoPar[1], numerosAleatorios, sizeof(numerosAleatorios));
    write(envioHijoImpar[1], numerosAleatorios, sizeof(numerosAleatorios));

    hijoPar = fork();
    if (hijoPar == 0)
    {
        close(envioHijoPar[1]);
        int arrayNumerosRecibidos[NUMEROMAXGEN];
        int contador = 0;
        read(envioHijoPar[0], arrayNumerosRecibidos, sizeof(arrayNumerosRecibidos));
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {
            if (arrayNumerosRecibidos[i] % DOS == CERO)
            {
                printf("Soy el hijo 1, he recibido: %d\n", arrayNumerosRecibidos[i]);
                contador++;
            }
        }
        exit(EXIT_SUCCESS);
    }
    hijoImpar = fork();
    if (hijoImpar == 0)
    {
        close(envioHijoImpar[1]);
        int arrayNumerosRecibidos[NUMEROMAXGEN];
        int contador = 0;
        read(envioHijoImpar[0], arrayNumerosRecibidos, sizeof(arrayNumerosRecibidos));
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {
            if (arrayNumerosRecibidos[i] % DOS == UNO)
            {
                printf("Soy el hijo 2, he recibido: %d\n", arrayNumerosRecibidos[i]);
                contador++;
            }
        }
        exit(EXIT_SUCCESS);
    }

    return 0;
}
