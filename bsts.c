#include <stdio.h>
#include <stdlib.h>

// Define a structure for a binary tree node
struct node 
{
    int data;
    struct node* left;
    struct node* right;
};

// Create a new node with given value
struct node* create_node(int value) 
{
    struct node* new_node = (struct node*)malloc(sizeof(struct node));
    new_node->data = value;                 // Set node data
    new_node->left = new_node->right = NULL;  // Initialize left and right pointers to NULL
    return new_node;
}

// Insert a value into the BST
struct node* insert(struct node* root, int value) 
{
    if (root == NULL) 
    {
        return create_node(value);  // Create and return a new node if root is NULL
    }
    
    if (value < root->data)  // Insert in the left subtree if value is smaller
    {
        root->left = insert(root->left, value);
    } 
    else  // Insert in the right subtree if value is greater or equal
    {
        root->right = insert(root->right, value);
    }

    return root; 
}

// Search for a key in the BST
struct node* search(struct node* root, int key) 
{
    // Base case: root is NULL or key is present at root
    if (root == NULL || root->data == key) 
    {
        return root;
    }
    
    // If key is smaller than root's data, search in left subtree
    if (key < root->data) 
    {
        return search(root->left, key);
    }
    else  // Otherwise, search in right subtree
    {
        return search(root->right, key);
    }
}

// Inorder traversal (Left, Root, Right)
void inorder(struct node* root) 
{
    if (root != NULL) 
    {
        inorder(root->left);           // Traverse left subtree
        printf("%d ", root->data);    // Print node data
        inorder(root->right);          // Traverse right subtree
    }
}

// Preorder traversal (Root, Left, Right)
void preorder(struct node* root) 
{
    if (root != NULL) 
    {
        printf("%d ", root->data);    // Print node data
        preorder(root->left);          // Traverse left subtree
        preorder(root->right);         // Traverse right subtree
    }
}

// Postorder traversal (Left, Right, Root)
void postorder(struct node* root) 
{
    if (root != NULL) 
    {
        postorder(root->left);        // Traverse left subtree
        postorder(root->right);       // Traverse right subtree
        printf("%d ", root->data);    // Print node data
    }
}

int main() 
{
    struct node* root = NULL;  // Initialize root as NULL
    int n, value, key;
    struct node* found;

    printf("Enter the number of nodes to insert: ");
    scanf("%d", &n);

    printf("Enter the elements : \n");
    for (int i = 0; i < n; i++) 
    {
        printf("element at %d: ", i);
        scanf("%d", &value);
        root = insert(root, value);  // Insert each value into the tree
    }

    printf("\nInorder traversal: ");
    inorder(root);  
    printf("\n");

    printf("Preorder traversal: ");
    preorder(root);  
    printf("\n");

    printf("Postorder traversal: ");
    postorder(root);  
    printf("\n");

    // Search key input
    printf("Enter key to search: ");
    scanf("%d", &key);

    found = search(root, key);

    if (found != NULL)
        printf("Key %d found in the BST\n", key);
    else
        printf("Key %d not found in the BST\n", key);

    return 0;
}

/*
OUTPUT
Enter the number of nodes to insert: 4
Enter the elements : 
element at 0: 4
element at 1: 3
element at 2: 2
element at 3: 1

Inorder traversal: 1 2 3 4 
Preorder traversal: 4 3 2 1 
Postorder traversal: 1 2 3 4 
Enter key to search: 10
Key 10 not found in the BST
*/
