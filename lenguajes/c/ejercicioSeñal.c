#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>


void usr1(int signo){
    printf("Hola\n");
}
void usr2(int signo){
    printf("mundo\n");
}

int main(int argc, char const *argv[])
{
    signal(SIGUSR1,usr1);

    signal(SIGUSR2,usr2); 
    while (1)
    {
        printf(".");
        fflush(stdout);
        sleep(1);
    }

    return 0;
}