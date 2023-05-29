let domain = "http://192.168.138.1:8080"
let createDetailAPI = domain + "/api/v1/client/detailwork/createDetail"
let createWorkAPI = domain + "/api/v1/client/work/createWork"
let uploadFileAPI = domain + "/api/v1/upload/uploadfile"
let createPaymentAPI = domain + "/api/v1/payment/save-payment"
let findAllTypeWork = domain + "/api/v1/typework/find-all-type"
let authenAPI = domain + "/api/v1/client/client/checkPermit"

function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}


let dataId = localStorage.getItem("editId") || null
if (dataId == null) {
    let edit_form = document.querySelector(".main")
    edit_form.innerHTML = ""
    let add_form = document.querySelector(".main")

    add_form.innerHTML = `
    <form action="" method="POST" class="form" id="edit_form">
    <h3 class="heading">Tạo công việc mới</h3>
    <p class="desc">Cùng nhau tạo thật nhiều việc để giúp ích cho xã hội ❤️</p>

    <div class="spacer"></div>

    <div class="form-group" id="comp_nme">
        <label for="compmyName" class="form-label">Job name: </label>
        <input id="name" name="name" rules="required" type="text" placeholder="Enter Job name here..." class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-group" id="comp_lgo">
        <label for="companylogo" class="form-label">Date: </label>
        <input id="date" name="date" rules="required" type="date" placeholder="Enter URL of logo here..." class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-group" id="comp_cntct">
        <label for="contact" class="form-label">Contact: </label>
        <input id="contact" name="contact" rules="required|max:12" type="number" placeholder="Enter Company contact here..." class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-group" id="comp_lct">
        <label for="location" class="form-label">Thành phố: </label>
        <select id="locationThanhPho" name="jobrole">

        </select>
    </div>

    <div class="form-group" id="comp_lct">
        <label for="location" class="form-label">Quận/Huyện: </label>
        <select id="locationQuanHuyen" name="jobrole">

        </select>
    </div>

    <div class="form-group" id="comp_lct">
        <label for="location_detail" class="form-label">Địa chỉ cụ thể: </label>
        <input id="location_detail" name="location_detail" rules="required" type="text" placeholder="Enter location detail here..." class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-group" id="comp_lct">
        <label for="hours" class="form-label">Hours: </label>
        <input id="hours" name="hours" rules="required" type="number" value="1" class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-group" id="comp_exp">
        <label for="experience" class="form-label">Quantity: </label>
        <input id="quantity" name="quantity" rules="required" type="number" value="1" class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-group" id="job_rle">
        <label for="typework" class="form-label">Type Job : </label>
        <select id="typework" name="typework">
        </select>
    </div>
    
    <div class="form-group" id="comp_slry">
        <label for="income" class="form-label">Income: </label>
        <span id="income" name="income" class="form-control"></span>
    </div>

    <div class="form-group" id="comp_desc">
        <p><label for="description" class="form-label">Description : </label></p>
        <textarea name="desc" id="desc" cols="30" rows="10" placeholder="Enter Company description here..." required></textarea>
    </div>

    <div class="form-group" id="comp_lct">
        <label for="image" class="form-label">Image: </label>
        <input id="image" name="image" rules="required" accept="image/*" type="file" placeholder="" class="form-control">
        <span class="form-message"></span>
    </div>

    <div class="form-button" id="button">
        <button id="save" class="form-submit">Tạo</button>
        <button id="cancel" class="form-cancel"><a href="./admin_page.html"> Thoát</a></button>
    </div>
    
</form>
   `

    //Dùng để create
    let save_add_btn = document.querySelector("#save")
    save_add_btn.addEventListener("click", (event) => {
        event.preventDefault()
        let job_name = document.querySelector("#name")
        let date = document.querySelector("#date")
        let contact = document.querySelector("#contact")
        let quantity = document.querySelector("#quantity")
        let income = document.querySelector("#income")
        let hours = document.querySelector("#hours")
        let type = document.querySelector("#typework")
        let desc = document.querySelector("#desc")
        let image = document.querySelector("#image")

        // location
        let city = document.querySelector("#locationThanhPho")
        let district = document.querySelector("#locationQuanHuyen")
        let address = document.querySelector("#location_detail")

        let detailObj = {
            contact: contact.value,
            description: desc.value,
            hours: hours.value,
            income: income.value
        }
        
        let workObj = {
            date: date.value,
            quantity: quantity.value,
            workname: job_name.value,
            typeworkID: type.value,
            involved: 0,
            city: city.value,
            district: district.value,
            address: address.value
        }

        imgObj = {

        }
        addtoserver(detailObj, workObj, imgObj)
    })
}

