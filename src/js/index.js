window.addEventListener("load", () => {
    const contacts = document.getElementById("contacts");
    function initDataStorageIfEmpty() {
        if (localStorage.length > 2) return;
        localStorage.clear();
        localStorage.setItem("selectedId", "0");
        localStorage.setItem("idCount", "4");
        const data = [
            {
                name: "Maria da Silva",
                email: "maria.silva@email.com",
                phone: "+551699999999"
            },
            {
                name: "José da Carvalho",
                email: "jose.carvalho@email.com",
                phone: "+551699999991"
            },
            {
                name: "Luiz Silveira",
                email: "luiz.silveira@email.com",
                phone: "+551699999992"
            },
            {
                name: "Josefa Pereira",
                email: "josefa.pereira@email.com",
                phone: "+551699999993"
            }

        ]
        data.forEach((item, index) => localStorage.setItem((index + 1).toString(), JSON.stringify(item)));
    }


    function createItem(key, dataObj) {
        const container = document.createElement("div");
        const p1 = document.createElement("p");
        const p2 = document.createElement("p");
        const p3 = document.createElement("p");
        const span = document.createElement("span");
        const delBtn = document.createElement("button");
        const editBtn = document.createElement("button");
        delBtn.innerText = "Deletar";
        delBtn.classList.add("del-btn");
        delBtn.addEventListener("click", (e) => {
            const id = e.target.parentNode.id.toString();
            const check = confirm(`Tem certeza que quer deletar o contato de ${dataObj.name}`)
            if (check) {
                localStorage.removeItem(id)
                alert(`O contato de ${dataObj.name} foi deletado com sucesso!`)
                populate();
            };

        })
        editBtn.innerText = "Editar";
        editBtn.classList.add("edit-btn");
        container.classList.add("item");
        container.setAttribute("id", key.toString());
        span.innerText = dataObj.name;
        p1.append("Nome: ");
        p1.append(span);
        p2.append(`Email: ${dataObj.email}`);
        p3.append(`Telefone: ${dataObj.phone}`);
        container.append(p1);
        container.append(p2);
        container.append(p3);
        container.append(editBtn);
        container.append(delBtn);
        contacts.append(container);
    }

    function populate() {
        contacts.innerHTML = "";
        const keys = Object.keys(localStorage);
        console.log(keys)
        keys.forEach(key => {
            const num = Number.parseInt(key);
            if(typeof num == "number" && !isNaN(num)) {
                console.log(Number.parseInt(key))
                const data = localStorage.getItem(key.toString());
                createItem(key, JSON.parse(data));
            }
        });
    }

    if (typeof Storage !== "undefined") {
        initDataStorageIfEmpty();
        console.log(localStorage);
        populate();
    } else {
        document.body.innerHTML = "The browser don't have support to LocalStorage API!"
    }
});