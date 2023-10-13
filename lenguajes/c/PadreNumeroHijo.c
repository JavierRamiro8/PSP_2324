#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char const *argv[])
{
    int accion[2];
    pid_t idProceso;
    if (pipe(accion) == -1)
    {
        perror("No se ha creado el pipe o tubería");
        return 1;
    }
    idProceso = fork();
    if (idProceso != 0)
    {
        // Proceso padre
        close(accion[0]); // Cierra el extremo de lectura

        int numero = 2;
        int escribir = write(accion[1], &numero, sizeof(numero));
        if (escribir == -1)
        {
            perror("Error al escribir en la tubería");
            return 1;
        }
    }
    else
    {
        // Proceso hijo
        close(accion[1]); // Cierra el extremo de escritura

        int numeroRecibido;
        int leer = read(accion[0], &numeroRecibido, sizeof(numeroRecibido));
        if (leer == -1)
        {
            perror("Error al leer de la tubería");
            return 1;
        }
        
        int resultado = numeroRecibido * numeroRecibido * numeroRecibido;
        printf("El resultado es: %d\n", resultado);
    }

    return 0;
}
