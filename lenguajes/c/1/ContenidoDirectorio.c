#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
    char archivoOriginal;
    char *comando = "ls";
    char *argumentos[] = {"ls", "/", NULL};

    archivoOriginal = open("salida.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (archivoOriginal == NULL)
    {
        perror("No es posible abrir el archivo");
        return 1;
    }

    pid_t child_pid = fork();
    if (child_pid == 0)
    {
        dup2(archivoOriginal, STDOUT_FILENO);
        execvp(comando, argumentos);
        perror("Error en execvp");
        exit(1);
    }
    else if (child_pid < 0)
    {
        perror("Error en fork");
        return 1;
    }
    else
    {
        int status;
        waitpid(child_pid, &status, 0);
        close(archivoOriginal);
    }

    return 0;
}
