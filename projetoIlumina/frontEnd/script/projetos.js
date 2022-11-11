window.addEventListener("load", async () => {
    if (readCookie("isLogged") == "true") {
        let token = readCookie("token");
        token = token.split("_");

        const loginToUser = document.querySelector(".navbar_right a");
        console.dir(loginToUser);
        loginToUser.attributes.href.nodeValue = "/frontEnd/perfil.html";
        loginToUser.innerHTML = `Olá, ${token[2]}`

        const parent = loginToUser.parentElement;
        const a = document.createElement("a");
        a.innerHTML = "Sair"
        a.classList.add("sair");
        a.style.cursor = "pointer";

        parent.insertAdjacentElement("beforeend", a);

        const sair = document.querySelector(".sair");
        sair.addEventListener("click", () => {
            document.cookie = "isLogged=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.replace("http://localhost:6790/frontEnd/index.html");
        })
    }

    const resp = await reqProjetos();
    console.log(resp);
    projetosContainer(resp);
})

const reqProjetos = async () => {
    const resp = await fetch("http://localhost:6791/projetos/getAll")
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => e);

    return resp;
}

const projetosContainer = (projetos) => {
    for (let i of projetos) {
        const div = document.createElement("div");
        div.classList.add("projetos");

        const img = document.createElement("img");
        img.src = `recursos/ong${randomNum(1, 13)}.jpg`

        const descricao = document.createElement("p");
        descricao.innerText = i.descricao;

        div.appendChild(img)
        div.appendChild(descricao)

        const container = document.querySelector(".containerProjetos");
        container.appendChild(div);
    }
}


function randomNum(min, max) { // min and max included 
    return Math.floor(Math.random() * (max - min + 1) + min)
}

