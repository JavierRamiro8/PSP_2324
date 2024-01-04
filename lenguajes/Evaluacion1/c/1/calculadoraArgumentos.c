#include <stdio.h>
#include <stdlib.h> 

int main(int argc, char const *argv[])
{
    if (argc != 4)
    {
        printf("Argumentos Insuficientes\n");
        return 1; 
    }

    int num1 = atoi(argv[1]);
    char operador = argv[2][0];
    int num2 = atoi(argv[3]);

    int resultado = 0;

    switch (operador)
    {
    case '+':
        resultado = num1 + num2;
        break;
    case '-':
        resultado = num1 - num2;
        break;
    case 'x':
        resultado = num1 * num2;
        break;
    case '/':
        if (num2 != 0)
        {
            resultado = num1 / num2;
        }
        else
        {
            printf("Error: No se puede dividir por cero.\n");
            return 2;
        }
        break;
    default:
        printf("Operador no válido. Use +, -, x, o /.\n");
        return 3;
    }

    printf("Resultado: %d\n", resultado);

    return 0;
}
