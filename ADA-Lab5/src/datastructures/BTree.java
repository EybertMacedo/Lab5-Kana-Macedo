package datastructures;

public class BTree {
	private int grado;
	private Node root;
	 	
public class Node {
	 int cantKeys;
	 int keys[] = new int[2 * grado - 1];
	 Node children[] = new Node[2 * grado];
	 boolean esHoja = true; 
	
	 private int encontrarEnNodo(int key) {
	   for (int i = 0; i < this.cantKeys; i++) {
	     if (this.keys[i] == key) {      	 
	       return i;
	     }
	   }
	   return -1;
	 }
}

public BTree(int t) {
	 grado = t;
	 root = new Node();
	 root.cantKeys = 0;
	 root.esHoja = true;
}

public void insertar(final int key) {
	 Node raiz = root;
	 System.out.println("\nAñadiendo : "+key);
	 if (raiz.cantKeys == 2 * grado - 1) {
	   Node nuevo = new Node();
	   root = nuevo;
	   nuevo.esHoja = false;
	   nuevo.cantKeys = 0;
	   nuevo.children[0] = raiz;
	   partir(nuevo, 0, raiz);
	   insertar(nuevo, key);
	 } else {
	   insertar(raiz, key);
	 }
}

private void insertar(Node node, int key) {
	 if (node.esHoja) {
	   int i = 0;
	   for (i = node.cantKeys - 1; i >= 0 && key < node.keys[i]; i--) {
	     node.keys[i + 1] = node.keys[i];
	   }
	   node.keys[i + 1] = key;
	   node.cantKeys = node.cantKeys + 1;
	 } else {
	   int i = 0;
	   for (i = node.cantKeys - 1; i >= 0 && key < node.keys[i]; i--) {
	   }
	   i++;
	   Node aux = node.children[i];
	   if (aux.cantKeys == 2 * grado - 1) {
	     partir(node, i, aux);
	     if (key > node.keys[i]) {
	       i++;
	     }
	   }
	   insertar(node.children[i], key);
	 }
}

private void remover(Node node, int key) {
    int pos = node.encontrarEnNodo(key);
    if (pos != -1) {
      if (node.esHoja) {
        int i = 0;
        for (i = 0; i < node.cantKeys && node.keys[i] != key; i++) {
        }
        ;
        for (; i < node.cantKeys; i++) {
          if (i != 2 * grado - 2) {
            node.keys[i] = node.keys[i + 1];
          }
        }
        node.cantKeys--;
        return;
      }
      if (!node.esHoja) {
        Node pred = node.children[pos];
        int predKey = 0;
        if (pred.cantKeys >= grado) {
          for (;;) {
            if (pred.esHoja) {
              System.out.println(pred.cantKeys);
              predKey = pred.keys[pred.cantKeys - 1];
              break;
            } else {
              pred = pred.children[pred.cantKeys];
            }
          }
          remover(pred, predKey);
          node.keys[pos] = predKey;
          return;
        }

        Node nextNode = node.children[pos + 1];
        if (nextNode.cantKeys >= grado) {
          int nextKey = nextNode.keys[0];
          if (!nextNode.esHoja) {
            nextNode = nextNode.children[0];
            for (;;) {
              if (nextNode.esHoja) {
                nextKey = nextNode.keys[nextNode.cantKeys - 1];
                break;
              } else {
                nextNode = nextNode.children[nextNode.cantKeys];
              }
            }
          }
          remover(nextNode, nextKey);
          node.keys[pos] = nextKey;
          return;
        }

        int temp = pred.cantKeys + 1;
        pred.keys[pred.cantKeys++] = node.keys[pos];
        for (int i = 0, j = pred.cantKeys; i < nextNode.cantKeys; i++) {
          pred.keys[j++] = nextNode.keys[i];
          pred.cantKeys++;
        }
        for (int i = 0; i < nextNode.cantKeys + 1; i++) {
          pred.children[temp++] = nextNode.children[i];
        }

        node.children[pos] = pred;
        for (int i = pos; i < node.cantKeys; i++) {
          if (i != 2 * grado - 2) {
            node.keys[i] = node.keys[i + 1];
          }
        }
        for (int i = pos + 1; i < node.cantKeys + 1; i++) {
          if (i != 2 * grado - 1) {
            node.children[i] = node.children[i + 1];
          }
        }
        node.cantKeys--;
        if (node.cantKeys == 0) {
          if (node == root) {
            root = node.children[0];
          }
          node = node.children[0];
        }
        remover(pred, key);
        return;
      }
    } else {
      for (pos = 0; pos < node.cantKeys; pos++) {
        if (node.keys[pos] > key) {
          break;
        }
      }
      Node tmp = node.children[pos];
      if (tmp.cantKeys >= grado) {
        remover(tmp, key);
        return;
      }
      if (true) {
        Node nb = null;
        int devider = -1;

        if (pos != node.cantKeys && node.children[pos + 1].cantKeys >= grado) {
          devider = node.keys[pos];
          nb = node.children[pos + 1];
          node.keys[pos] = nb.keys[0];
          tmp.keys[tmp.cantKeys++] = devider;
          tmp.children[tmp.cantKeys] = nb.children[0];
          for (int i = 1; i < nb.cantKeys; i++) {
            nb.keys[i - 1] = nb.keys[i];
          }
          for (int i = 1; i <= nb.cantKeys; i++) {
            nb.children[i - 1] = nb.children[i];
          }
          nb.cantKeys--;
          remover(tmp, key);
          return;
        } else if (pos != 0 && node.children[pos - 1].cantKeys >= grado) {

          devider = node.keys[pos - 1];
          nb = node.children[pos - 1];
          node.keys[pos - 1] = nb.keys[nb.cantKeys - 1];
          Node child = nb.children[nb.cantKeys];
          nb.cantKeys--;

          for (int i = tmp.cantKeys; i > 0; i--) {
            tmp.keys[i] = tmp.keys[i - 1];
          }
          tmp.keys[0] = devider;
          for (int i = tmp.cantKeys + 1; i > 0; i--) {
            tmp.children[i] = tmp.children[i - 1];
          }
          tmp.children[0] = child;
          tmp.cantKeys++;
          remover(tmp, key);
          return;
        } else {
          Node lt = null;
          Node rt = null;
          @SuppressWarnings("unused")
		boolean last = false;
          if (pos != node.cantKeys) {
            devider = node.keys[pos];
            lt = node.children[pos];
            rt = node.children[pos + 1];
          } else {
            devider = node.keys[pos - 1];
            rt = node.children[pos];
            lt = node.children[pos - 1];
            last = true;
            pos--;
          }
          for (int i = pos; i < node.cantKeys - 1; i++) {
            node.keys[i] = node.keys[i + 1];
          }
          for (int i = pos + 1; i < node.cantKeys; i++) {
            node.children[i] = node.children[i + 1];
          }
          node.cantKeys--;
          lt.keys[lt.cantKeys++] = devider;

          for (int i = 0, j = lt.cantKeys; i < rt.cantKeys + 1; i++, j++) {
            if (i < rt.cantKeys) {
              lt.keys[j] = rt.keys[i];
            }
            lt.children[j] = rt.children[i];
          }
          lt.cantKeys += rt.cantKeys;
          if (node.cantKeys == 0) {
            if (node == root) {
              root = node.children[0];
            }
            node = node.children[0];
          }
          remover(lt, key);
          return;
        }
      }
    }
  }

