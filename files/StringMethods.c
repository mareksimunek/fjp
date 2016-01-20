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
char text[STR_LEN];
strcpy(text, "Hello World!" );
char firstLetter = text[0] ;
;
int size = strlen(text);
;
int isEqual = 0 ;
;
if ( strcmp(text, "Hello World!") ) { 
isEqual = 1 ;
}


int indexOfW = strcspn(text, "W");
;
printf("firstLetter -  %c \n", firstLetter);
printf("size -  %d \n", size);
printf("isEqual -  %d \n", isEqual);
printf("indexOfW -  %d \n", indexOfW);
}


