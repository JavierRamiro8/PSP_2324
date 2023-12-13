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
    int envioPadreImpar[DOS];
    int envioPadrePar[DOS];

    if (pipe(envioHijoPar) < 0)
    {
        perror("No se ha creado el pipe");
        exit(EXIT_FAILURE);
    }
    if (pipe(envioHijoImpar) < 0)
    {
        perror("No se ha creado el pipe");
        exit(EXIT_FAILURE);
    }
    if (pipe(envioPadrePar) < 0)
    {
        perror("No se ha creado el pipe");
        exit(EXIT_FAILURE);
    }

    if (pipe(envioPadreImpar) < 0)
    {
        perror("No se ha creado el pipe");
        exit(EXIT_FAILURE);
    }

    srand(time(NULL));
    int par[NUMEROMAXGEN];
    int impar[NUMEROMAXGEN];
    int contadorPar = 0;
    int contadorImpar = 0;
    for (int i = 0; i < NUMEROMAXGEN; i++)
    {
        numerosAleatorios = rand() % NUMEROMAXRAND;
        if (numerosAleatorios % DOS == CERO)
        {
            par[contadorPar] = numerosAleatorios;
            contadorPar++;
        }
        if (numerosAleatorios % DOS == UNO)
        {
            impar[contadorImpar] = numerosAleatorios;
            contadorImpar++;
        }
    }

    for (; contadorPar < NUMEROMAXGEN; contadorPar++)
    {
        par[contadorPar] = 0;
    }
    for (; contadorImpar < NUMEROMAXGEN; contadorImpar++)
    {
        impar[contadorImpar] = 0;
    }

    write(envioHijoPar[1], par, sizeof(par));
    write(envioHijoImpar[1], impar, sizeof(impar));

    hijoPar = fork();
    if (hijoPar == 0)
    {
        close(envioHijoPar[1]);
        int arrayNumerosRecibidos[NUMEROMAXGEN];
        read(envioHijoPar[0], arrayNumerosRecibidos, sizeof(arrayNumerosRecibidos));
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {
            if (arrayNumerosRecibidos[i] != 0 && arrayNumerosRecibidos[i] < NUMEROMAXRAND)
            {
                printf("Soy el hijo 1, he recibido:%d\n", arrayNumerosRecibidos[i]);
            }
        }
        int resultadoSumaPar = 0;
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {

            if (arrayNumerosRecibidos[i] > 0 && arrayNumerosRecibidos[i] < NUMEROMAXRAND)
            {
                resultadoSumaPar += arrayNumerosRecibidos[i];
            }
        }
        close(envioPadrePar[0]);
        write(envioPadrePar[1], &resultadoSumaPar, sizeof(resultadoSumaPar));
        exit(EXIT_SUCCESS);
    }

    hijoImpar = fork();
    if (hijoImpar == 0)
    {
        close(envioHijoImpar[1]);
        int arrayNumerosRecibidos[NUMEROMAXGEN];
        read(envioHijoImpar[0], arrayNumerosRecibidos, sizeof(arrayNumerosRecibidos));
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {
            if (arrayNumerosRecibidos[i] > 0 && arrayNumerosRecibidos[i] < NUMEROMAXRAND)
            {
                printf("Soy el hijo 2, he recibido:%d\n", arrayNumerosRecibidos[i]);
            }
        }
        int resultadoSumaImpar = 0;
        for (int i = 0; i < NUMEROMAXGEN; i++)
        {
            if (arrayNumerosRecibidos[i] > 0 && arrayNumerosRecibidos[i] < NUMEROMAXRAND)
            {
                resultadoSumaImpar += arrayNumerosRecibidos[i];
            }
        }
        close(envioPadreImpar[0]);
        write(envioPadreImpar[1], &resultadoSumaImpar, sizeof(resultadoSumaImpar));
        exit(EXIT_SUCCESS);
    }

    int resultadoRecibidoSumaPar = 0;
    int resultadoRecibidoSumaImpar = 0;
    close(envioPadreImpar[1]);
    close(envioPadrePar[1]);

    for (int i = 0; i < DOS; i++)
    {
        wait(NULL);
    }

    read(envioPadrePar[0], &resultadoRecibidoSumaPar, sizeof(resultadoRecibidoSumaPar));
    read(envioPadreImpar[0], &resultadoRecibidoSumaImpar, sizeof(resultadoRecibidoSumaImpar));

    printf("la suma de los pares es %d\n", resultadoRecibidoSumaPar);
    printf("la suma de los impares es %d\n", resultadoRecibidoSumaImpar);

    return 0;
}
