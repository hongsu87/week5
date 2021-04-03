public class ShortestPath {
    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.value(0, 1, 7);
        g.value(0, 4, 3);
        g.value(0, 5, 10);
        g.value(1, 4, 2);
        g.value(1, 2, 4);
        g.value(1, 5, 6);
        g.value(2, 3, 2);
        g.value(3, 5, 9);
        g.value(3, 6, 4);
        g.value(1, 3, 10);
        g.value(3, 4, 11);
        g.value(4, 6, 5);
        dijkstra(g, 0);
    }

    private static void dijkstra(Graph g, int v) {
        int[] distance = new int[g.n]; // 시작 정점으로부터 최단경로 거리
        boolean[] visit = new boolean[g.n]; // 방문한 정점 표시

        // 초기화
        for(int i = 0; i<g.n; i++) {
            distance[i] = Integer.MAX_VALUE;
            visit[i] = false;
        }

        // 시작 정점 방문 표시
        distance[v] = 0;
        visit[v] = true;

        // distance 초기값 저장
        for(int i = 0; i<g.n; i++) {
            if(!visit[i] && g.weight[v][i] !=0){
                distance[i] = g.weight[v][i];
            }
        }

        // 모든 정점을 방문하는 동안 반복
        for(int j = 0; j<g.n-1; j++){
            int min = Integer.MAX_VALUE;
            int min_pos = -1;
            for(int i = 0; i<g.n; i++){
                if(distance[i] < min && !visit[i]){
                    min = distance[i];
                    min_pos = i;
                }
            }

            visit[min_pos] = true;
            for(int i = 0; i<g.n; i++){
                if(!visit[i] && g.weight[min_pos][i]!=0)
                    if(distance[min_pos] + g.weight[min_pos][i] < distance[i])
                        distance[i] = distance[min_pos] + g.weight[min_pos][i];
            }
        }

        // 최단 경로의 거리 출력
        for(int i = 0; i<g.n; i++) {
            System.out.print(distance[i]+" ");
        }
    }

    private static class Graph {
        int n; // 정점의 개수
        int[][] weight; // 가중치
        public Graph(int n){
            this.n = n;
            weight = new int[n][n];
        }
        public void value(int i, int j, int w){
            weight[i][j] = w;
            weight[j][i] = w;
        }
    }
}