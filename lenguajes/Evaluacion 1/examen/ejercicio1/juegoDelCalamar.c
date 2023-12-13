#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#include <time.h>
#define LEER 0
#define ESCRIBIR 1
#define MAXRAND 100
int main(int argc, char const *argv[])
{
    int numero1 = atof(argv[1]);
    int numeroAleatorio[numero1];
    int numeroCreado = 0;
    int accionHijo1[2];
    if (pipe(accionHijo1) < 0)
    {
        perror("No se creo el pipe");
    }
    srand(time(NULL));
    for (int i = 0; i < numero1; i++)
    {
        numeroCreado = rand() % MAXRAND;

        numeroAleatorio[i] = numeroCreado;
        printf("%d\n", numeroAleatorio[i]);
    }
    printf("\n");
    write(accionHijo1[ESCRIBIR], &numeroAleatorio, sizeof(numeroAleatorio));
    pid_t hijo1;
    hijo1 = fork();
    if (hijo1 == 0)
    {
        close(accionHijo1[ESCRIBIR]);
        int arrayNumerosRecibidos[numero1];
        read(accionHijo1[LEER], arrayNumerosRecibidos, sizeof(arrayNumerosRecibidos));
        for (int i = 0; i < numero1; i++)
        {
            printf("%d", arrayNumerosRecibidos[i]);
            printf("\n");
        }
    }
    return 0;
}
