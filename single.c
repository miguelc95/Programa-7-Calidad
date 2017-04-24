#include <stdio.h>
#include <stdlib.h>

void main(){
  omp_set_num_threads(6);
  #pragma omp parallel
  {
    #pragma omp single
    {
      printf("my thid is %d\n",omp_get_threadid() );
      printf("Miguel Fernando" );
      printf("Cuellar" );
      printf("Gauna" );
      printf("\n");
    }
  }
}
