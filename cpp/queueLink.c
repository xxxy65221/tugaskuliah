#include <stdio.h>
#include <stdlib.h>
#define SIZE 5

typedef struct Node{
   int data;
   struct Node *next;
} node;
node *front = NULL;
node *rear = NULL;

void enqueue(int val){
   node *ptr = (node *)malloc(sizeof(node));
   ptr->data = val;
   ptr->next = NULL;
   if(front == NULL && rear == NULL)
      front = rear = ptr;
   else{
      rear->next = ptr;
      rear = ptr;
   }
   printf("Insert: %d\n", val);
}
void dequeue(){
   node *tmp = front;
   if(front == NULL && rear == NULL)
      printf("queue is empty\n");
   else{
      front = front->next;
      printf("Deleted => %d\n", tmp->data);
      free(tmp);
   }
}
void peek(){
   if(front == NULL) printf("-1\n");
   else printf("\npeek: %d\n", front->data);
}

int main(){
   printf("queue is created\n");
   printf("enqueue:\n");
   for(int i=0; i < SIZE; i++) enqueue(rand());

   peek();
   for(int i=0; i < SIZE-1; i++) dequeue();

   peek();

   return 0;

}
