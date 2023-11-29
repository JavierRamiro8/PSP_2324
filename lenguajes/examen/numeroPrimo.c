int esPrimo(int numero)
{
    // Caso especial para 1 y números negativos
    if (numero <= 1)
    {
        return 0;
    }

    // Verificar divisibilidad desde 2 hasta la raíz cuadrada del número
    for (int i = 2; i * i <= numero; ++i)
    {
        if (numero % i == 0)
        {
            return 0; // El número no es primo
        }
    }

    return 1; // Si no se encontraron divisores, el número es primo
}
int main()
{
    int numero;

    // Solicitar al usuario que ingrese un número
    printf("Ingrese un número entero positivo: ");
    scanf("%d", &numero);

    // Verificar si el número es primo y mostrar el resultado
    if (esPrimo(numero))
    {
        printf("%d es un número primo.\n", numero);
    }
    else
    {
        printf("%d no es un número primo.\n", numero);
    }

    return 0;
}