async function getDataTypeWork() {
    const response = await fetch(findAllTypeWork)
    const data = await response.json();
    length = data.length;
    var temp = "";
    for (i = 0; i < length; i++) {
        temp += "<option";
        temp += " value='" + data[i].typeworkID + "'>";
        temp += data[i].nametypework + "</option>";
    }

    document.getElementById("typework").innerHTML = temp;
}

async function setDataIncome() {
    let hours = document.getElementById("hours");
    let quantity = document.getElementById("quantity");
    let typework = document.getElementById("typework");

    const response = await fetch(findAllTypeWork, {
        method: "GET",
          headers: {
              "Content-Type": "application/json",
          }
      });
    const data = await response.json();
    length = data.length;
    var temp = 0;
    var typework_select = 0;
    for (i = 0; i < length; i++) {
        if (typework.value == data[i].typeworkID) {
            typework_select = i;
            break;
        }
    }

    temp = temp + data[typework_select].price * hours.value * quantity.value;

    document.getElementById("income").innerHTML = temp.toString() + ' VNĐ';
}

async function addtoserver(detailObj, workObj, imgObj) {
    try {
        paymentObj = {
            time: null,
            paymentmethodID: 1
        }
        token = localStorage.getItem("token")
        console.log(token)

        // Tạo payment
        let create_payment = await fetch(createPaymentAPI, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                authorization: token ? `Bearer ${token}` : ""
            },
            body: JSON.stringify(paymentObj)
        })

        // lấy ra id payment vừa tạo đưa vào detailwork
        payment = (await create_payment.json())
        detailObj['paymentID'] = payment.paymentID

        //Tạo detail work
        let add_detail = await fetch(createDetailAPI, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                authorization: token ? `Bearer ${token}` : ""
            },
            body: JSON.stringify(detailObj)
        })


        // lấy ra id detail vừa tạo
        detailworkID = (await add_detail.json())
        workObj["detailworkID"] = detailworkID

        //Tạo work
        let add_work = await fetch(createWorkAPI, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                authorization: token ? `Bearer ${token}` : ""
            },
            body: JSON.stringify(workObj)
        })




        if (add_detail.ok && add_work.ok) {
            alert("Data Added Successfully")
        } else {
            alert("Data not added.\nPlease Try Again")
        }
    } catch (error) {
        alert("Need authenticate")
        window.location.replace("signin.html")
    }
}

window.addEventListener("load", () => {
    if (dataId != null) {
        editData(dataId)
        let edit_strt_btn = document.querySelector("#edit_pge_btn")
        setTimeout(() => {
            edit_strt_btn.innerText = "Start Editing"
        }, 1000);
        edit_strt_btn.innerText = "Fetching Data..."
    }
})

// let edit_strt_btn=document.querySelector("#edit_pge_btn")
// edit_strt_btn.addEventListener("click",(event)=>{
//     setTimeout(() => {
//         edit_strt_btn.innerText="In Process..."
//         editData(dataId)
//     }, 1000);
//     edit_strt_btn.innerText="Fetching Data..."

// })

