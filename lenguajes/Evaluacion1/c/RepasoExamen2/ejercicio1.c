#include <stdio.h>
#include <strings.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>
#define MAXNUMEROSENTEROS 3
#define NUMERORANDMAX 100
#define CERO 0
#define DOS 2
/*Crea un proceso que cree un proceso hijo, el envíe 3 números enteros aleatorios a través de un pipe. El proceso hijo los ordenará y los escribirá en el fichero salida.txt.*/
int main(int argc, char const *argv[])
{
    int acciones[DOS];
    pipe(acciones);
    srand(time(NULL));
    pid_t hijo;
    hijo = fork();
    if (hijo != CERO)
    {
        close(acciones[0]);
        int numerosAleatorios[3];
        for (int i = 0; i < MAXNUMEROSENTEROS; i++)
        {
            numerosAleatorios[i] = rand() % NUMERORANDMAX;
        }
        write(acciones[1], numerosAleatorios, sizeof(numerosAleatorios));
    }
    if (hijo == CERO)
    {
        close(acciones[1]);
        int numerosAleatoriosRecibidos[3];
        read(acciones[0], numerosAleatoriosRecibidos, sizeof(numerosAleatoriosRecibidos));

        int resultadoOrdenado[MAXNUMEROSENTEROS];

        if (numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[1] && numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[0] = numerosAleatoriosRecibidos[0];
        }
        else if (numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[0] && numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[0] = numerosAleatoriosRecibidos[1];
        }
        else
        {
            resultadoOrdenado[0] = numerosAleatoriosRecibidos[2];
        }

        if (numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[1] && numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[2] || numerosAleatoriosRecibidos[0] > numerosAleatoriosRecibidos[2] && numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[1])
        {
            resultadoOrdenado[1] = numerosAleatoriosRecibidos[0];
        }
        else if (numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[0] && numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[2] || numerosAleatoriosRecibidos[1] > numerosAleatoriosRecibidos[2] && numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[0])
        {
            resultadoOrdenado[1] = numerosAleatoriosRecibidos[1];
        }
        else
        {
            resultadoOrdenado[1] = numerosAleatoriosRecibidos[2];
        }
        if (numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[1] && numerosAleatoriosRecibidos[0] < numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[2] = numerosAleatoriosRecibidos[0];
        }
        else if (numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[0] && numerosAleatoriosRecibidos[1] < numerosAleatoriosRecibidos[2])
        {
            resultadoOrdenado[2] = numerosAleatoriosRecibidos[1];
        }
        else
        {
            resultadoOrdenado[2] = numerosAleatoriosRecibidos[2];
        }
        for (int i = 0; i < MAXNUMEROSENTEROS; i++)
        {
            printf("Estos son los numeros ordenados: %d\n", resultadoOrdenado[i]);
        }
        FILE *archivo;
        archivo = fopen("salidaEjercicio1.txt", "w+");

        for (int i = 0; i < MAXNUMEROSENTEROS; i++)
        {
            fprintf(archivo, "%d\n", resultadoOrdenado[i]);
            //fwrite(resultadoOrdenado[i],sizeof(resultadoOrdenado),sizeof(int),archivo);
        }
        fclose(archivo);
    }
    wait(NULL);
    return 0;
}
