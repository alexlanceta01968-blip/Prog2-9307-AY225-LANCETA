/**
 * =====================================================
 * Student Name    : LANCETA, ALEXANDER D.
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : 2026-03-16
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-lanceta-alexander
 *
 * Description:
 * This program computes the determinant of a 3x3 matrix assigned to 
 * LANCETA, ALEXANDER D. using cofactor expansion along the first row. 
 * It prints each step of the calculation and identifies singular matrices.
 * =====================================================
 */
public class DeterminantSolver {

    // ── SECTION 1: Matrix Declaration ───────────────────────────────────
    static int[][] matrix = {
        {1, 3, 5},
        {2, 4, 6},
        {3, 5, 7}
    };

    // ── SECTION 2: 2×2 Determinant Helper ───────────────────────────────
    public static int computeMinor(int a, int b, int c, int d) {
        return (a * d) - (b * c);
    }

    // ── SECTION 3: Matrix Printer ────────────────────────────────────────
    public static void printMatrix(int[][] m) {
        System.out.println("  Assigned Matrix:");
        for (int[] row : m) {
            System.out.print("  | ");
            for (int val : row) System.out.print(val + "  ");
            System.out.println("|");
        }
    }

    // ── SECTION 4: Step-by-Step Determinant Solver ──────────────────────
    public static void solveDeterminant(int[][] m) {
        System.out.println("===================================================");
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: LANCETA, ALEXANDER D.");
        printMatrix(m);
        System.out.println("===================================================");

        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);

        int c1 =  m[0][0] * minor11;
        int c2 = -m[0][1] * minor12;
        int c3 =  m[0][2] * minor13;

        int det = c1 + c2 + c3;

        System.out.println("\nExpanding along Row 1 (cofactor expansion):");
        System.out.println("  Step 1 — Minor M₁₁: (" + m[1][1] + "×" + m[2][2] + ") - (" + m[1][2] + "×" + m[2][1] + ") = " + minor11);
        System.out.println("  Step 2 — Minor M₁₂: (" + m[1][0] + "×" + m[2][2] + ") - (" + m[1][2] + "×" + m[2][0] + ") = " + minor12);
        System.out.println("  Step 3 — Minor M₁₃: (" + m[1][0] + "×" + m[2][1] + ") - (" + m[1][1] + "×" + m[2][0] + ") = " + minor13);
        System.out.println("\n  det(M) = " + c1 + " + (" + c2 + ") + " + c3);
        System.out.println("\n===================================================");
        System.out.println("  ✓  DETERMINANT = " + det);
        if (det == 0) System.out.println("  ⚠ The matrix is SINGULAR — it has no inverse.");
        System.out.println("===================================================");
    }

    public static void main(String[] args) {
        solveDeterminant(matrix);
    }
}