async function editData(id) {
    try {
        let edit_data = await fetch(`https://636d633891576e19e327545a.mockapi.io/companies/${id}`);
        if (edit_data.ok) {
            let temp = await edit_data.json()
            showdata(temp)
        } else {

        }
    } catch (error) {
        alert("Bad request")
    }
}

function showdata(data) {
    let cname = document.querySelector("#company_name")
    cname.value = data["companyName"]
    cname.readOnly = true
    let clogo = document.querySelector("#company-logo")
    clogo.value = data["avatar"]
    clogo.readOnly = true;
    let crole = document.querySelector("#company_job_role")
    crole.value = data["jobRole"]
    crole.readOnly = true
    let ccontact = document.querySelector("#company_contact")
    ccontact.value = data["contact"]
    ccontact.readOnly = true
    let cexp = document.querySelector("#company_exp")
    cexp.value = data["experience"]
    cexp.readOnly = true
    let csalary = document.querySelector("#company_salary")
    csalary.value = data["Salary"]
    csalary.readOnly = true
    let cloc = document.querySelector("#company_loca")
    cloc.value = data["location"]
    cloc.readOnly = true
    let cdesc = document.querySelector("#company_desc")
    cdesc.value = data["description"]
    cdesc.readOnly = true
}

let temp_data = document.querySelector("#edit_pge_btn")
if (dataId != null) {
    temp_data.addEventListener("click", (event) => {
        temp_data.innerText = "Editing In Process..."
        let cname = document.querySelector("#company_name")
        cname.readOnly = false
        let clogo = document.querySelector("#company-logo")
        clogo.readOnly = false
        let crole = document.querySelector("#company_job_role")
        crole.readOnly = false
        let ccontact = document.querySelector("#company_contact")
        ccontact.readOnly = false
        let cexp = document.querySelector("#company_exp")
        cexp.readOnly = false
        let csalary = document.querySelector("#company_salary")
        csalary.readOnly = false
        let cloc = document.querySelector("#company_loca")
        cloc.readOnly = false
        let cdesc = document.querySelector("#company_desc")
        cdesc.readOnly = false
    })
}

//Dùng để Edit
let save_data = document.querySelector("#save")
if (dataId != null) {
    save_data.addEventListener("click", (event) => {
        let temp_data = document.querySelector("#edit_pge_btn")
        temp_data.innerText = "Start Editing"
        event.preventDefault()
        let data = {}
        let cname = document.querySelector("#company_name")
        data["companyName"] = cname.value
        let clogo = document.querySelector("#company-logo")
        data["avatar"] = clogo.value;
        let crole = document.querySelector("#company_job_role")
        data["jobRole"] = crole.value;
        let ccontact = document.querySelector("#company_contact")
        data["contact"] = ccontact.value;
        let cexp = document.querySelector("#company_exp")
        data["experience"] = cexp.value;
        let csalary = document.querySelector("#company_salary")
        data["Salary"] = csalary.value;
        let cloc = document.querySelector("#company_loca")
        data["location"] = cloc.value;
        let cdesc = document.querySelector("#company_desc")
        data["description"] = cdesc.value
        cname.readOnly = true
        clogo.readOnly = true;
        crole.readOnly = true
        ccontact.readOnly = true
        cexp.readOnly = true
        csalary.readOnly = true
        cloc.readOnly = true
        cdesc.readOnly = true
        finalData(data)
    })
}



