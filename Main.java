import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Edge{
    //source is the person 1 in the calls
    //destination is person 2 in the calls
    String person1,person2;
    //weight is the duration of calls between them
    //weight will be used to determine how strongly connected the two persons are
    float weight;

    public Edge(String person1, String person2, float weight) {
        this.person1 = person1;
        this.person2 = person2;
        this.weight = weight;
    }
}

class Graph{
    static class Node{
        String name;
        String callee;
        float weight;

        //a node contains the person's name and id number
        public Node(String name, String callee,float weight) {
            this.name = name;
            this.callee=callee;
            this.weight = weight;
        }
    }

    //list to store the nodes
    List<List<Node>> adj_list = new ArrayList<>();

    public Graph(List<Edge> edges){

        //adjacency list memory allocation
        for (int i=0;i<edges.size();i++){
            adj_list.add(i,new ArrayList<>());
        }

        for (int i=0;i<edges.size();i++){
            Node node = new Node(edges.get(i).person1,edges.get(i).person2,edges.get(i).weight);
            adj_list.get(i).add(node);
        }

    }

    public static void printGraph(Graph graph){
        int source_vertex=0;
        int listSize = graph.adj_list.size();
        float mostMinutes = 0f;
        String source = "";
        String destination = "";

        System.out.println("Structure of the graph");
        while (source_vertex<listSize){
            for (Node node : graph.adj_list.get(source_vertex)) {
                System.out.print("Vertex:" + node.callee + " ==> " + node.name +
                        " (" + node.weight + ")\t" + "seconds");
                if (node.weight>mostMinutes){
                    mostMinutes= node.weight;
                    source = node.name;
                    destination= node.callee;;
                }
            }
            System.out.println();
            source_vertex++;
        }
        System.out.println("The strongest connection is between " + source + " and " + destination
                + " who talked for a total of " + mostMinutes + " seconds");
    }

}
class Main {
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(new Edge("James","Lewis",11f),
                new Edge("James","Timothy",90f),new Edge("Lewis","Timothy",70f),
                new Edge("Timothy","Mercy",50f),new Edge("Lewis","Mercy",110f));
        Graph graph = new Graph(edges);
        graph.printGraph(graph);

    }
}