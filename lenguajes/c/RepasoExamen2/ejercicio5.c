#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
void manejadorSeñal(int señal)
{
    if (señal == SIGUSR1)
    {
        printf("Soy el hijo y he recibido la señal SIGINT");
    }
}
int main(int argc, char const *argv[])
{
    signal(SIGUSR1, manejadorSeñal);
    pid_t hijo;
    hijo = fork();
    if (hijo < 0)
    {
        perror("No se ha creado el hijo");
        exit(EXIT_FAILURE);
    }
    else if (hijo == 0)
    {
        printf("Esperando a la señal CNTRL+C\n");
        pause();
    }
    else
    {
        printf("Esperando....");
        wait(NULL);
    }
    return 0;
}
