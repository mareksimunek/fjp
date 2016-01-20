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

static char NULA  = ' ';
static char JEDNA  = 'o';
static char KONEC  = '_';
static int INDEX_ZACATEK  = 2;
static int INDEX_KONEC  = 9;
static int INDEX_VODICI_PAS  = 6;

void main(char** args);

char dekodujZnak(char* radka);

void main(char** args) {
;

char radka[STR_LEN];
strcpy(radka, readLine());
while ( strcpy(radka, readLine())[0] !=KONEC  ) { 
printf("%c ", dekodujZnak(radka));}

printf("\n");
}

char dekodujZnak(char* radka) {
char znak = ( char ) 0 ;
;
for ( int i = INDEX_ZACATEK ;
i  <= INDEX_KONEC  ; i ++ ) { 
if ( i  != INDEX_VODICI_PAS  ) { 
znak <<= 1 ;
if ( radka[i] ==JEDNA  ) { 
znak |= 1 ;
}

}

}

return znak ;
}


