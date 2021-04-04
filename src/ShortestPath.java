import java.util.Random;
import java.util.Scanner;

public class ShortestPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("정점의 개수 : ");
        int n = scanner.nextInt();
        Graph g = new Graph(n);
        System.out.print("간선의 개수 : ");
        int edge = scanner.nextInt();

        int num = 0;
        for(int i = 0; i<edge; i++) {
            int x = num;
            if(num>n-1){
                x = random.nextInt(n);
            }
            int y = random.nextInt(n);
            if(x == y){
                while(x==y){
                    y = random.nextInt(n);
                }
            }
            int z = random.nextInt(20) + 1;
            System.out.println(x+" "+y+" "+z);
            g.value(x,y,z);
            num++;
        }

        System.out.print("시작 정점 : ");
        int v = scanner.nextInt();
        dijkstra(g, v);
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

        // 모든 정점을 방문하면서 최단 경로 찾기
        for(int j = 0; j<g.n-1; j++){
            int min = Integer.MAX_VALUE;
            int min_pos = -1;

            //방문하지 않은 정점 중에서 최단 거리의 정점 고르기
            for(int i = 0; i<g.n; i++){
                if(distance[i] < min && !visit[i]){
                    min = distance[i]; // 최단 거리
                    min_pos = i; // 최단 거리의 인덱스(정점)
                }
            }

            visit[min_pos] = true; // 선택된 정점은 방문 표시해줌
            for(int i = 0; i<g.n; i++){
                if(!visit[i] && g.weight[min_pos][i]!=0) // 방문하지 않은 정점일 때만
                    if(distance[min_pos] + g.weight[min_pos][i] < distance[i]) // 선택된 정점을 통한 거리(distance 값)가 원래 거리보다 짧으면
                        distance[i] = distance[min_pos] + g.weight[min_pos][i]; // 인접 정점의 distance 값 업데이트
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