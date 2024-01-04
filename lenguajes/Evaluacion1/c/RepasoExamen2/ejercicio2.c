#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#include <time.h>
#define NUMEROMAXGEN 20
#define NUMEROMAXRAND 100
#define DOS 2
#define CERO 0
#define UNO 1
int main(int argc, char const *argv[])
{
    pid_t hijoPar;
    pid_t hijoImpar;
    int numerosAleatorios;
    int envioHijoPar[DOS];
    int envioHijoImpar[DOS];
    pipe(envioHijoPar);
    pipe(envioHijoImpar);
    srand(time(NULL));
    int par[NUMEROMAXGEN];
    int impar[NUMEROMAXGEN];
    int contadorPar=0;
    int contadorImpar=0;
    for (int i = 0; i < NUMEROMAXGEN; i++)
    {
        numerosAleatorios = rand() % NUMEROMAXRAND;
        if (numerosAleatorios % DOS == CERO)
        {
            par[contadorPar] = numerosAleatorios;
            contadorPar++;
        }
        else if (numerosAleatorios % DOS == UNO)
        {
            impar[contadorImpar] = numerosAleatorios;
            contadorImpar++;
        }
    }

    write(envioHijoPar[1], par, sizeof(par));
    write(envioHijoImpar[1], impar, sizeof(impar));

    hijoPar = fork();
    if (hijoPar == 0)
    {
        close(envioHijoPar[1]);
        int arrayNumerosRecibidos[NUMEROMAXGEN];
        int contador = 0;
        read(envioHijoPar[0], arrayNumerosRecibidos, sizeof(arrayNumerosRecibidos));
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {
            if (arrayNumerosRecibidos[i] != 0)
            {
                printf("soy el hijo 1, he recibido:%d\n", arrayNumerosRecibidos[i]);
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
                printf("Soy el hijo 2, he recibido:%d\n", arrayNumerosRecibidos[i]);
            }
        }
        exit(EXIT_SUCCESS);
    }
    for (int i = 0; i < DOS; i++)
    {
        wait(NULL);
    }

    return 0;
}
