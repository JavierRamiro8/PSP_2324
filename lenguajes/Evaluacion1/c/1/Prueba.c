/*int main(void){
    int num=42;
    printf("Este es tu numero: %d\n", num);
    scanf("%d",&num);
    printf("El numero nuevo es: %d\n", num);
    return 0;

}*/

#include <stdio.h>

/*int main() {
    // Definir los números a sumar
    int numero1 = 5;
    int numero2 = 10;

    // Realizar la suma
    int suma = numero1 + numero2;

    // Mostrar el resultado
    printf("La suma de %d y %d es: %d\n", numero1, numero2, suma);

    return 0;
}*/

int main(void){
    int numero1=0;
    int suma;
    printf("Introduce un numero \n");
    scanf("%d",&numero1);
    suma=numero1;
    printf("Introduce otro numero \n");
    scanf("%d",&numero1);
    suma+=numero1;
    printf("La suma es %d\n" ,suma);
    return 0;
}