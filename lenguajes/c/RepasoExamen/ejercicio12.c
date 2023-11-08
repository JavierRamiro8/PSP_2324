#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
int main(int argc, char const *argv[])
{
    printf("Introduce un numero para saber cuantos numeros primos y te muestro ");
    int numeroHijos;
    scanf("%d",&numeroHijos);
    printf("Introduce un numero para utilizar x procesos");
    pid_t nProcesos;
    scanf("%d",&nProcesos);
    
    return 0;
}
