
#include <stdio.h>
#include <stdlib.h>
int main() {
  FILE *file;
  char line[16];

  file = fopen("input.txt", "r");
  while (fgets(line, sizeof(line), file)){
    printf("%s", line);
  }
  char str[] = "BCDEFGHIJKLMNOPQ";
  //burris values are shifted right by 1 here since my strings in C are 0..15
     int result =abs( ( str[1] + ((str[3] + str[4] + str[6]  + str[7]) / 381 + str[0]) / 587 - str[10]));
  printf("Hash result: %d\n", result);
  fclose(file);
    return 0;
}