  public void remover(int key) {
	System.out.println("\nEliminando elemento : "+key);
    Node x = buscarNodo(root, key);
    if (x == null) {
      return;
    }
    System.out.println("Removiendo..");
    remover(root, key);
  }

  private void partir(Node nuevo, int pos, Node raiz) {	
		 Node aux = new Node(); 
		 aux.esHoja = raiz.esHoja;
		 aux.cantKeys = grado - 1;
		 for (int j = 0; j < grado - 1; j++) {
		   aux.keys[j] = raiz.keys[j + grado];
		 }
		 if (!raiz.esHoja) {
		   for (int j = 0; j < grado; j++) {
		     aux.children[j] = raiz.children[j + grado];
		   }
		 }
		 raiz.cantKeys = grado - 1;
		 for (int j = nuevo.cantKeys; j >= pos + 1; j--) {
		   nuevo.children[j + 1] = nuevo.children[j];
		 }
		 nuevo.children[pos + 1] = aux;
		
		 for (int j = nuevo.cantKeys - 1; j >= pos; j--) {
		   nuevo.keys[j + 1] = nuevo.keys[j];
		 }
		 nuevo.keys[pos] = raiz.keys[grado - 1];
		 nuevo.cantKeys = nuevo.cantKeys + 1;
		 System.out.println("\nPartición necesaria, nueva key papá : "+raiz.keys[raiz.keys.length/2]);
	}
public void imprimir() {
	imprimir(root);
}

private void imprimir(Node x) {
	 assert (x == null);
	 System.out.print("[");
	 for (int i = 0; i < x.cantKeys; i++) {
	   System.out.print(x.keys[i]);
	   if(i!=x.cantKeys-1) System.out.print("|");
	 }
	 System.out.print("] ");
	 if (!x.esHoja) {	
		 System.out.println("");
	   for (int i = 0; i < x.cantKeys + 1; i++) {
	     imprimir(x.children[i]);
	   }
	   System.out.print("\n");
	 }
}
void buscar(int key) {
	System.out.println("\nBuscando elemento : "+key);
	Node x = buscarNodo(root, key);
    if (x == null) {
      return;
    }  
}
private Node buscarNodo(Node node, int key) {	
	int i = 0;    
    if (node == null)
      return node;
    for (i = 0; i < node.cantKeys; i++) {
      if (key < node.keys[i]) {
        break;
      }
      if (key == node.keys[i]) {
    	  System.out.println("Elemento : "+key+" encontrado");
        return node;
      }
    }
    if (node.esHoja) {
    	System.out.println("Elemento : "+key+" no encontrado");
      return null;
    } else {
      return buscarNodo(node.children[i], key);
    }
  }
}