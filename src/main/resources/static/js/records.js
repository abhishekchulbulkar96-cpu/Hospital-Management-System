/**
 * 
 */
window.onload = function(){

    loadPatients();

    loadRecords();

};

// =====================
// LOAD PATIENTS
// =====================

function loadPatients(){

    fetch("/patients")

    .then(response => response.json())

    .then(data => {

        let dropdown =
            document.getElementById("patient");

        dropdown.innerHTML =
            '<option value="">Select Patient</option>';

        data.forEach(patient => {

            dropdown.innerHTML +=

            `<option value="${patient.patientId}">
                ${patient.name}
            </option>`;

        });

    });

}

// =====================
// SAVE RECORD
// =====================
function saveRecord(){

    let record = {

        patientId:
        document.getElementById("patient").value,

        diagnosis:
        document.getElementById("diagnosis").value,

        treatment:
        document.getElementById("treatment").value,

        prescription:
        document.getElementById("prescription").value,

        visitDate:
        document.getElementById("visitDate").value

    };

    let id =
        document.getElementById(
            "recordId"
        ).value;

    let url="/records";
    let method="POST";

    if(id){

        url="/records/"+id;
        method="PUT";

    }

    fetch(url,{

        method:method,

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(record)

    })

    .then(response => response.json())

    .then(data => {

        alert(
            id
            ? "Record Updated"
            : "Record Saved"
        );

        loadRecords();

    });

}
// =====================
// VIEW RECORDS
// =====================

function loadRecords(){

    fetch("/records")

    .then(response => response.json())

    .then(data => {

        let tableBody =
            document.getElementById(
                "recordTableBody"
            );

        tableBody.innerHTML="";

        data.forEach((record,index)=>{

            tableBody.innerHTML += `

            <tr>

                <td>${index+1}</td>

                <td>${record.patient.name}</td>

                <td>${record.diagnosis}</td>

                <td>${record.treatment}</td>

                <td>${record.prescription}</td>

                <td>${record.visitDate}</td>

				<td>

				<button
				class="btn btn-warning btn-sm me-2"

				onclick="editRecord(
				${record.recordId},
				${record.patient.patientId},
				'${record.diagnosis}',
				'${record.treatment}',
				'${record.prescription}',
				'${record.visitDate}'
				)">

				Edit

				</button>

				<button
				class="btn btn-danger btn-sm"

				onclick="deleteRecord(
				${record.recordId}
				)">

				Delete

				</button>

				</td>

            </tr>

            `;

        });

    });

}

// =====================
// SEARCH
// =====================

function searchRecord(){

    let input =
        document.getElementById(
            "searchRecord"
        ).value.toLowerCase();

    let rows =
        document.querySelectorAll(
            "#recordTableBody tr"
        );

    rows.forEach(row => {

        let text =
            row.innerText.toLowerCase();

        row.style.display =
            text.includes(input)
            ? ""
            : "none";

    });

}


function editRecord(
id,
patientId,
diagnosis,
treatment,
prescription,
visitDate){

document.getElementById(
"recordId"
).value=id;

document.getElementById(
"patient"
).value=patientId;

document.getElementById(
"diagnosis"
).value=diagnosis;

document.getElementById(
"treatment"
).value=treatment;

document.getElementById(
"prescription"
).value=prescription;

document.getElementById(
"visitDate"
).value=visitDate;

}


function deleteRecord(id){

if(!confirm(
"Delete Record?"
)){
return;
}

fetch(
"/records/"+id,
{
method:"DELETE"
}
)

.then(response =>
response.text())

.then(data => {

alert(
"Record Deleted"
);

loadRecords();

});

}