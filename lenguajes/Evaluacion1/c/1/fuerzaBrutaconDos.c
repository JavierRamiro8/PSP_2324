#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <unistd.h>
#include <sys/wait.h>
#define PRINCIPIOABCEDARIO 'a'
#define FINPRIMERPROCESOHIJO 'm'
#define PRINCIPIOSEGUNDOHIJO 'n'
#define FINALABCEDARIO 'z'

void md5_hash(const char *str, unsigned char *digest) {
    EVP_MD_CTX *mdctx;
    const EVP_MD *md;
    unsigned int md_len;

    OpenSSL_add_all_digests();

    md = EVP_get_digestbyname("md5");
    mdctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(mdctx, md, NULL);
    EVP_DigestUpdate(mdctx, str, strlen(str));
    EVP_DigestFinal_ex(mdctx, digest, NULL);

    EVP_MD_CTX_free(mdctx);
}

void buscarTexto(char start, char end, const char *target_hash) {
    char text[5];
    unsigned char hash[MD5_DIGEST_LENGTH];

    for (text[0] = start; text[0] <= end; text[0]++) {
        for (text[1] = PRINCIPIOABCEDARIO; text[1] <= FINALABCEDARIO; text[1]++) {
            for (text[2] = PRINCIPIOABCEDARIO; text[2] <= FINALABCEDARIO; text[2]++) {
                for (text[3] = PRINCIPIOABCEDARIO; text[3] <= FINALABCEDARIO; text[3]++) {
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
        buscarTexto(PRINCIPIOABCEDARIO, FINPRIMERPROCESOHIJO, target_hash);
    } else {

        // Estamos en el proceso padre
        child_pid2 = fork();

        if (child_pid2 == 0) {
            // Este es el segundo proceso hijo
            buscarTexto(PRINCIPIOSEGUNDOHIJO, FINALABCEDARIO, target_hash);
        } else {
            
            wait(&status);
            wait(&status);
        }
    }

    return 0;
}
