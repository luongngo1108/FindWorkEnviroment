let work = JSON.parse(localStorage.getItem("detailwork"));
detailworkAPI = "http://192.168.138.1:8080/api/v1/detailwork/find-detailwork?id=" + work.detailworkID
typeworkAPI = "http://192.168.138.1:8080/api/v1/typework/find-typework?id=" + work.typeworkID
let authenAPI = "http://192.168.138.1:8080/api/v1/client/client/checkPermit"

async function fetchdata() {
    try {
        token = localStorage.getItem("token")

        let restw = await fetch(typeworkAPI, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                // authorization: token ? `Bearer ${token}` : ""
            }
        });
        let typework = await restw.json()

        let restdw = await fetch(detailworkAPI, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                //   authorization: token ? `Bearer ${token}` : ""
            }
        });
        let detailwork = await restdw.json()

        let reslocation = await fetch('https://provinces.open-api.vn/api/?depth=2')
        let data_location = await reslocation.json()

        renderDetails(typework, detailwork, data_location)

    } catch (error) {
        alert("Need authenticate")
        window.location.replace("signin.html")
    }
}


function renderDetails(typework, detailwork, data_location) {
    let data = document.querySelector(".data");

    let city_location = data_location.filter((item) => {
        return item.codename === work.city
    })


    let district_location = city_location[0].districts.filter((item) => {
        return item.codename === work.district
    })

    data.innerHTML = "";
    const date = new Date(detailwork.date);
    data.innerHTML = `
        <img src=${work.image} alt="">
        <h2>${work.workname}</h2>
        <p>Location : ${work.address} ${district_location[0].name} ${city_location[0].name}</p>
        <p>Date : ${date}h</p>
        <p>Hours : ${detailwork.hours}h</p>
        <p>Contact : ${detailwork.contact}</p>
        <p>Salary : ${detailwork.income}$</p>
        <p>Quantity:  ${work.involved}/${work.quantity}</p>
        <p>Type work : ${typework.nametypework}</p>
        <p>Description: ${detailwork.description}</p>
        <button> Tham gia </button>
    `
}
fetchdata();