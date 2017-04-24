#include <stdio.h>
#include <stdlib.h>

void main(){

    #pragma omp single
    {
      printf("my thid is %d\n",omp_get_threadid() );
      printf("Miguel Fernando" );
      printf("Cuellar" );
      printf("Gauna" );
      printf("\n");
    }
}
