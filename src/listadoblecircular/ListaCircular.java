/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadoblecircular;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * @author Diego Fernando Echeverry
 * @author Carlos Augusto Barrera
 */
public class ListaCircular {

    private int contador;

    class Nodo {

        Object dato;
        ListaCircular.Nodo anterior, siguiente;
    }
    ListaCircular.Nodo inicio;

    public void insertarInicio(Object v) {
        if (inicio == null) { // cuando no se ha creado ningun nodo
            inicio = new ListaCircular.Nodo();
            inicio.dato = v;
            inicio.siguiente = inicio;
            inicio.anterior = inicio;
            contador++;
        } else {

            ListaCircular.Nodo nuevo = new ListaCircular.Nodo();
            nuevo.dato = v;
            nuevo.siguiente = inicio;
            nuevo.anterior = inicio.anterior;
            inicio.anterior = nuevo;
            nuevo.anterior.siguiente = nuevo;
            inicio = nuevo;
            contador++;
        }
    }

    public void insertarDatoInicio(Object v) {
        if (inicio != null) {
            ListaCircular.Nodo nuevo = new ListaCircular.Nodo();
            nuevo.dato = v;
            ListaCircular.Nodo aux = inicio.anterior;
            nuevo.anterior = aux;
            aux.siguiente = nuevo;
            inicio.anterior = nuevo;
            nuevo.siguiente = inicio;
            inicio = nuevo;
            contador++;
        }
    }

    public void insertarEnmedio(Object v) {

        int cont = 1;
        ListaCircular.Nodo auxiliar = inicio;
        while (auxiliar.siguiente != inicio) {
            int medio = (int) (contador / 2);
            if (cont == medio) {
                ListaCircular.Nodo nuevo = new ListaCircular.Nodo();
                nuevo.dato = v;
                ListaCircular.Nodo auxiliar2 = auxiliar.siguiente;
                auxiliar.siguiente = nuevo;
                nuevo.anterior = auxiliar;
                nuevo.siguiente = auxiliar2;
                auxiliar2.anterior = nuevo;
                contador++;
                break;
            }

            cont++;
            auxiliar = auxiliar.siguiente;
        }
    }

    public void insertarEnPosicion(Object v, int pos) {

        int cont = 1;
        ListaCircular.Nodo auxiliar = inicio;
        while (auxiliar.siguiente != inicio) {
            if (cont == pos - 1) {
                ListaCircular.Nodo nuevo = new ListaCircular.Nodo();
                nuevo.dato = v;
                ListaCircular.Nodo auxiliar2 = auxiliar.siguiente;
                auxiliar.siguiente = nuevo;
                nuevo.anterior = auxiliar;
                nuevo.siguiente = auxiliar2;
                auxiliar2.anterior = nuevo;
                contador++;
                break;
            }

            cont++;
            auxiliar = auxiliar.siguiente;
        }
    }

    public Object extraerInicio() {
        if (inicio != null) {
            Object v;
            if (inicio.siguiente != inicio) {
                v = inicio.dato; // se inserta el dato
                inicio.siguiente.anterior = inicio.anterior;
                inicio.anterior.siguiente = inicio.siguiente;
                inicio = inicio.siguiente;
                contador--;
            } else {
                v = inicio.dato;
                inicio = null;
            }
            return v;
        }
        return -1;
    }

    public Object extraerFinal() {
        if (inicio != null) {
            Object v;
            if (inicio.siguiente != inicio) {
                v = inicio.anterior.dato;
                inicio.anterior.anterior.siguiente = inicio;
                inicio.anterior = inicio.anterior.anterior;
                contador--;
                return v;
            } else {
                return extraerInicio();
            }
        }
        return -1;
    }

    public void buscar(String palabra) {
        boolean estado = false;
        ListaCircular.Nodo aux = inicio;
        if (aux != null) {
            while (aux.siguiente != inicio) {
                if (aux.dato.equals(palabra)) {
                    estado = true;
                    break;
                }
                aux = aux.siguiente;
            }
            if (estado) {
                JOptionPane.showMessageDialog(null, "si esta!");
            } else {
                JOptionPane.showMessageDialog(null, "No esta!", "Erorr", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void eliminar(String palabra) {
        boolean estado = false;
        ListaCircular.Nodo aux = inicio;
        if (aux != null) {
            while (aux.siguiente != inicio) {
                if (aux.dato.equals(palabra)) {
                    inicio.siguiente.anterior = aux.anterior;
                    inicio.anterior.siguiente = aux.siguiente;
                    inicio = inicio.siguiente;
                    estado = true;
                }
                aux = aux.siguiente;
            }
        }
    }

    /**
     * metodo para imprimir en jlist
     *
     * @return
     */
    public DefaultListModel<String> mostrarL() {
        DefaultListModel<String> l1 = new DefaultListModel<>();

        ListaCircular.Nodo aux = inicio;
        if (aux != null) {
            l1.addElement(" dato | siguiente | anterior");
            while (aux.siguiente != inicio) {
                l1.addElement(
                        aux.dato + " | "
                        + aux.anterior.dato + " | "
                        + aux.siguiente.dato);
                aux = aux.siguiente;
            }
            l1.addElement(
                    aux.dato + " | "
                    + aux.anterior.dato + " | "
                    + aux.siguiente.dato);
        }
        return l1;
    }
}
