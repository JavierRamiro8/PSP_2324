// La idea es que cuando yo clone el proceso padre, que el hijo haga la tabla segun el numero de hijo que es, si es el hijo 1, que haga la tabla de multiplicar del 1
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdlib.h>
#define LIMITEFOR 10;
int main(int argc, char const *argv[])
{
    for (int i = 0; i < LIMITEFOR i++)
    {
        pid_t idHijo = fork();
        if (idHijo == 0)
        {
            int resultado = 0;
            for (int j = 1; j < LIMITEFOR j++)
            {
                resultado = i * j;
                printf("%d x %d = %d\n", i, j, resultado);
            }
            exit(0);
        }
    }
    for (int i = 0; i < LIMITEFOR i++)
    {
        wait(NULL);
    }

    return 0;
}
