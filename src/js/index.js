window.addEventListener("load", () => {
    const mainEl = document.querySelector("main");
    function initDataStorageIfEmpty() {
        if(localStorage.length != 0) return;
        localStorage.clear();
        localStorage.setItem("selectedId","0");
        localStorage.setItem("idCount", "4");
        const data = [
            {
                name : "Maria da Silva",
                email: "maria.silva@email.com",
                phone: "+551699999999"
            },
            {
                name : "José da Carvalho",
                email: "jose.carvalho@email.com",
                phone: "+551699999991"
            },
            {
                name : "Luiz Silveira",
                email: "luiz.silveira@email.com",
                phone: "+551699999992"
            },
            {
                name : "Josefa Pereira",
                email: "josefa.pereira@email.com",
                phone: "+551699999993"
            }

        ]
        data.forEach((item, index) => localStorage.setItem((index+1).toString(), JSON.stringify(item)));
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
        mainEl.append(container);
    }

    function populate() {
        let key = 1;
        while (key < localStorage.length-1) {
            createItem(key, JSON.parse(localStorage.getItem(key.toString())));
            key++;
        }
    }  

    if (typeof Storage !== "undefined") {
        initDataStorageIfEmpty();
        console.log(localStorage);
        populate();
    } else {
        document.body.innerHTML = "The browser don't have support to LocalStorage API!"
    }
});