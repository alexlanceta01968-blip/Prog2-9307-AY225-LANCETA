/**
 * Midterm Lab 3: CSV Dataset Processing
 * Student: LANCETA, ALEXANDER D.
 * Machine Problems: MP07, MP08, MP09
 */

const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({ 
    input: process.stdin, 
    output: process.stdout 
});

rl.question('Enter dataset file path: ', (path) => {
    try {
        const cleanPath = path.replace(/['"]/g, '');
        const fileContent = fs.readFileSync(cleanPath, 'utf8');
        
        const records = fileContent.split('\n')
            // CRITICAL: Filter for lines that actually contain PASS or FAIL.
            // This skips the first 6 metadata lines AND the header automatically!
            .filter(line => line.includes('PASS') || line.includes('FAIL'))
            .map(line => {
                // Splits on commas that are NOT inside double quotes
                const p = line.split(/,(?=(?:(?:[^"]*"){2})*[^"]*$)/);
                
                // Clean the data: remove quotes and whitespace
                const name = p[0] ? p[0].replace(/"/g, '').trim() : '';
                const exam = p[3] ? p[3].trim() : 'Unknown';
                const score = parseInt(p[6] ? p[6].trim() : '0');
                const result = p[7] ? p[7].trim() : '';
                
                return { name, exam, score, result };
            });

        // MP07: Sort by Exam Name
        records.sort((a, b) => a.exam.localeCompare(b.exam));
        console.log('\n[MP07] Sorted by Exam Name:');
        records.forEach((r, i) => console.log(`${i + 1}. ${r.exam} | ${r.name}`));

        // MP08: Filter by Keyword "PASS"
        console.log('\n[MP08] Candidates who passed:');
        const passed = records.filter(r => r.result === 'PASS');
        passed.forEach(r => console.log(`- ${r.name}`));

        // MP09: Stats (Average Score)
        const total = records.reduce((sum, r) => sum + r.score, 0);
        const avg = total / records.length;
        console.log(`\n[MP09] Dataset Statistics - Average Score: ${avg.toFixed(2)}`);

    } catch (err) {
        console.error('Error processing file:', err.message);
    }
    rl.close();
});