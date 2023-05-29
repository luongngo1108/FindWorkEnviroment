let login = document.getElementById("loginbtn").addEventListener("click",()=>{
  window.location.href="signin.html"
})
let register = document.getElementById("registerbtn").addEventListener("click",()=>{
  window.location.href="signup.html"
})
let landingPage = document.getElementById("dashboardlogo").addEventListener("click",()=>{
  window.location.href="index.html"
})

let domain = "http://192.168.138.1:8080"
let findAllWorkAPI = domain + "/api/v1/work/find-all-work";
let findAllDetailWorkAPI = domain + "/api/v1/detailwork/find-all-detailwork"

let companyData = document.querySelector(".companyList>div");

let arr_work = [];
let arr_detail = [];
// fetch company data
async function fetchData(pageNumber = 1) {
  try {
    // render work data
    let token = localStorage.getItem("token")
    let res_work = await fetch(`${findAllWorkAPI}?page=${pageNumber}&limit=14`, {
      method: "GET",
        headers: {
            "Content-Type": "application/json",
            // authorization: token ? `Bearer ${token}` : ""
        }
    });
    let data_work = await res_work.json();
    arr_work = data_work;
        
    // render detail work data
    let res_detail = await fetch(`${findAllDetailWorkAPI}`, {
      method: "GET",
        headers: {
            "Content-Type": "application/json",
            // authorization: token ? `Bearer ${token}` : ""
        }
    });
    let data_detail = await res_detail.json();
    arr_detail = data_detail;
    
    renderData(arr_work);

    let totalPage = 5;
    paginationData(totalPage);
  } catch (err) {
    console.log("Error Occurred");
  }
}
fetchData();

function renderData(workObj) {
  // render data
  companyData.innerHTML = "";
  companyData.innerHTML = workObj
    .map((item) => {

      // lấy ra detail work của mỗi công việc
      let detailwork = arr_detail.filter((item1) => {
        return item1.detailworkID === item.detailworkID
      })

      return `
            <div class="combox" data-id=${item.workID}>
                <div>
                    <img
                        src=${item.image}
                        alt="image"
                    />
                </div>
                <div>
                    <h3>${item.workname}</h3>
                    <p>Income: $${detailwork[0].income}</p>
                    <p>Hours: ${detailwork[0].hours}h</p>
                    <p>Quantity: ${item.involved}/${item.quantity}</p>
                    <p>Date: ${item.date}</p>
                </div>
            </div>
        `;
    })
    .join(" ");

  // company detail
  let comboxes = document.querySelectorAll(".combox");
  for (let combox of comboxes) {
      combox.addEventListener("click", (event) => {
          for (let i = 0; i < workObj.length; i++) {
              if (String(workObj[i].workID) === combox.dataset.id) {
                  localStorage.setItem("detailwork", JSON.stringify(workObj[i]));
                  window.location.href = "com_detail.html";
              }
          }
      });
  }
}

// filterPart
let inputJobButtons = document.querySelectorAll("#jobType input");
for (let inputButton of inputJobButtons) {
  inputButton.addEventListener("click", (event) => {
    inputButton.setAttribute("checked", false);
    if (inputButton.checked) {
      inputButton.setAttribute("checked", true);
    }
    
    let inputValue = inputButton.value;
    let filteredComp = arr_work.filter((item) => {
      return item.typeworkID === Number(inputValue);
    });

    if (inputButton.checked) {
      renderData(filteredComp);
    } else {
      renderData(arr_work);
    }
  });
}

// filter location
let inputLocationButtons = document.querySelectorAll("#location input");
for (let inputButton of inputLocationButtons) {
  inputButton.addEventListener("click", (event) => {
    inputButton.setAttribute("checked", false);
    if (inputButton.checked) {
      inputButton.setAttribute("checked", true);
    }

    let inputValue = inputButton.value;
    let filteredComp = arr_work.filter((item) => {
      return item.city === inputValue;
    });

    if (inputButton.checked) {
      renderData(filteredComp);
    } else {
      renderData(arr_work);
    }
  });
}

