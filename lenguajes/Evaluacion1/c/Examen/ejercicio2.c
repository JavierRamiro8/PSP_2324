#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#define ESPRIMO 2
int esPrimo(int num)
{
    if (num < ESPRIMO)
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
    int hijos = atoi(argv[1]);
    pid_t numeroPidHijos[hijos];
    int centenas=100;
    for (int i = 0; i < hijos; i++)
    {
        numeroPidHijos[i]=fork();
        if(numeroPidHijos[i]==0){
            int contadorHastaPrimo;
            while (esPrimo(centenas)==0)
            {
                centenas++;
                contadorHastaPrimo++;
            }
            int resultadoCentenas=centenas;
            printf("el numero primo que ha encontrado el hijo %d es el %d\n",getpid(),resultadoCentenas);
            return contadorHastaPrimo;
            exit(0);
        }
    }
    for (int i = 0; i < hijos; i++)
    {
        waitpid(numeroPidHijos[i],NULL,0);
    }
    return 0;
}
