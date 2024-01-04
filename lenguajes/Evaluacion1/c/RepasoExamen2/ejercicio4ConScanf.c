#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>

int main(int argc, char const *argv[])
{
    printf("introduce un numero para crear procesos hijos\n");
    int numeroHijos;
    scanf("%d", &numeroHijos);
    while (numeroHijos <= 0)
    {
        printf("no se ha podido registrar el numero de hijos, intentalo de nuevo\n");
        scanf("%d", &numeroHijos);
    }
    pid_t hijo[numeroHijos];

    for (int i = 0; i < numeroHijos; i++)
    {
        hijo[i] = fork();
        if (hijo[i] == 0)
        {
            printf("Soy el hijo %d PID %d \n", getpid(), getpid());
            sleep(1);
            printf("yo soy el hijo %d y me han matado\n",getpid());
            fflush(stdout);
            kill(getpid(),SIGKILL);
        }
    }

    for (int i = 0; i < numeroHijos; i++)
    {
        wait(NULL);
    }

    return 0;
}
