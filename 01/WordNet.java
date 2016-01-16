import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.ST;

public class WordNet {
    private ST<String, Queue<Integer>> nouns;
    private ST<Integer, String> keys;
    private Digraph G;
    private SAP anc;


    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        //StdOut.println(synsets);
        //StdOut.println(hypernyms);
        In n = new In(synsets);
        In r = new In(hypernyms);

        int S = 0;
        nouns = new ST<String, Queue<Integer>>();
        keys = new ST<Integer , String>();
        while (!n.isEmpty()) {
            String[] a = n.readLine().split(","); 

            assert (a.length == 3);
            ++S;
            int idx = Integer.parseInt(a[0]);

            String[] b = a[1].split(" ");

            for (int i = 0; i < b.length; i++) {
                Queue<Integer> dq = nouns.get(b[i]);
                if (dq == null) {
                    dq = new Queue<Integer>();

                }
                dq.enqueue(idx);
                nouns.put(b[i] , dq);
            }
            keys.put(idx , a[1]);
        }

        G = new Digraph(S);

        while (!r.isEmpty()) {
            String[] t = r.readLine().split(","); 
            int source = Integer.parseInt(t[0]);
            int destination = 0;
            for (int i = 1; i < t.length; i++) {
                destination = Integer.parseInt(t[i]);
                G.addEdge(source , destination);
            }
        }
        anc = new SAP(G);

        //StdOut.println(G);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nouns.contains(word);
    }


    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) 
            throw new IllegalArgumentException();

        return anc.length(nouns.get(nounA) , nouns.get(nounB));
    }

    // a synset (second field of synsets.txt) 
    //that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return keys.get(anc.ancestor(nouns.get(nounA) , nouns.get(nounB)));
    }

    // do unit testing of this class

    public static void main(String[] args) {

    }

}
