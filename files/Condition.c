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

static int size  = 5;
static int a  = 10;

void main(char** args);

int getNumber();

int getSum(int a,int b);

void main(char** args) {
for ( int i = 0 ;
i  < size  ; i ++ ) { 
printf("%d ", i);}

printf("\n");
int start = 0 ;
;
while ( start  < size  ) { 
printf("%d \n", start);start ++;}

start = 0 ;

if ( start  < size  && getNumber ( )  > size  ) { 
a = 15 ;
}

 else { 
a = 0 ;
}


printf("%d \n", getNumber());
printf("%d \n", getSum(1,1));
}

int getNumber() {
return a ;
}

int getSum(int a, int b) {
return a + b ;
}


