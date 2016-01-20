#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#define STR_LEN 256

char str[STR_LEN];

char* readLine() {
  fgets(str, STR_LEN, stdin);
  str[strlen(str) - 1] = '\0';
  return str;
}


void main(char** args);

void main(char** args) {
;

printf("Write a line:  \n");
char line[STR_LEN];
strcpy(line, readLine());
printf("Your line: \n");
printf("%s \n", line);
}


