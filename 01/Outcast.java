import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Outcast {
    private WordNet w;

    public Outcast(WordNet wordnet)   {
         w = wordnet;

    } // constructor takes a WordNet object
    public String outcast(String[] nouns) {
        String cast = null;
        int out  = 0;
        for (String e : nouns) {
            int di = 0;
            for (String v : nouns) {
                if (v.equals(e)) continue;
                di += w.distance(v , e);
            }
            if (di > out) {
                cast = e;
                out = di;
            }
        }
        return cast;
    } // given an array of WordNet nouns, return an outcast
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    } // see test client below
}
