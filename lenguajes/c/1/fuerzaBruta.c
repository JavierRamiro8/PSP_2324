#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>

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

int main() {
    char target_hash[] = "74437fabd7c8e8fd178ae89acbe446f2";

    char text[5]; // Una cadena de 4 letras más el carácter nulo
    unsigned char hash[MD5_DIGEST_LENGTH];

    for (text[0] = 'a'; text[0] <= 'z'; text[0]++) {
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
                        return 0;
                    }
                }
            }
        }
    }

    printf("Texto no encontrado.\n");
    return 0;
}
