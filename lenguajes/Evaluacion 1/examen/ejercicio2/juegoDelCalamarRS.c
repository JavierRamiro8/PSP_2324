#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#include <time.h>
#define NUMHIJOS 3
void manejadorSeñal(int signal)
{
    if (signal == SIGUSR1)
    {
        printf("me han matado\n");
    }
    else if (signal == SIGINT)
    {
        printf("me han rematado\n");
    }
    exit(EXIT_SUCCESS);
}

int main(int argc, char const *argv[])
{
    pid_t hijos[NUMHIJOS];
    signal(SIGUSR1, manejadorSeñal);
    signal(SIGINT, manejadorSeñal);
    for (int i = 0; i < NUMHIJOS; i++)
    {
        pid_t child = fork();
        if (child == 0)
        {
            printf("Soy el hijo %d (PID %d) y estoy vivo.\n", i + 1, getpid());
            while (1)
            {
                sleep(1);
            }
        }
        else
        {
            hijos[i] = child;
        }
    }
    for (int i = 0; i < NUMHIJOS; i++)
    {
        kill(hijos[i], SIGUSR1);
        kill(hijos[i], SIGINT);
    }
    for (int i = 0; i < NUMHIJOS; i++)
    {
        wait(NULL);
    }
    return 0;
}
