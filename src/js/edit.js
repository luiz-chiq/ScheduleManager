window.addEventListener('load', () => {
    const emailRegex = /^\w*(\-\w)?(\.\w*)?@\w*(-\w*)?\.\w{2,3}(\.\w{2,3})?$/gi;
    const phoneRegex = /^[+]\d{13}$/gi;
    const nameEl = document.getElementById("name");
    const emailEl = document.getElementById("email");
    const phoneEl = document.getElementById("phone");
    const formEl = document.querySelector("form");

    function checkNameInput() {
        return nameEl.value.trim() != "";
    }

    function checkEmailInput() {
        return emailEl.value.match(emailRegex) != null;
    }

    function checkPhoneInput() {
        return phoneEl.value.match(phoneRegex) != null;
    }

    function clearForm() {
        nameEl.value = "";
        emailEl.value = "";
        phoneEl.value = "";
    }

    function setFormData(dataObj) {
        nameEl.value = dataObj.name;
        emailEl.value = dataObj. email;
        phoneEl.value = dataObj.phone;
    }

    function getFormData() {
        if (!checkNameInput()) throw new Error("Nome inválido!");
        if (!checkEmailInput()) throw new Error("Email inválido!");
        if (!checkPhoneInput()) throw new Error("Telefone inválido!");
        return {
            name: nameEl.value,
            email: emailEl.value,
            phone: phoneEl.value
        }
    }

    function update() {
        try {
            const dataObj = getFormData();
            const selectedKey = localStorage.getItem("selectedId");
            localStorage.setItem(selectedKey, JSON.stringify(dataObj));
            localStorage.setItem("selectedId", "0");
            document.getElementById("nav").click();
            alert("Contato editado com sucesso!");
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    function load() {
        const selectedKey = localStorage.getItem("selectedId");
        if(selectedKey == "0") throw new Error("No contact was selected!")
        const dataObj = JSON.parse(localStorage.getItem(selectedKey));
        console.log(dataObj)
        setFormData(dataObj);
    }

    nameEl.addEventListener("input", (e) => {
        if (!checkNameInput()) {
            e.target.classList.add("error");
            return;
        }
        if (e.target.classList.contains("error")) e.target.classList.remove("error");
    });

    emailEl.addEventListener("input", (e) => {
        if (!checkEmailInput()) {
            e.target.classList.add("error");
            return;
        }
        if (e.target.classList.contains("error")) e.target.classList.remove("error");
    });

    phoneEl.addEventListener("input", (e) => {
        if (!checkPhoneInput()) {
            e.target.classList.add("error");
            return;
        }
        if (e.target.classList.contains("error")) e.target.classList.remove("error");
    });

    formEl.addEventListener("submit", (e) => {
        e.preventDefault();
        update();
    })

    if (typeof Storage == "undefined")
        document.body.innerHTML = "The browser don't have support to LocalStorage API!";

    load();
})