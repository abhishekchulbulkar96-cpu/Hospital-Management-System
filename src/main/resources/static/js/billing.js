window.onload = function () {

    loadPatients();
    loadBills();

};

// =========================
// LOAD PATIENTS
// =========================

function loadPatients() {

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

// =========================
// SAVE / UPDATE BILL
// =========================

function saveBill() {

    let bill = {

        patientId:
            document.getElementById("patient").value,

        consultationFee:
            document.getElementById("consultationFee").value,

        medicineFee:
            document.getElementById("medicineFee").value,

        billDate:
            document.getElementById("billDate").value

    };

    let id =
        document.getElementById("billId").value;

    let url = "/bills";
    let method = "POST";

    if (id) {

        url = "/bills/" + id;
        method = "PUT";

    }

    fetch(url, {

        method: method,

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(bill)

    })

        .then(response => response.json())

        .then(data => {

            alert(
                id
                    ? "Bill Updated Successfully"
                    : "Bill Generated Successfully"
            );

            document.getElementById("billId").value = "";

            document.getElementById("patient").value = "";
            document.getElementById("consultationFee").value = "";
            document.getElementById("medicineFee").value = "";
            document.getElementById("totalAmount").value = "";
            document.getElementById("billDate").value = "";

            loadBills();

        });

}

// =========================
// LOAD BILLS
// =========================

function loadBills() {

    fetch("/bills")

        .then(response => response.json())

        .then(data => {

            let tableBody =
                document.getElementById("billTableBody");

            tableBody.innerHTML = "";

            data.forEach((bill, index) => {

                tableBody.innerHTML += `

                <tr>

                    <td>${index + 1}</td>

                    <td>${bill.patient.name}</td>

                    <td>${bill.consultationFee}</td>

                    <td>${bill.medicineFee}</td>

                    <td>${bill.totalAmount}</td>

                    <td>${bill.billDate}</td>

                    <td>

                        <button
                        class="btn btn-warning btn-sm me-2"
                        onclick="editBill(
                            ${bill.billId},
                            ${bill.patient.patientId},
                            ${bill.consultationFee},
                            ${bill.medicineFee},
                            '${bill.billDate}'
                        )">

                        Edit

                        </button>

                        <button
                        class="btn btn-danger btn-sm"
                        onclick="deleteBill(${bill.billId})">

                        Delete

                        </button>
						<button
						class="btn btn-primary btn-sm"

						onclick="printBill(${bill.billId})">

						PDF

						</button>

                    </td>

                </tr>

                `;

            });

        });

}

// =========================
// AUTO TOTAL
// =========================

document.addEventListener("input", function () {

    let consultation =
        parseFloat(
            document.getElementById("consultationFee").value
        ) || 0;

    let medicine =
        parseFloat(
            document.getElementById("medicineFee").value
        ) || 0;

    document.getElementById("totalAmount").value =
        consultation + medicine;

});

// =========================
// DELETE BILL
// =========================

function deleteBill(id) {

    if (!confirm("Delete this bill?")) {
        return;
    }

    fetch("/bills/" + id, {

        method: "DELETE"

    })

        .then(response => response.text())

        .then(data => {

            alert("Bill Deleted Successfully");

            loadBills();

        });

}

// =========================
// EDIT BILL
// =========================

function editBill(
    id,
    patientId,
    consultationFee,
    medicineFee,
    billDate) {

    document.getElementById("billId").value = id;

    document.getElementById("patient").value = patientId;

    document.getElementById("consultationFee").value =
        consultationFee;

    document.getElementById("medicineFee").value =
        medicineFee;

    document.getElementById("billDate").value =
        billDate;

    document.getElementById("totalAmount").value =
        Number(consultationFee) +
        Number(medicineFee);

}



function printBill(id){

    window.open(
        "/bills/pdf/" + id,
        "_blank"
    );

}