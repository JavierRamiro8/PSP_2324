#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char const *argv[])
{
    char* comando="ip";
    char* argumentos[]={"ip","a",NULL};
    execvp(comando,argumentos);
    return 0;
}
