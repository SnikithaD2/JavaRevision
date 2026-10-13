public class BinaryExponentiation {

    static final long MOD = 1_000_000_007L;

    // 1. Binary Exponentiation: Calculate (A^B) % M.
    // Time: O(log B), Space: O(log B) due to recursion.
    public long modMultiply(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

    public long power(long A, long B, long M) {
        if (B == 0) return 1 % M;
        if (B == 1) return A % M;

        long half = power(A, B / 2, M);
        long result = modMultiply(half, half, M);

        if (B % 2 == 1) {
            result = modMultiply(result, A % M, M);
        }

        return result;
    }

    // 2. Matrix Exponentiation: Calculate matrix A raised to power n.
    // Time: O(m^3 log n), Space: O(m^2 log n) due to recursion.
    public int[][] matrixPower(int n, int m, int[][] a) {
        if (n == 0) return identity(m);
        if (n == 1) return a;

        int[][] half = matrixPower(n / 2, m, a);
        int[][] squared = product(half, half, m);

        if (n % 2 == 0) return squared;

        return product(squared, a, m);
    }

    // Create an identity matrix of size m x m.
    public int[][] identity(int m) {
        int[][] result = new int[m][m];

        for (int i = 0; i < m; i++) {
            result[i][i] = 1;
        }

        return result;
    }

    // Multiply two m x m matrices modulo MOD.
    public int[][] product(int[][] a, int[][] b, int m) {
        int[][] result = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    result[i][j] = addM(
                        result[i][j],
                        multiM(a[i][k], b[k][j])
                    );
                }
            }
        }

        return result;
    }

    // Modular addition.
    public int addM(int a, int b) {
        return (int) (((long) a + b) % MOD);
    }

    // Modular multiplication.
    public int multiM(int a, int b) {
        return (int) (((long) a * b) % MOD);
    }
}
