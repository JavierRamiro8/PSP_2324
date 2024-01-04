#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>

int main(void){
    pid_t id;//declara variable servicio
    int n=42;
    double pi = 3.14;

    //clonacion!!!!!
    id=fork();
    
    //hay dos procesos
    
    if(id!=0){
        //Padre
        printf("Soy el padre, mi id es %d y mi hijo tiene el id: %d\n",id);

    }else{
        //Hijo
        printf("Soy el hijo, mi id es %d y mi hijo tiene el id: %d\n",id);

    }
    
    return 0;

}