let inputIncomeButtons = document.querySelectorAll("#income input");
for (let inputButton of inputIncomeButtons) {
  inputButton.addEventListener("click", (event) => {
    inputButton.setAttribute("checked", false);
    if (inputButton.checked) {
      inputButton.setAttribute("checked", true);
    }

    let inputValue = inputButton.value;
    let filteredComp = arr_detail.filter((item) => {
      if (inputValue === "50") {
        return item.income <= 50
      } else if (inputValue === "100") {
        return 50 < item.income &&  item.income <= 100
      } else if (inputValue === "200") {
        return 100 < item.income && item.income <= 200
      } else if (inputValue === "201") {
        return item.income > 200
      }
    });

    // Có danh sách detail work cần chuyển lại thành một danh sách work
    let flag = false
    let worklist = arr_work.filter((work) => {
      flag = false
      filteredComp.forEach((detail) => {
        if (work.detailworkID === detail.detailworkID) {
          flag = true
        }
      })
      return flag
    })

    if (inputButton.checked) {
      renderData(worklist);
    } else {
      renderData(arr_work);
    }
  });
}

// Pagination part
function paginationData(totalPage) {
  let paginationSection = document.querySelector(".pagination");
  paginationSection.innerHTML = `
    ${createPagButton(totalPage).join(" ")}
  `;

  let pageButtons = document.querySelectorAll(".pagination-btn");
  for (let pageButton of pageButtons) {
    pageButton.addEventListener("click", (event) => {
      let pageNumber = event.target.dataset.id;
      // let limti = 16;
      fetchData(pageNumber);
    });
  }
}

function createPagButton(totalPage) {
  let array = [];
  for (let page = 1; page <= totalPage; page++) {
    array.push(
      `<div class="pagination-btn" ${page ? `data-id = ${page}` : ""}><h4 ${
        page ? `data-id = ${page}` : ""
      }>${page}</h4></div>`
    );
  }
  return array;
}

// hiringPart.nextElementSibling.children[0].classList.toggle("active");

let hiringParts = document.querySelectorAll(".hiring-part h3");

// for(let i=0; i<hiringParts.length; i++) {
//   hiringParts[i].addEventListener("click",function(event){
//     let main = hiringParts[i].nextElementSibling;

//     if(main.style.display == "grid") {
//       main.style.display = "none";
//     } else {
//       main.style.display = "grid";
//     }
//   })
// }

for (let i = 0; i < hiringParts.length; i++) {
  hiringParts[0].nextElementSibling.style.display = "block";
  hiringParts[0].style.borderBottom = "4px solid orange"
  hiringParts[i].addEventListener("click", function () {
    if (i == 0) {
      hiringParts[0].style.borderBottom = "4px solid orange"
      hiringParts[1].style.borderBottom = "none"
      hiringParts[2].style.borderBottom = "none"
      hiringParts[0].nextElementSibling.style.display = "block";
      hiringParts[1].nextElementSibling.style.display = "none";
      hiringParts[2].nextElementSibling.style.display = "none";
    } 
    else if (i == 1) {
      hiringParts[1].style.borderBottom = "4px solid orange"
      hiringParts[0].style.borderBottom = "none"
      hiringParts[2].style.borderBottom = "none"
      hiringParts[1].nextElementSibling.style.display = "block";
      hiringParts[0].nextElementSibling.style.display = "none";
      hiringParts[2].nextElementSibling.style.display = "none";
    } 
    else if (i == 2) {
      hiringParts[2].style.borderBottom = "4px solid orange"
      hiringParts[0].style.borderBottom = "none"
      hiringParts[1].style.borderBottom = "none"
      hiringParts[2].nextElementSibling.style.display = "block";
      hiringParts[0].nextElementSibling.style.display = "none";
      hiringParts[1].nextElementSibling.style.display = "none";
    }
  });
}
