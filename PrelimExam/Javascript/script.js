// Programmer Identifier: Lanceta, Alexander D. 23-1269-840

// Requirement: Hardcoded CSV content as a multi-line string
const csvString = `StudentID,first_name,last_name,LAB WORK 1,LAB WORK 2,LAB WORK 3,PRELIM EXAM,ATTENDANCE GRADE
073900438,Osbourne,Wakenshaw,69,5,52,12,78
114924014,Albie,Gierardi,58,92,16,57,97
111901632,Eleen,Pentony,43,81,34,36,16
084000084,Arie,Okenden,31,5,14,39,99
272471551,Alica,Muckley,49,66,97,3,95
104900721,Jo,Burleton,98,94,33,13,29
111924392,Cam,Akram,44,84,17,16,24`;

let students = [];

// Parse string into Array of Objects
function parseArchive() {
    const lines = csvString.trim().split("\n").slice(1);
    students = lines.map(line => {
        const cols = line.split(",");
        return { 
            id: cols[0], 
            name: `${cols[1]} ${cols[2]}`, 
            grade: cols[6] 
        };
    });
    render();
}

// Requirement: render() function using Template Literals
function render() {
    const tbody = document.getElementById("tableBody");
    tbody.innerHTML = ""; 

    students.forEach((student, index) => {
        // Using backticks for template literals
        const row = `
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.grade}</td>
                <td><button onclick="deleteEntry(${index})">Delete</button></td>
            </tr>`;
        tbody.innerHTML += row;
    });
}

// Requirement: push() a new object and re-render
function addEntry() {
    const id = document.getElementById("idIn").value;
    const name = document.getElementById("nameIn").value;
    const grade = document.getElementById("gradeIn").value;

    if (id && name) {
        students.push({ id, name, grade });
        render();
        // Clear inputs
        document.getElementById("idIn").value = "";
        document.getElementById("nameIn").value = "";
        document.getElementById("gradeIn").value = "";
    }
}

// Requirement: Delete specific entry from array
function deleteEntry(index) {
    students.splice(index, 1);
    render();
}

// Initial Call
parseArchive();