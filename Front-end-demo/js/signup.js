// Sử lại địa chỉ API
const saveUserAPI = "http://192.168.138.1:8080/api/v1/client/account/saveUser"

document.querySelector("#signup-btn").addEventListener("click", saveUser);

let login = document.getElementById("loginbtn").addEventListener("click", () => {
    window.location.href = "signin.html"
})
let register = document.getElementById("registerbtn").addEventListener("click", () => {
    window.location.href = "signup.html"
})

async function saveUser() {
    let name = document.querySelector("#fullname").value;
    let gmail = document.querySelector("#email").value;
    let password = document.querySelector("#password").value;
    let location = document.querySelector("#location").value;
    let phonenumber = document.querySelector("#phonenumber").value;
    let accountnumber = document.querySelector("#accountnumber").value;

    let accData = []
    let data = {
        accountnumber: accountnumber,
        gmail: gmail,
        location: location,
        name: name,
        password: password,
        phonenumber: phonenumber,
        ROLE: 1

    };

    let response = await fetch(saveUserAPI, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })

    if (response.status === 201) {
        accData.push(data);
        localStorage.setItem("accdata", JSON.stringify(accData));
        setTimeout(() => {
            alert("Đăng ký thành công")
            window.location.href = "signin.html";
        }, 0);
    } else if (response.status === 405) {
        setTimeout(() => {
            alert("Email already!")
        }, 0);
    } else {
        setTimeout(() => {
            alert("Server Error!")
        }, 0);
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