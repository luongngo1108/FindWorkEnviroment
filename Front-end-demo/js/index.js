// let topworkAPI ="https://636d633891576e19e327545a.mockapi.io/companies"
let topworkAPI = "http://192.168.138.1:8080/api/v1/work/find-top10-work"


document.getElementById("searchbtn").addEventListener("click",
    () => {
        window.location.href = "worklist.html"
    })


let login = document.getElementById("loginbtn").addEventListener("click", () => {
    window.location.href = "signin.html"
})
let register = document.getElementById("registerbtn").addEventListener("click", () => {
    window.location.href = "signup.html"
})

//fetching
let realData = []
async function fetchdata() {
    try {
        let token = localStorage.getItem("token")
        console.log(token)
        
        let res = await fetch(`${topworkAPI}`, {
            method: "GET",
              headers: {
                  "Content-Type": "application/json",
                //   authorization: token ? `Bearer ${token}` : ""
              }
          });
        let data = await res.json()

        renderData2(data)
    } catch (error) {
        alert(error)
    }
}


//Search Section 
let companyobj = [{
    name: "Hồ Chí Minh >",
    img: "https://cdn.haitrieu.com/wp-content/uploads/2022/07/Thanh-pho-Ho-Chi-Minh-1.png"

}, {
    name: "Hà nội >",
    img: "https://cdn.haitrieu.com/wp-content/uploads/2022/06/Logo-Ha-Noi.png"
},
{
    name: "Đà Nẵng >",
    img: "https://cdn.haitrieu.com/wp-content/uploads/2022/06/Logo-Thanh-Pho-Da-Nang.png"
},
{
    name: "Châu Đốc >",
    img: "https://cdn.haitrieu.com/wp-content/uploads/2022/09/Logo-Thanh-Pho-Chau-Doc-1024x1024.png"
},
{
    name: "Bình Định >",
    img: "https://cdn.haitrieu.com/wp-content/uploads/2022/08/Logo-Tinh-Binh-Dinh.png"
},
{
    name: "Bình Dương >",
    img: "https://cdn.haitrieu.com/wp-content/uploads/2022/06/Logo-Binh-Duong.png"
}]

rendercompanycarddata(companyobj)

function rendercompanycarddata(arr) {
    let renderCard = document.querySelector("#jobcard")
    arr.forEach(element => {
        let card = document.createElement("div")
        card.classList.add("companydatacard")
        card.addEventListener("click", () => {
            window.location.href = "companies.html"
        })
        let name = document.createElement("h4");
        name.innerText = element.name;
        //console.log(name)
        let image = document.createElement("img");
        image.setAttribute("src", element.img)

        card.append(image, name)
        renderCard.append(card)
        // console.log()


    })
}

// Top Job
let featurecompany = document.querySelector("#featureslide");
function renderData2(comData) {
    // render data
    featurecompany.innerHTML = "";
    featurecompany.innerHTML = comData.map((item) => {
        return `
            <div class="combox" data-id=${item.workID}>
                <div>
                    <img
                    // Type Job Avatar (Create Obj Store Img Order By Type Job)
                        src=${item.image}
                        alt="image"
                    />
                </div>
                <div>
                    <h3>${item.workname}</h3>
                    <p>Địa điểm: ${item.location}</p>
                    <p>Thời gian: ${item.date}</p>
                    <p>Số lượng: ${item.involved}/${item.quantity}</p>
                </div>
            </div>
        `
    }).join(" ");
    // company detail
    let comboxes = document.querySelectorAll(".combox");
    for (let combox of comboxes) {
        combox.addEventListener("click", (event) => {
            for (let i = 0; i < comData.length; i++) {
                if (String(comData[i].workID) === combox.dataset.id) {
                    console.log(JSON.stringify(comData[i]))
                    localStorage.setItem("detailwork", JSON.stringify(comData[i]));
                    window.location.href = "com_detail.html";
                }
            }
        });
    }
}

// function render_featurecard(arr){
//     let featurecard = document.querySelector("#featureslide");
//     arr.forEach(element=>{
//         let card = document.createElement("div");
//         let img = document.createElement("image");
//         img.setAttribute("src",element.avatar)
//         let subcard = document.createElement("div")

//         let name = document.createElement("h4");
//         name.innerText=element.companyName;
//         console.log(name)
//         let rating = document.createElement("p");
//         rating.innerText = element.rating;
//         console.log(rating)
//         let review = document.createElement("p");
//         review.innerText = "Reviews"
//         let desc = docuument.createElement("p");
//         desc.innerText=element.description
//         let job = document.createElement("button");
//         job.innerText="View Job"

//         subcard.append(name,rating,review,desc,job)
//         card.append(img,subcard)
//         featurecard.append(card)

//     })
// }

//Job Across Popular roles 

//Sponsered Companies  

//Interview Prepration Section  

//Grow your Career through Learning  

//Video Profile  

//Footer  

// testing

fetchdata();

function renderdata(arr) {
    let renderCard = document.querySelector("#searchsection")
    arr.forEach(element => {
        let card = document.createElement("div")
        let name = document.createElement("h3");
        name.innerText = element.companyName;
        //console.log(name)
        let image = document.createElement("img");
        image.setAttribute("src", element.avatar)

        card.append(name, image)
        renderCard.append(card)
        // console.log()


    })
}