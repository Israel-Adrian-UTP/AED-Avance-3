
public class ArbolesBinarios {
    public static void main(String[] args) {
        // CREANDO ARBOL
        Arbol arbol = new Arbol();
        //AGREGANDO CATEGORIAS
        arbol.insertar("Mabe");
        arbol.insertar("Indurama");
        arbol.insertar("Climatic");
        arbol.insertar("Bosh");
        arbol.insertar("Electrolux");
        arbol.insertar("Oster");
        arbol.insertar("Sole");

        System.out.println("\nPREORDEN:");
        arbol.preOrden();
        System.out.println("\nINORDEN:");
        arbol.inOrden();
        System.out.println("\nPOSTORDEN:");
        arbol.postOrden();
        System.out.println("\nBUSQUEDA:");
        arbol.buscar("Bosh");
        arbol.buscar("Whirlpool");
        System.out.println("\nELIMINAR:");
        arbol.eliminar("Oster"); 
        arbol.inOrden();
    }
}

//CLASE NODO
class Nodo {
    //ATRIBUTOS
    String categoria;
    Nodo izquierdo;
    Nodo derecho;
    
    //CONSTRUCTOR
    public Nodo(String categoria) {
        this.categoria = categoria;
        this.izquierdo = null;
        this.derecho = null;
    }
}

//CLASE ARBOL
class Arbol {
    Nodo raiz;
    public Arbol() {
        raiz = null;
    }
    //METODO INSERTAR
    public void insertar(String categoria) {
        raiz = insertarRecursivo(raiz, categoria);
        System.out.println("CATEGORIA REGISTRADA CORRECTAMENTE.");
    }
    private Nodo insertarRecursivo(Nodo actual, String categoria) {
        if (actual == null) {
            return new Nodo(categoria);
        }
        int comparacion = categoria.compareTo(actual.categoria);
        if (comparacion < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, categoria);
        } else if (comparacion > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, categoria);
        }
        return actual;
    }
    //METODO BUSCAR
    public boolean buscar(String categoria) {
        boolean encontrado = buscarRecursivo(raiz, categoria);
        if (encontrado) {
            System.out.println("- CATEGORIA '" + categoria + "' ENCONTRADA.");
        } else {
            System.out.println("- CATEGORIA '" + categoria + "' NO ENCONTRADA.");
        }
        return encontrado;
    }
    private boolean buscarRecursivo(Nodo actual, String categoria) {
        if (actual == null) {
            return false;
        }
        int comparacion = categoria.compareTo(actual.categoria);
        if (comparacion == 0) {
            return true;
        }
        return comparacion < 0 ? buscarRecursivo(actual.izquierdo, categoria) : buscarRecursivo(actual.derecho, categoria);
    }
    //METODO ELIMINAR
    public void eliminar(String categoria) {
        raiz = eliminarRecursivo(raiz, categoria);
        System.out.println("- ELIMINANDO LA CATEGORIA: " + categoria);
    }
    private Nodo eliminarRecursivo(Nodo actual, String categoria) {
        if (actual == null) {
            return null;
        }
        int comparacion = categoria.compareTo(actual.categoria);
        if (comparacion < 0) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, categoria);
        } else if (comparacion > 0) {
            actual.derecho = eliminarRecursivo(actual.derecho, categoria);
        } else {
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }
            actual.categoria = minText(actual.derecho);
            actual.derecho = eliminarRecursivo(actual.derecho, actual.categoria);
        }
        return actual;
    }
    private String minText(Nodo raiz) {
        String minv = raiz.categoria;
        while (raiz.izquierdo != null) {
            minv = raiz.izquierdo.categoria;
            raiz = raiz.izquierdo;
        }
        return minv;
    }
    //METODO PREORDEN
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }
    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.categoria + " | "); 
            preOrdenRecursivo(nodo.izquierdo);   
            preOrdenRecursivo(nodo.derecho);     
        }
    }
    //METODO INORDEN
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }
    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo);   
            System.out.print(nodo.categoria + " | "); 
            inOrdenRecursivo(nodo.derecho);     
        }
    }
    //METODO POSTORDEN
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }
    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo);   
            postOrdenRecursivo(nodo.derecho);     
            System.out.print(nodo.categoria + " | "); 
        }
    }
}
