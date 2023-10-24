#include <stdio.h>
#include <stdlib.h>  // Para rand()
#include <unistd.h>
#include <sys/types.h> // Para pid_t
#include <sys/wait.h>

#define MAXIMONUMERORANDOM 101
#define MAXNUMERORANDOM 20
#define PAR 2
#define IMPAR 1
#define CERO 0

int main(int argc, char const *argv[])
{
    srand(time(NULL));
    int accionPares[2];
    int accionImpares[2];
    pipe(accionPares);
    pipe(accionImpares);
    pid_t hijo1;
    pid_t hijo2;
    int numerosAleatorios[20];

    for (int i = 0; i < MAXNUMERORANDOM; i++)
    {
        int random = rand() % MAXIMONUMERORANDOM;
        numerosAleatorios[i] = random;
    }


    hijo1 = fork();

    if (hijo1 == CERO)
    {
        close(accionPares[0]);
        close(accionImpares[0]);

        int numerosPares[20];
        int incrementador = 0;
        for (int i = 0; i < MAXNUMERORANDOM; i++)
        {
            if (numerosAleatorios[i] % PAR == CERO)
            {
                numerosPares[incrementador] = numerosAleatorios[i];
                incrementador++;
            }
        }
        for (int i = 0; i < incrementador; i++)
        {
            printf("Soy el hijo 1 y este es mi número par: %d\n", numerosPares[i]);
        }
        write(accionPares[1], &incrementador, sizeof(int));
        close(accionPares[1]);
        exit(0);
    }



    hijo2 = fork();
    if (hijo2 == CERO)
    {
        close(accionPares[0]);
        close(accionImpares[0]);

        int numerosImpares[20];
        int incrementador = 0;
        for (int i = 0; i < MAXNUMERORANDOM; i++)
        {
            if (numerosAleatorios[i] % PAR != CERO)
            {
                numerosImpares[incrementador] = numerosAleatorios[i];
                incrementador++;
            }
        }
        for (int i = 0; i < incrementador; i++)
        {
            printf("Soy el hijo 2 y este es mi número impar: %d\n", numerosImpares[i]);
        }
        write(accionImpares[1], &incrementador, sizeof(int));
        close(accionImpares[1]);
        exit(0);
    }



    close(accionPares[1]);
    close(accionImpares[1]);

    // Esperar a que los hijos terminen
    wait(NULL);
    wait(NULL);

    return 0;
}


