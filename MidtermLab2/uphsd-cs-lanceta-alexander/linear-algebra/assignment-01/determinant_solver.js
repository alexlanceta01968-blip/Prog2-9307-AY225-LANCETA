/**
 * =====================================================
 * Student Name    : LANCETA, ALEXANDER D.
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : 2026-03-16
 * GitHub Repo     : https://github.com/alexlanceta01968-blip/Prog2-9307-AY225-LANCETA/tree/main
 *
 * Description:
 * This program computes the determinant of a 3x3 matrix assigned to 
 * LANCETA, ALEXANDER D. using cofactor expansion along the first row. 
 * It prints each step of the calculation and identifies singular matrices.
 * =====================================================
 */

// ── SECTION 1: Matrix Declaration ───────────────────────────────────
const matrix = [
    [1, 3, 5],
    [2, 4, 6],
    [3, 5, 7]
];

// ── SECTION 2: 2×2 Determinant Helper ───────────────────────────────
const computeMinor = (a, b, c, d) => (a * d) - (b * c);

// ── SECTION 3: Matrix Printer ────────────────────────────────────────
const printMatrix = (m) => {
    console.log("  Assigned Matrix:");
    m.forEach(row => {
        const rowString = row.map(num => num.toString().padStart(4, ' ')).join(' ');
        console.log(`  | ${rowString} |`);
    });
};

// ── SECTION 4: Step-by-Step Determinant Solver ──────────────────────
const solveDeterminant = (m) => {
    console.log("===================================================");
    console.log("  3x3 MATRIX DETERMINANT SOLVER");
    console.log("  Student: LANCETA, ALEXANDER D.");
    printMatrix(m);
    console.log("===================================================");

    const m11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    const m12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    const m13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);

    const c1 =  m[0][0] * m11;
    const c2 = -m[0][1] * m12;
    const c3 =  m[0][2] * m13;

    const det = c1 + c2 + c3;

    console.log(`\nExpanding along Row 1 (cofactor expansion):`);
    console.log(`  Step 1 — Minor M₁₁: (${m[1][1]}×${m[2][2]}) - (${m[1][2]}×${m[2][1]}) = ${m11}`);
    console.log(`  Step 2 — Minor M₁₂: (${m[1][0]}×${m[2][2]}) - (${m[1][2]}×${m[2][0]}) = ${m12}`);
    console.log(`  Step 3 — Minor M₁₃: (${m[1][0]}×${m[2][1]}) - (${m[1][1]}×${m[2][0]}) = ${m13}`);
    console.log(`\n  det(M) = ${c1} + (${c2}) + ${c3}`);
    console.log("\n===================================================");
    console.log(`  ✓  DETERMINANT = ${det}`);
    if (det === 0) console.log("  ⚠ The matrix is SINGULAR — it has no inverse.");
    console.log("===================================================");
};

solveDeterminant(matrix);