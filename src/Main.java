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
        grafo.put('A', Arrays.asList(new Nodo('C', 20), new Nodo('G', 35)));
        grafo.put('C', Arrays.asList(new Nodo('A', 20), new Nodo('G', 15), new Nodo('L', 20)));
        grafo.put('G', Arrays.asList(new Nodo('A', 35), new Nodo('C', 15), new Nodo('M', 10)));
        grafo.put('M', Arrays.asList(new Nodo('G', 10), new Nodo('I', 5), new Nodo('H', 15)));
        grafo.put('I', Arrays.asList(new Nodo('M', 5), new Nodo('B', 10), new Nodo('L', 5)));
        grafo.put('L', Arrays.asList(new Nodo('C', 20), new Nodo('I', 5)));
        grafo.put('B', Arrays.asList(new Nodo('I', 10), new Nodo('H', 20)));
        grafo.put('H', Arrays.asList(new Nodo('B', 20), new Nodo('M', 15), new Nodo('S', 25)));
        grafo.put('S', Arrays.asList(new Nodo('H', 25), new Nodo('Q', 20)));
        grafo.put('Q', Arrays.asList(new Nodo('S', 20)));

        System.out.println("A: Aeropuerto\n" + "C: Cumbaya\n" + "G: Guapulo\n" + "M: Mariscal Sucre\n" + "I: Iñaquito\n"
                + "L: La Carolina\n" + "B: Bellavista\n" + "H: Centro Historico\n" + "S: El Recreo\n" + "Q: Quitumbe\n");

        dijsktra(grafo, 'A');

    }

    public static void dijsktra (Map<Character, List<Nodo>> grafo, char inicio ) {
        Map<Character, Integer> distancias = new HashMap<>();
        Map<Character, Character> padres = new HashMap<>();

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
                    padres.put(vecino.id, actual.id);
                    pq.add(new Nodo(vecino.id, nuevaDist));
                }
            }
        }
        System.out.println("---Rutas mas optimas desde el vertice " + inicio + " hasta los demas nodos---");
        for (Character destino: grafo.keySet()) {
            camino(padres, inicio, destino, distancias.get(destino));
        }
    }

    public static void camino (Map<Character, Character> padres, char inicio, char destino, int distanciaTotal) {
        List<Character> camino = new LinkedList<>();
        Character paso = destino;
        while (paso != null) {
            camino.add(0, paso);
            paso = padres.get(paso);
        }
        System.out.println("Hasta " + destino + ": " + camino + " | Tiempo: " + distanciaTotal);
    }
}
