#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <unistd.h>
#include <sys/wait.h>

// Función para calcular el hash MD5 de una cadena
void md5_hash(const char *str, unsigned char *digest) {
    EVP_MD_CTX *mdctx;
    const EVP_MD *md;
    unsigned int md_len;

    OpenSSL_add_all_digests();

    md = EVP_get_digestbyname("md5");
    mdctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(mdctx, md, NULL);
    EVP_DigestUpdate(mdctx, str, strlen(str));
    EVP_DigestFinal_ex(mdctx, digest, &md_len);

    EVP_MD_CTX_free(mdctx);
}

// Función que realiza la búsqueda por fuerza bruta en un rango específico
void buscarTexto(char start, char end, const char *target_hash) {
    char text[5]; // Una cadena de 4 letras más el carácter nulo
    unsigned char hash[MD5_DIGEST_LENGTH];

    for (text[0] = start; text[0] <= end; text[0]++) {
        for (text[1] = 'a'; text[1] <= 'z'; text[1]++) {
            for (text[2] = 'a'; text[2] <= 'z'; text[2]++) {
                for (text[3] = 'a'; text[3] <= 'z'; text[3]++) {
                    md5_hash(text, hash);

                    char hash_str[33];
                    for (int i = 0; i < 16; i++) {
                        sprintf(&hash_str[i * 2], "%02x", (unsigned int)hash[i]);
                    }

                    if (strcmp(hash_str, target_hash) == 0) {
                        printf("Texto encontrado: %s\n", text);
                        _exit(0);
                    }
                }
            }
        }
    }
}

int main() {
    char target_hash[] = "74437fabd7c8e8fd178ae89acbe446f2";
    int status;

    pid_t child_pid1, child_pid2;

    // Crear dos procesos hijos
    child_pid1 = fork();

    if (child_pid1 < 0) {
        perror("Fallo al crear el primer proceso hijo");
        return 1;
    }

    if (child_pid1 == 0) {
        // Este es el primer proceso hijo
        buscarTexto('a', 'm', target_hash);
    } else {
        // Estamos en el proceso padre
        child_pid2 = fork();

        if (child_pid2 < 0) {
            perror("Fallo al crear el segundo proceso hijo");
            return 1;
        }

        if (child_pid2 == 0) {
            // Este es el segundo proceso hijo
            buscarTexto('n', 'z', target_hash);
        } else {
            // Estamos en el proceso padre nuevamente
            // Esperar a que ambos procesos hijos terminen
            wait(&status);
            wait(&status);
        }
    }

    printf("Texto no encontrado.\n");
    return 0;
}
