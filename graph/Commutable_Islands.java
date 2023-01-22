class Pair{
    int u;
    int v;
    int w;
    Pair(int a, int b, int c){
        u = a;
        v = b;
        w = c;
    }
}
class PairComparator implements Comparator<Pair> {
    public int compare(Pair A, Pair B){
        if(A.w < B.w) return -1;
        if(A.w > B.w) return 1;
        return 0; 
    }
}

// solution using prims algorithm
public class Solution {
    public int solve(int A, int[][] B) {
        int cost = 0;

        // create an adjancy list
        ArrayList[] adjList = new ArrayList[A+1];

        for(int i = 1; i <= A; i++){
            // init all node with empty array to avoid null pointer execption
            adjList[i] = new ArrayList<Pair>();
        }

        for(int i = 0; i < B.length; i++){
            // add the nodes in adjList
            int u = B[i][0];
            int v = B[i][1];
            int w = B[i][2];

            adjList[u].add(new Pair(u, v, w));
            adjList[v].add(new Pair(v, u, w));
        }

        // decalare a custom minHeap and a visited array
        PriorityQueue<Pair> mh = new PriorityQueue<Pair>(A, new PairComparator());
        boolean[] vis = new boolean[A+1];
        
        // pick any node and load it in the minHeap
        vis[1] = true;
        for(int i = 0; i < adjList[1].size(); i++){
            Pair temp = (Pair)adjList[1].get(i);
            mh.add(temp);
        }

        while(!mh.isEmpty()){
            Pair temp = mh.peek();
            mh.poll();
            // if both the nodes are not visisted then
            if(!(vis[temp.u] && vis[temp.v])){
                // mark second node as visited
                vis[temp.v] = true; 

                // and load its connected and valid nodes in minheap
                for(int i = 0; i < adjList[temp.v].size(); i++){
                    Pair node = (Pair)adjList[temp.v].get(i);
                    // a valid node is a node which is not visited.
                    if(!vis[node.v]){
                        mh.add(node);
                    }
                }
                cost += temp.w;
            }
            
        }
        return cost;
    }
}
