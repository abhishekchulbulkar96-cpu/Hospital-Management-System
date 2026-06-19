/**
 * 
 */window.onload = function(){

    loadPatients();

    loadDoctors();

    loadAppointments();

};
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
function loadDoctors(){

    fetch("/doctors")

    .then(response => response.json())

    .then(data => {

        let dropdown =
            document.getElementById("doctor");

        dropdown.innerHTML =
            '<option value="">Select Doctor</option>';

        data.forEach(doctor => {

            dropdown.innerHTML +=

            `<option value="${doctor.doctorId}">
                ${doctor.name}
            </option>`;

        });

    });

}

function saveAppointment(){

    let appointment = {

        patientId:
        document.getElementById("patient").value,

        doctorId:
        document.getElementById("doctor").value,

        appointmentDate:
        document.getElementById("appointmentDate").value

    };

    fetch("/appointments",{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(appointment)

    })

    .then(response => response.json())

    .then(data => {

        alert("Appointment Booked Successfully");

        loadAppointments();

    })

    .catch(error => {

        alert("Error Booking Appointment");

    });

}


function loadAppointments(){

    fetch("/appointments")

    .then(response => response.json())

    .then(data => {

        let tableBody =
            document.getElementById(
                "appointmentTableBody"
            );

        tableBody.innerHTML="";

		data.forEach(app => {

		    tableBody.innerHTML += `

		    <tr>

		        <td>${app.appointmentId}</td>

		        <td>${app.patient.name}</td>

		        <td>${app.doctor.name}</td>

		        <td>${app.appointmentDate}</td>

		        <td>

		            <button
		                class="btn btn-warning btn-sm me-2"
		                onclick="editAppointment(
		                    ${app.appointmentId},
		                    ${app.patient.patientId},
		                    ${app.doctor.doctorId},
		                    '${app.appointmentDate}'
		                )">

		                Edit

		            </button>

		            <button
		                class="btn btn-danger btn-sm"
		                onclick="deleteAppointment(
		                    ${app.appointmentId}
		                )">

		                Delete

		            </button>
					<button
					class="btn btn-primary btn-sm me-2"
					onclick="window.open(
					'/appointments/pdf/${app.appointmentId}'
					)">

					Print Slip

					</button>

		        </td>

		    </tr>

		    `;

		});
    });

}

function deleteAppointment(id){

    if(!confirm("Delete this appointment?")){
        return;
    }

    fetch("/appointments/" + id, {

        method:"DELETE"

    })

    .then(response => response.text())

    .then(data => {

        alert("Appointment Deleted");

        loadAppointments();

    })

    .catch(error => {

        alert("Delete Failed");

    });

}
function searchAppointment(){

    let input =
        document.getElementById(
            "searchAppointment"
        ).value.toLowerCase();

    let rows =
        document.querySelectorAll(
            "#appointmentTableBody tr"
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

function editAppointment(
    id,
    patientId,
    doctorId,
    date){

    document.getElementById("appointmentId").value = id;

    document.getElementById("patient").value = patientId;

    document.getElementById("doctor").value = doctorId;

    document.getElementById("appointmentDate").value = date;
}