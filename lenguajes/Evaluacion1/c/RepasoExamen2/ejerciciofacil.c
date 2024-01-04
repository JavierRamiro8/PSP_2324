#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#include <time.h>
#define CERO 0
#define MAXNUMALEATORIOS 3
#define MAXRAND 100
#define TRES 3
int main(int argc, char const *argv[])
{
    int padreHijo[2];
    int hijoPadre[2];
    pipe(padreHijo);
    pipe(hijoPadre);
    pid_t hijo;
    hijo = fork();
    if (hijo != CERO)
    {
        close(padreHijo[0]);
        close(hijoPadre[1]);
        int numerosAleatorios[TRES];
        srand(time(NULL));
        for (int i = 0; i < MAXNUMALEATORIOS; i++)
        {
            numerosAleatorios[i] = rand() % MAXRAND;
        }
        write(padreHijo[1], numerosAleatorios, sizeof(numerosAleatorios));
    }
    if (hijo == CERO)
    {
        close(padreHijo[1]);
        close(hijoPadre[0]);
        int numerosAleatoriosRecibidos[TRES];
        read(padreHijo[0], numerosAleatoriosRecibidos, sizeof(numerosAleatoriosRecibidos));

        int resultadoOrdenado[TRES];

        if (numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[1] && numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[0] = numerosAleatoriosRecibidos[0];
        }
        else if (numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[0] && numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[0] = numerosAleatoriosRecibidos[1];
        }
        else
        {
            resultadoOrdenado[0] = numerosAleatoriosRecibidos[2];
        }

        if (numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[1] && numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[2] || numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[2] && numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[1])
        {
            resultadoOrdenado[1] = numerosAleatoriosRecibidos[0];
        }
        else if (numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[0] && numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[2] || numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[2] && numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[0])
        {
            resultadoOrdenado[1] = numerosAleatoriosRecibidos[1];
        }
        else
        {
            resultadoOrdenado[1] = numerosAleatoriosRecibidos[2];
        }
        if (numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[1] && numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[2] = numerosAleatoriosRecibidos[0];
        }
        else if (numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[0] && numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[2] = numerosAleatoriosRecibidos[1];
        }
        else
        {
            resultadoOrdenado[2] = numerosAleatoriosRecibidos[2];
        }
        write(hijoPadre[1], resultadoOrdenado, sizeof(resultadoOrdenado));
        exit(EXIT_SUCCESS);
    }
    wait(NULL);
    int resultadoRecibidoOrdenado[TRES];
    read(hijoPadre[0], resultadoRecibidoOrdenado, sizeof(resultadoRecibidoOrdenado));
    for (int i = 0; i < TRES; i++)
    {
        printf("%d\n", resultadoRecibidoOrdenado[i]);
    }
    return 0;
}
