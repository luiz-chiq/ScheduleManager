window.addEventListener('load', () => {
    const emailRegex = /^ $/i;
    const phoneRegex = /^ $/i;
    const nameEl = document.getElementById("name");
    const emailEl = document.getElementById("email");
    const phoneEl = document.getElementById("phone");
    const formEl = document.querySelector("form");

    function checkNameInput() {
        return nameEl.value != "";
    }

    function checkEmailInput() {
        return emailEl.value != "" && emailEl.value.match(emailRegex) != null;
    }

    function checkPhoneInput() {
        return phoneEl.value != "" && phoneEl.value.match(phoneRegex) != null;
    }

    
    


    function getFormData() {

        if (!checkNameInput()) throw new Error("Name input is an invalid value!");
        if (!checkPhoneInput()) throw new Error("Phone input is an invalid value!");
        if (!checkEmailInput()) throw new Error("Phone input is an invalid value!");
        
        return {
            name : nameEl.value,
            email: emailEl.value,
            phone: phoneEl.value
        }
    }

    function save() {

    }

    function update() {

    }

    if (typeof Storage !== "undefined") {

    }
})