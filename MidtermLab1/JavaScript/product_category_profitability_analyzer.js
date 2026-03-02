const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askFilePath() {
    rl.question("Enter dataset file path (vgchartz-2024.csv): ", (path) => {
        if (fs.existsSync(path) && fs.lstatSync(path).isFile()) {
            processCSV(path);
        } else {
            console.log("Invalid file path. Please try again.");
            askFilePath();
        }
    });
}

function processCSV(path) {
    const data = fs.readFileSync(path, 'utf8');
    const lines = data.split('\n');
    
    const salesMap = {};
    const countMap = {};

    // Start from 1 to skip header
    for (let i = 1; i < lines.length; i++) {
        const line = lines[i].trim();
        if (!line) continue;

        // Regex to handle commas inside quotes
        const columns = line.split(/,(?=(?:(?:[^"]*"){2})*[^"]*$)/);

        if (columns.length >= 9) {
            const category = columns[3].trim(); // Genre
            const sales = parseFloat(columns[7].trim()) || 0; // Total Sales

            salesMap[category] = (salesMap[category] || 0) + sales;
            countMap[category] = (countMap[category] || 0) + 1;
        }
    }

    displayResults(salesMap, countMap);
}

function displayResults(sales, counts) {
    let mostProfitable = "";
    let leastProfitable = "";
    let maxS = -1;
    let minS = Infinity;

    console.log("\n--- Category Analytics ---");
    for (const cat in sales) {
        const total = sales[cat];
        const avg = total / counts[cat];

        console.log(`Category: ${cat.padEnd(15)} | Total: ${total.toFixed(2).padStart(10)} | Avg: ${avg.toFixed(2).padStart(8)}`);

        if (total > maxS) {
            maxS = total;
            mostProfitable = cat;
        }
        if (total < minS) {
            minS = total;
            leastProfitable = cat;
        }
    }

    console.log(`\nMost Profitable Category: ${mostProfitable}`);
    console.log(`Least Profitable Category: ${leastProfitable}`);
    rl.close();
}

askFilePath();