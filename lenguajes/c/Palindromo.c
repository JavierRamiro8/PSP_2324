#include<stdio.h>
#include<string.h>
#define BUFFER 1024
int main(){
    char frase[BUFFER];
    int esPalindromo;
    fprintf(stdout,"Introduce una palabra: ");
    scanf("%s",frase);
    int longitudFrase=strlen(frase);
     char caracteres;
    for(int i=0;i<longitudFrase;i++){
       if(frase[i]!= frase[longitudFrase-1-i]){
        esPalindromo=0;
        break;
       }else{
        esPalindromo=1;
       }
    }
    if(esPalindromo==1){
        printf("la palabra %s es palindromo",frase);
    }else{
        printf("la palabra %s no es palindromo",frase);

    }
    return 0;
}
