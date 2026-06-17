import java.util.*;

public class Main {

    static class Nodo implements Comparable<Nodo> {
        char id;
        int distancia;

        public Nodo (char id, int distancia) {
            this.id = id;
            this.distancia = distancia;
        }

        @Override
        public int compareTo (Nodo otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }

    public static void main(String[] args) {

        Map<Character, List<Nodo>> grafo = new HashMap<>();
        grafo.put('A', Arrays.asList(new Nodo('C', 1), new Nodo('D', 2), new Nodo('B', 4)));
        grafo.put('B', Arrays.asList(new Nodo('A', 4), new Nodo('D', 5), new Nodo('E', 1)));
        grafo.put('C', Arrays.asList(new Nodo('A', 1), new Nodo('D', 2), new Nodo('E', 10)));
        grafo.put('D', Arrays.asList(new Nodo('A', 2), new Nodo('B', 5), new Nodo('C', 2), new Nodo('E', 3)));
        grafo.put('E', Arrays.asList(new Nodo('B', 1), new Nodo('C', 10), new Nodo('D', 3)));

        dijsktra(grafo, 'A');

    }

    public static void dijsktra (Map<Character, List<Nodo>> grafo, char inicio ) {
        Map<Character, Integer> distancias = new HashMap<>();
        for (Character nodo : grafo.keySet()) distancias.put(nodo, Integer.MAX_VALUE);
        distancias.put(inicio, 0);

        PriorityQueue<Nodo> pq = new PriorityQueue<>();
        pq.add(new Nodo(inicio, 0));

        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();

            if (actual.distancia > distancias.get(actual.id)) continue;

            for (Nodo vecino : grafo.get(actual.id)) {
                int nuevaDist = distancias.get(actual.id) + vecino.distancia;
                if (nuevaDist < distancias.get(vecino.id)) {
                    distancias.put(vecino.id, nuevaDist);
                    pq.add(new Nodo(vecino.id, nuevaDist));
                }
            }
        }
        System.out.println("Distancias minimas desde el nodo: " + inicio + ":");
        distancias.forEach((k, v) -> System.out.println("Hasta " + k + ": " + v));
    }
}
