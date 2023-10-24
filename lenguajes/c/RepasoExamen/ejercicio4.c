#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Uso: %s <número de hijos>\n", argv[0]);
        return 1;
    }

    int numHijos = atoi(argv[1]);

    if (numHijos <= 0) {
        printf("El número de hijos debe ser un entero positivo.\n");
        return 1;
    }

    pid_t hijos[numHijos];

    // Crear n hijos
    for (int i = 0; i < numHijos; i++) {
        pid_t child = fork();

         if (child == 0) {
            // Código del proceso hijo
            printf("Soy el hijo %d (PID %d) y estoy vivo.\n", i + 1, getpid());
            while (1) {
                // Espera infinita para mantener al hijo vivo
                sleep(1);
            }
        } else {
            hijos[i] = child; // Almacenar el PID del hijo
        }
    }
    sleep(2);

    for (int i = 0; i < numHijos; i++) {
        kill(hijos[i], SIGKILL);
        printf("Señal SIGKILL enviada al hijo %d (PID %d)\n", i + 1, hijos[i]);
    }

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < numHijos; i++) {
        wait(NULL);
    }

    return 0;
}

