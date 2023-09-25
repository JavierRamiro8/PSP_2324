#include<stdio.h>

int main(void){
    int numeroCelsius;
    int numeroFarhenheit;
    printf("Introduce un numero: ");
    scanf("%d",&numeroCelsius);
    numeroFarhenheit=(numeroCelsius * 9/5) + 32;
    printf("El numero Celsius que has puesto es %d convertido a farhenheit es %d\n",numeroCelsius,numeroFarhenheit);
}