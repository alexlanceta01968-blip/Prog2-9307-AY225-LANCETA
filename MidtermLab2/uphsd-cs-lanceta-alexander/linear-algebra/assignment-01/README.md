# Assignment 01: 3x3 Matrix Determinant Solver

**Student:** LANCETA, ALEXANDER D.  
**Course:** Math 101 — Linear Algebra  
**School:** University of Perpetual Help System DALTA, Molino Campus  
**Date:** March 16, 2026

## Overview
This assignment demonstrates the implementation of a 3×3 matrix determinant solver using cofactor expansion. It includes a Java implementation and a JavaScript (Node.js) implementation, both designed to output step-by-step calculations as required by the assignment rubric.

## Assigned Matrix
Assigned to LANCETA, ALEXANDER D.:

| 1 | 3 | 5 |
|---|---|---|
| 2 | 4 | 6 |
| 3 | 5 | 7 |

## How to Run

### For Java Implementation
1. Ensure that you have JDK installed.
2. Open your terminal in the `Prog2-9307-AY225-LANCETA/MidtermLab2/linear-algebra/assignment-01/` directory.
3. Run the program. (But if not working, I recommend you to do "javac DeterminantSolver.java" just in case and assure that its on its own terminal, then command with "java DeterminantSolver" as the final.)


#### For Javascript Implementation
1. Ensure you have Node.js installed.
2. Open its terminal in the linear-algebra/assignment-01/ directory.
3. Run the script with: node determinant_solver.js

##### For the sample output: This output will look like this in both terminals.
===================================================
  3x3 MATRIX DETERMINANT SOLVER
  Student: LANCETA, ALEXANDER D.
  Assigned Matrix:
  | 1  3  5  |
  | 2  4  6  |
  | 3  5  7  |
===================================================

Expanding along Row 1 (cofactor expansion):
  Step 1 — Minor M₁₁: (4×7) - (6×5) = -2
  Step 2 — Minor M₁₂: (2×7) - (6×3) = -4
  Step 3 — Minor M₁₃: (2×5) - (4x3) = -2

  det(M) = -2 + (12) + -10

===================================================
  ✓  DETERMINANT = 0
  ⚠ The matrix is SINGULAR — it has no inverse.
===================================================