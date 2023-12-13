#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char const *argv[])
{
    int accion[2];
    pid_t pid;

    if (pipe(accion) == -1) {
        perror("Error al crear la tubería");
        return 1;
    }

    pid = fork();

    if (pid == -1) {
        perror("Error al crear el proceso hijo");
        return 1;
    }

    if (pid == 0) {
        // Proceso hijo
        close(accion[1]); // Cierra el extremo de escritura

        char mensaje_recibido[20];
        int bytes_leidos = read(accion[0], mensaje_recibido, sizeof(mensaje_recibido));
        if (bytes_leidos > 0) {
            mensaje_recibido[bytes_leidos] = '\0';
            printf("Proceso hijo: Mensaje recibido: %s\n", mensaje_recibido);
        } else if (bytes_leidos == 0) {
            printf("Proceso hijo: No se recibieron datos\n");
        } else {
            perror("Error al leer de la tubería");
        }
    } else {
        // Proceso padre
        close(accion[0]); // Cierra el extremo de lectura

        char cadena[] = "Hola mundo";
        int bytes_escritos = write(accion[1], cadena, strlen(cadena));
        if (bytes_escritos > 0) {
            printf("Proceso padre: Mensaje enviado: %s\n", cadena);
        } else {
            perror("Error al escribir en la tubería");
        }
    }

    return 0;
}