async function finalData(obj) {
    try {
        let edit_fetch = await fetch(`https://636d633891576e19e327545a.mockapi.io/companies/${dataId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(obj)
        })
        if (edit_fetch.ok) {
            alert("Data edited and saved succefully!")
        } else {
            alert("Edited data not saved.")
        }
    } catch (error) {
        alert("Bad Request")
    }
}

function Validator(formSelector) {

    var _this = this;
    var formRules = {};

    function getParent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    };

    /**
     * Quy ước tạo rules:
     * - Nếu có lỗi thì return error messages
     * - Nếu không có lỗi thì return undefined
     */
    var validatorRules = {
        required: function(value) {
            return value ? undefined : 'Vui lòng nhập trường này';
        },
        email: function(value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Trường này phải là email';
        },
        min: function(min) {
            return function(value) {
                return value.length >= min ? undefined : `Vui lòng nhập ít nhất ${min} kí tự`;
            }
        },
        max: function(max) {
            return function(value) {
                return value.length <= max ? undefined : `Vui lòng nhập tối đa ${max} kí tự`;
            }
        }
    };

    // Lấy ra element trong DOM theo formSelector
    var formElement = document.querySelector(formSelector);

    // Chỉ xử lý khi có element trong DOM
    if (formElement) {
        var inputs = formElement.querySelectorAll('[name][rules]');
        for (var input of inputs) {
            var rules = input.getAttribute('rules').split('|');
            for (var rule of rules) {
                var isRuleHasValue = rule.includes(':');
                var ruleInfo;

                if (isRuleHasValue) {
                    ruleInfo = rule.split(':');
                    rule = ruleInfo[0];
                }

                var ruleFunc = validatorRules[rule];

                if (isRuleHasValue) {
                    ruleFunc = ruleFunc(ruleInfo[1]);
                }

                if (Array.isArray(formRules[input.name])) {
                    formRules[input.name].push(ruleFunc);
                } else {
                    formRules[input.name] = [ruleFunc];
                }
            };

            // Lắng nghe sự kiện để validate (blur, change, ...)
            input.onblur = handleValidate;
            input.oninput = handleClearValidate;
        };

        // Hàm thực hiện validate
        function handleValidate(event) {
            var rules = formRules[event.target.name];
            var errorMessage;

            for (var rule of rules) {
                errorMessage = rule(event.target.value);
                if (errorMessage) {
                    break;
                }
            }

            // Nếu có lỗi thì hiển thị message lỗi ra UI
            if (errorMessage) {
                var formGroup = getParent(event.target, '.form-group');
                if (formGroup) {
                    formGroup.classList.add('invalid');
                    var formMessage = formGroup.querySelector('.form-message');
                    if (formMessage) {
                        formMessage.innerHTML = errorMessage;
                    }
                }
            }

            return !errorMessage;
        };

        // Hàm clear messages lỗi
        function handleClearValidate(event) {
            var formGroup = getParent(event.target, '.form-group');
            if (formGroup.classList.contains('invalid')) {
                formGroup.classList.remove('invalid');
                var formMessage = formGroup.querySelector('.form-message');
                if (formMessage) {
                    formMessage.innerHTML = '';
                }
            }
        };
    }

    // Xử lý hành vi submit form
    formElement.onsubmit = function(event) {
        event.preventDefault();

        var inputs = formElement.querySelectorAll('[name][rules]');
        var isValid = true;
        for (var input of inputs) {
            if (!handleValidate({ target: input })) {
                isValid = false;
            };
        }

        // Khi không có lỗi thì submit form
        if (isValid) {
            if (typeof _this.onSubmit === 'function') {
                var enableInputs = formElement.querySelectorAll('[name]');
                var formValues = Array.from(enableInputs).reduce(function(values, input) {
                    switch (input.type) {
                        case 'checkbox':
                            if (!input.matches(':checked')) {
                                values[input.name] = '';
                                return values;
                            }
                            if (!Array.isArray(values[input.name])) {
                                values[input.name] = [];
                            }
                            values[input.name].push(input.value);
                            break;
                        case 'radio':
                            values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                            break;
                        case 'file':
                            values[input.name] = input.files;
                            break;
                        default:
                            values[input.name] = input.value;
                    }
                    return values;
                }, {});

                // Gọi lại hàm onSubmit và trả về kèm giá trị của form
                _this.onSubmit(formValues);
            } else {
                formElement.submit();
            }
        }
    };
}