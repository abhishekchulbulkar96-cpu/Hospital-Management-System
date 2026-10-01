/**
 * 
 */window.onload = function(){

    loadDepartments();

};

// ===================
// SAVE / UPDATE
// ===================

function saveDepartment(){

    let department = {

        deptName:
        document.getElementById(
            "deptName"
        ).value

    };

    let id =
        document.getElementById(
            "deptId"
        ).value;

    let url = "/departments";
    let method = "POST";

    if(id){

        url = "/departments/" + id;
        method = "PUT";

    }

    fetch(url,{

        method:method,

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(
            department
        )

    })

    .then(response => response.json())

    .then(data => {

        alert(
            id
            ? "Department Updated"
            : "Department Added"
        );

        document.getElementById(
            "deptId"
        ).value="";

        document.getElementById(
            "deptName"
        ).value="";

        loadDepartments();

    });

}

// ===================
// VIEW
// ===================

function loadDepartments(){

    fetch("/departments")

    .then(response => response.json())

    .then(data => {

        let tableBody =
            document.getElementById(
                "departmentTableBody"
            );

        tableBody.innerHTML="";

        data.forEach(
            (dept,index)=>{

            tableBody.innerHTML += `

            <tr>

                <td>${index+1}</td>

                <td>${dept.deptName}</td>

                <td>

                    <button
                    class="btn btn-warning btn-sm me-2"

                    onclick="editDepartment(
                        ${dept.deptId},
                        '${dept.deptName}'
                    )">

                    Edit

                    </button>

                    <button
                    class="btn btn-danger btn-sm"

                    onclick="deleteDepartment(
                        ${dept.deptId}
                    )">

                    Delete

                    </button>

                </td>

            </tr>

            `;

        });

    });

}

// ===================
// DELETE
// ===================

function deleteDepartment(id){

    if(!confirm(
        "Delete Department?"
    )){
        return;
    }

    fetch(
        "/departments/" + id,
        {
            method:"DELETE"
        }
    )

    .then(response =>
        response.text())

    .then(data => {

        alert(
            "Department Deleted"
        );

        loadDepartments();

    });

}

// ===================
// EDIT
// ===================

function editDepartment(
    id,
    name){

    document.getElementById(
        "deptId"
    ).value=id;

    document.getElementById(
        "deptName"
    ).value=name;

}

function loadDepartments(){

    fetch("/departments")

    .then(response => response.json())

    .then(data => {

        let dropdown =
            document.getElementById(
                "department"
            );

        dropdown.innerHTML =
            '<option value="">Select Department</option>';

        data.forEach(dept => {

            dropdown.innerHTML +=

            `<option value="${dept.deptId}">
                ${dept.deptName}
            </option>`;

        });

    });

}