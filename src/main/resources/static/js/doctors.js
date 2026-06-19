window.onload = function () {

    loadDepartments();
    loadDoctors();

};

function loadDepartments() {

    fetch("/departments")

        .then(response => response.json())

        .then(data => {

            let dropdown =
                document.getElementById("department");

            dropdown.innerHTML =
                '<option value="">Select Department</option>';

            data.forEach(dept => {

                dropdown.innerHTML += `

                <option value="${dept.deptId}">
                    ${dept.deptName}
                </option>

                `;

            });

        });

}
function saveOrUpdateDoctor() {

    let doctor = {

        name:
        document.getElementById("doctorName").value,

        specialization:
        document.getElementById("specialization").value,

        deptId:
        document.getElementById("department").value

    };

    fetch("/doctors", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(doctor)

    })

    .then(response => response.json())

    .then(data => {

        alert("Doctor Saved Successfully");

        loadDoctors();

        document.getElementById("doctorName").value = "";
        document.getElementById("specialization").value = "";
        document.getElementById("department").value = "";

    })

    .catch(error => {

        alert("Save Failed");

    });

}
function loadDoctors() {

    fetch("/doctors")
    .then(response => response.json())
    .then(data => {

        console.log(data);

        let tableBody =
            document.getElementById("doctorTableBody");

        tableBody.innerHTML = "";

        data.forEach(doctor => {

            tableBody.innerHTML +=
            "<tr>" +
            "<td>" + doctor.doctorId + "</td>" +
            "<td>" + doctor.name + "</td>" +
            "<td>" + doctor.specialization + "</td>" +
			"<td>" +
			(doctor.department
			    ? doctor.department.deptName
			    : "No Department")
			+
			"</td>" +

			"<td>" +

			"<button class='btn btn-warning btn-sm me-2' " +
			"onclick='editDoctor(" +
			doctor.doctorId +
			",\"" + doctor.name +
			"\",\"" + doctor.specialization +
			"\"," +
			(doctor.department
			 ? doctor.department.deptId
			 : "null") +
			")'>" +

			"Edit" +

			"</button>" +

			"<button class='btn btn-danger btn-sm' " +
			"onclick='deleteDoctor(" + doctor.doctorId + ")'>" +

			"Delete" +

			"</button>" +

			"</td>"
			"</tr>";
        });

    });

}
function deleteDoctor(id){

    if(!confirm("Delete this doctor?")){
        return;
    }

    fetch("/doctors/" + id, {

        method:"DELETE"

    })

    .then(response => response.text())

    .then(data => {

        alert("Doctor Deleted");

        loadDoctors();

    })

    .catch(error => {

        alert("Delete Failed");

    });

}
function searchDoctor(){

    let input =
        document.getElementById("searchDoctor")
        .value
        .toLowerCase();

    let rows =
        document.querySelectorAll(
            "#doctorTableBody tr"
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

function editDoctor(id,name,specialization,deptId){

    document.getElementById("doctorId").value=id;

    document.getElementById("doctorName").value=name;

    document.getElementById("specialization").value=specialization;

    document.getElementById("department").value=deptId;
}