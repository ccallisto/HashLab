
#include <stdio.h>
#include <stdlib.h>
int main() {
  FILE *file;
  char line[20]; //changed to 20 because of read errors
  int result;
  file = fopen("input.txt", "r");
  while (fgets(line, sizeof(line), file)){
    printf("%s", line);
    result = abs(( line[1] + ((line[3] + line[4] + line[6]  + line[7]) / 381 + line[0]) / 587 - line[10]));
    printf("Hash result: %d\n", result);
  }
  //burris values are shifted right by 1 here since my strings in C are 0..15
  fclose(file);
    return 0;
}
