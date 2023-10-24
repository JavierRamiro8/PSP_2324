#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>

#define MAX_CHARACTERS 26  // Número de letras del alfabeto (a-z)

// Función para generar combinaciones de caracteres únicas
void generateCombinations(FILE *file, int length) {
    char combination[length + 1];
    memset(combination, 'a', length);
    combination[length] = '\0';

    char end[length + 1];
    memset(end, 'z', length);
    end[length] = '\0';

    do {
        fprintf(file, "%s\n", combination);

        // Generar la siguiente combinación lexicográfica
        int i = length - 1;
        while (i >= 0 && combination[i] == 'z') {
            i--;
        }
        if (i >= 0) {
            combination[i]++;
            for (int j = i + 1; j < length; j++) {
                combination[j] = 'a';
            }
        }
    } while (strcmp(combination, end) != 0);
}

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

    // Bucle para crear hijos
    for (int i = 1; i <= numHijos; i++) {
        pid_t child = fork();

        if (child < 0) {
            perror("Error al crear el proceso hijo");
            return 1;
        } else if (child == 0) {
            // Proceso hijo

            // Crear el nombre del archivo
            char filename[15]; // Suficiente para "datosX.txt"
            sprintf(filename, "datos%d.txt", i);

            // Abrir el archivo para escritura
            FILE *file = fopen(filename, "w");

            if (file == NULL) {
                perror("Error al abrir el archivo para escritura");
                return 1;
            }

            // Generar combinaciones de caracteres únicas
            generateCombinations(file, i);

            fclose(file);
            exit(0);
        }
    }

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < numHijos; i++) {
        wait(NULL);
    }

    return 0;
}
