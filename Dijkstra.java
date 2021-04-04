import java.util.Scanner;

public class Dijkstra  {

    private static void dijkstra(int[][] ad) {
        int v = ad.length;
        boolean visited[] = new boolean[v];
        int distance[] = new int[v];
        distance[0] = 0;
        for(int i = 1; i < v; i++){
            distance[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < v - 1; i++){
            // 최소거리 찾기
            int minVertex = findMin(distance, visited);
            visited[minVertex] = true;
            for(int j = 0; j < v; j++){
                if(ad[minVertex][j] !=0 && distance[minVertex] != Integer.MAX_VALUE) {
                    int newDist = distance[minVertex] + ad[minVertex][j];
                    if(newDist < distance[j]){
                        distance[j] = newDist;
                    }
                }
            }
        }

        for(int i = 0; i < v; i++){
            System.out.println(i + " "+ distance[i]);
        }
    }
    private static int findMin(int[] distance, boolean visited[]) {

        int minVertex = -1;
        for(int i = 0; i < distance.length; i++){
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])){
                minVertex = i;
            }
        }
        return minVertex;
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("정점의 개수 : ");
        int v = s.nextInt();
        System.out.print("간선의 개수 : ");
        int e = s.nextInt();
        int ad[][] = new int[v][v];
        for(int i = 0; i < e; i++){
            int v1 = s.nextInt(v);
            int v2 = s.nextInt(v);
            int weight = s.nextInt();
            ad[v1][v2] = weight;
            ad[v2][v1] = weight;
        }
        dijkstra(ad);
    }
}

