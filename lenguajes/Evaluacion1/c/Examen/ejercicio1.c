#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#define MAXRAND 999
#define MINRAND 100
#define MULTIPLODIEZ 10
#define MULTIPLOCINCO 5
#define MULTIPLODOSOPRIMO 2

int esMultiploDeDiez(int num)
{
    if ((num % MULTIPLOCINCO) == 0)
    {
        if ((num % MULTIPLODOSOPRIMO) == 0)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    else
    {
        return 1;
    }
}
int esPrimo(int num)
{
    if (num < MULTIPLODOSOPRIMO)
        return 0;
    for (int i = 2; i * i <= num; ++i)
    {
        if (num % i == 0)
            return 0;
    }
    return 1;
}

int main(int argc, char const *argv[])
{
    int procesoPadre = getpid();
    int pipeHijo1[2];
    int pipeHijoPadre[2];

    if (pipe(pipeHijo1) != 0)
    {
        printf("error en el pipe");
        return 1;
    }
    if (pipe(pipeHijoPadre) != 0)
    {
        printf("error en el pipe");
        return 1;
    }
    srand(time(NULL));
    pid_t hijo1;
    hijo1 = fork();
    int finBucle = 0;
    if (hijo1 == 0)
    {
        int numeroSumaPrimos = 0;
        while (1)
        {
            int numeroAleatorioRecibido;
            read(pipeHijo1[0], &numeroAleatorioRecibido, sizeof(int));
            if (esPrimo(numeroAleatorioRecibido) == 1)
            {
                numeroSumaPrimos = numeroSumaPrimos + numeroAleatorioRecibido;
                printf("el numero %d es primo\n", numeroAleatorioRecibido);
            }
            if (esMultiploDeDiez(numeroAleatorioRecibido) == 0)
            
            {
                printf("El numero %d es multiplo de diez\n", numeroAleatorioRecibido);
                int finBucle = 1;
                write(pipeHijoPadre[1], &finBucle, sizeof(int));
                write(pipeHijoPadre[1], &numeroSumaPrimos, sizeof(int));
                break;
            }
            else
            {
                printf("El numero %d no es multiplo de diez\n", numeroAleatorioRecibido);
                int finBucle = 0;
                write(pipeHijoPadre[1], &finBucle, sizeof(int));
            }
        }
        exit(0);
    }
    else
    {
        while (finBucle == 0)
        {
            int numeroAleatorio = (rand() + MINRAND) % MAXRAND;
            write(pipeHijo1[1], &numeroAleatorio, sizeof(int));
            read(pipeHijoPadre[0], &finBucle, sizeof(int));
        }
    }
    waitpid(hijo1, NULL, 0);
    int totalSumPrimos = 0;
    read(pipeHijoPadre[0], &totalSumPrimos, sizeof(int));
    printf("el numero total de primos es: %d", totalSumPrimos);
    return 0;
}
