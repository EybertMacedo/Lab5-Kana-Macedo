package datastructures;

public class Main {
	public static void main(String[] args) {
		 BTree b = new BTree(3);
		 System.out.print("Arbol BTree");
		 b.insertar(6);
		 b.imprimir();
		 
		 b.insertar(9);
		 b.imprimir();
		 b.insertar(17);
		 b.imprimir();
		 b.insertar(11);
		 b.imprimir();
		 b.insertar(3);
		 b.imprimir();
		 b.insertar(12);
		 b.imprimir();
		 b.insertar(8);
		 b.imprimir();
		 b.insertar(20);
		 b.imprimir();
		 b.insertar(22);
		 b.imprimir();
		 b.insertar(23);
		 b.imprimir();
		 b.buscar(23);
		 b.remover(23);
		 b.imprimir();
		 b.insertar(40);
		 b.imprimir();
		 b.insertar(17);
		 b.imprimir();
		 b.insertar(26);
		 b.imprimir();
		 b.insertar(5);
		 b.imprimir();
		 b.insertar(219);
		 b.insertar(23);
		 b.imprimir();
		 b.insertar(223);
		 b.imprimir();
		 b.insertar(323);
		 b.imprimir();
		 b.insertar(113);
		 b.imprimir();
		 b.insertar(153);
		 b.imprimir();
		 b.insertar(163);
		 b.imprimir();
		 b.insertar(173);
		 b.imprimir();
		 b.insertar(253);
		 b.imprimir();
		 b.insertar(293);
		 b.imprimir();
		 b.insertar(233);
		 b.imprimir();
		 b.insertar(453);
		 b.imprimir();
		 b.insertar(140);
		 b.imprimir();
		 b.remover(140);
		 b.imprimir();
		}
}
