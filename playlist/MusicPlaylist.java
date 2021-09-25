package bytestax.practice.playlist;

import java.util.List;
import java.util.Objects;

public class MusicPlaylist {

    public static class Pair {
        final int song1;
        final int song2;

        public Pair(int song1, int song2) {
            this.song1 = song1;
            this.song2 = song2;
        }

        public int getSong1() {
            return song1;
        }

        public int getSong2() {
            return song2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return (getSong1() == pair.getSong1() && getSong2() == pair.getSong2()) || (getSong2() == pair.getSong1() && getSong1() == pair.getSong2());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getSong1(), getSong2());
        }
    }
    public static long playlist(List<Integer> songs, List<Pair> pairs) {
        int n = songs.size();
        System.out.println("Size is " + n);
        long ans = 0;
        int[] d = new int[1001];
        for(int i=1; i<=1000; i++){
            d[i] = 0;
        }

        for(int i=0; i<n; i++){
            ++d[songs.get(i)];
        }

        for(int i=1; i<=1000; i++){
            if (d[i] == 0) {
                continue;
            }

            if  ((i % 60) ==0 || ((i+i) % 60) ==0){
                System.out.println( "i is " + i + " d[i] is " + d[i]);
                int c = d[i];
                c = ((c * (c-1)) / 2);
                ans += c;
                System.out.println("c is " + c + " ans is " + ans);
                for (int k = 0; k < c; k++) {
                    pairs.add(new Pair(i, i));
                }
            }

            if  ((i % 60) ==0){
                System.out.println( "i is " + i + " d[i] is " + d[i]);
                int c = d[i];
                c = ((c * (c-1)) / 2);
                ans += c;
                System.out.println("c is " + c + " ans is " + ans);
                for (int k = 0; k < c; k++) {
                    pairs.add(new Pair(i, i));
                }
            }
            for (int j = i+1; j<=1000 ; j++){
                if (d[j] == 0)
                    continue;

                if ((i+j) % 60 == 0){
                    System.out.println( "i is " + i + " d[i] is " + d[i]);
                    System.out.println("j is " + j + " d[j] is " + d[j]);
                    int c =0;
                    if ((i+j) % 60 == 0) {
                        c += (d[i] * d[j]);
                        ans += c;
                        System.out.println("c is " + c + " ans is " + ans);
                        for (int l = 0; l < d[i]; l++) {
                            for (int m = 0; m < d[j]; m++) {
                            pairs.add(new Pair(i, j));
                        }
                        }
                    }

                }
            }
        }

        return ans;

    }

    public static long playlistBruteForce(List<Integer> songs, List<Pair> pairs) {
        int n = songs.size();
        System.out.println("Size is " + n);
        long ans = 0;

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if ((songs.get(i) + songs.get(j)) % 60 == 0){
                    ans += 1;
                    pairs.add(new Pair(songs.get(i), songs.get(j)));
                }
            }
        }

        return ans;

    }

}
