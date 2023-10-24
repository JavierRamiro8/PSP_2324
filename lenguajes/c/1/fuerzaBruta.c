#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#define PRINCIPIOABCEDARIO 'a'
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
    EVP_DigestFinal_ex(mdctx, digest, &md_len);

    EVP_MD_CTX_free(mdctx);
}

int main() {
    char target_hash[] = "74437fabd7c8e8fd178ae89acbe446f2";

    char arrayLetras[5];
    unsigned char hash[MD5_DIGEST_LENGTH];

    for (arrayLetras[0] = PRINCIPIOABCEDARIO; arrayLetras[0] <= FINALABCEDARIO; arrayLetras[0]++) {
        for (arrayLetras[1] = PRINCIPIOABCEDARIO; arrayLetras[1] <= FINALABCEDARIO; arrayLetras[1]++) {
            for (arrayLetras[2] = PRINCIPIOABCEDARIO; arrayLetras[2] <= FINALABCEDARIO; arrayLetras[2]++) {
                for (arrayLetras[3] = PRINCIPIOABCEDARIO; arrayLetras[3] <= FINALABCEDARIO ; arrayLetras[3]++) {
                    md5_hash(arrayLetras, hash);

                    char hash_str[33];
                    for (int i = 0; i < 16; i++) {
                        sprintf(&hash_str[i * 2], "%02x", (unsigned int)hash[i]);
                    }

                    if (strcmp(hash_str, target_hash) == 0) {
                        printf("Texto encontrado: %s\n", arrayLetras);
                        return 0;
                    }
                }
            }
        }
    }

    printf("Texto no encontrado.\n");
    return 0;
}
