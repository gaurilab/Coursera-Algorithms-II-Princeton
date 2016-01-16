import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.Stack;
//import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
//import java.util.Iterator;


public class SAP {
    private Digraph gra;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException();
        //Topological t = new Topological(G);
        //if (!t.hasOrder())
        //throw new IllegalArgumentException("Graph is not a DAG");

        //DAG 
        int rc = 0;
        //How to check if its rooted
 //       StdOut.println(G);
 //       for (int v = 0 ; v < G.V() ; ++v) {
   //         if (G.outdegree(v) == 0) {
     //           ++rc;
       //         root = v;
         //   }
           // if (rc > 1) 
           //throw new IllegalArgumentException("Graph has multiple roots");
 //       }
        gra = new Digraph(G);
    }
    private boolean isValid(int v) {
        return (v >= 0 && v <= this.gra.V() - 1);
    }
    private boolean isValid(Iterable<Integer> v, Iterable<Integer> w) {
        for (int k : v) if (!isValid(k)) return false;
        for (int k : w) if (!isValid(k)) return false;
        return true;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (!isValid(v)) 
            throw new IndexOutOfBoundsException("Vertices  out of range");
        if (!isValid(w))
            throw new IndexOutOfBoundsException("Vertices  out of range");

        BreadthFirstDirectedPaths tV = new BreadthFirstDirectedPaths(gra , v);
        BreadthFirstDirectedPaths tW = new BreadthFirstDirectedPaths(gra , w);

        int sd = Integer.MAX_VALUE;
        int sa = -1;
        for (int c = 0; c < gra.V(); ++c) {
            if (tV.hasPathTo(c) && tW.hasPathTo(c)) {
                int tmp  = tV.distTo(c) + tW.distTo(c);
                if (sd > tmp) {
                    sd = tmp;
                    sa = c;
                }
            }
        }
        if (sd == Integer.MAX_VALUE) sd = -1;
        return sd;

    }

    // a common ancestor of v and w that participates
    //in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (!isValid(v))
            throw new IndexOutOfBoundsException("Vertices  out of range");
        if (!isValid(w))
            throw new IndexOutOfBoundsException("Vertices  out of range");

        BreadthFirstDirectedPaths tV = new BreadthFirstDirectedPaths(gra , v);
        BreadthFirstDirectedPaths tW = new BreadthFirstDirectedPaths(gra , w);

        int sd = Integer.MAX_VALUE;
        int sa = -1;
        for (int c = 0; c < gra.V(); ++c) {
            if (tV.hasPathTo(c) && tW.hasPathTo(c)) {
                int tmp  = tV.distTo(c) + tW.distTo(c);
                if (sd > tmp) {
                    sd = tmp;
                    sa = c;
                }
            }
        }
        return sa;

    }

    // length of shortest ancestral path 
    //between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (!isValid(v , w)) 
            throw new IndexOutOfBoundsException("Vertices  out of range");

        BreadthFirstDirectedPaths tV = new BreadthFirstDirectedPaths(gra , v);
        BreadthFirstDirectedPaths tW = new BreadthFirstDirectedPaths(gra , w);

        int sd = Integer.MAX_VALUE;
        int sa = -1;
        for (int c = 0; c < gra.V(); ++c) {
            if (tV.hasPathTo(c) && tW.hasPathTo(c)) {
                int tmp  = tV.distTo(c) + tW.distTo(c);
                if (sd > tmp) {
                    sd = tmp;
                    sa = c;
                }
            }
        }
        if (sd == Integer.MAX_VALUE) sd = -1;
        return sd;
    }

    // a common ancestor that participates 
    //in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (!isValid(v , w)) 
            throw new IndexOutOfBoundsException("Vertices  out of range");

        BreadthFirstDirectedPaths tV = 
            new BreadthFirstDirectedPaths(gra , v);
        BreadthFirstDirectedPaths tW = 
            new BreadthFirstDirectedPaths(gra , w);

        int sd = Integer.MAX_VALUE;
        int sa = -1;
        for (int c = 0; c < gra.V(); ++c) {
            if (tV.hasPathTo(c) && tW.hasPathTo(c)) {
                int tmp  = tV.distTo(c) + tW.distTo(c);
                if (sd > tmp) {
                    sd = tmp;
                    sa = c;
                }
            }
        }
        return sa;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length,
                    ancestor);
        }
    }
}
