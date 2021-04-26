#include <stdio.h>
#include <stdlib.h>
#define SIZE 5

typedef struct Node{
   int data;
   struct Node *next;
} node;

node *top = NULL;

void push(int val){
   node *ptr = (node *)malloc(sizeof(node));
   ptr->data = val;
   ptr->next = top;
   top = ptr;
   printf("Insert : %d\n", val);
}
void pop(){
   node *tmp = top;
   if(top == NULL) printf("stack is empty");
   else{
      top = top->next;
      printf("Deleted => %d\n", tmp->data);
      free(tmp);
   }
}
void peek(){
   if(top == NULL) printf("-1\n");
   else printf("\npeek: %d\n", top->data);
}

int main(){
   printf("stack is created\n");
   printf("push:\n");
   for(int i=0; i < SIZE; i++) push(rand());

   peek();
   for(int i=0; i < SIZE-1; i++) pop();

   peek();

   return 0;
}
