#include <stdio.h>
#include <stdlib.h>

void main(){
  omp_set_num_threads(6);
  #pragma omp parallel
  {


      printf("Miguel Fernando" );
      printf("Cuellar" );
      printf("Gauna" );
      printf("\n");

  }
}
