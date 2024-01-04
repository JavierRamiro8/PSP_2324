#include <stdio.h>
#include <string.h>
#include <unistd.h>
int main(int argc, char const *argv[])
{
    char *comando="date";
    char *argumentos[]={"date",NULL};
    execvp(comando,argumentos);
    return 0;
}
