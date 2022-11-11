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
    const idInstituicao = localStorage.getItem("instituicaoClicked");
    const resp = await reqInstituicao(idInstituicao);
    containerInstituto(resp);

    const resp2 = await reqProjetos(idInstituicao);
    console.log(resp2);
    projetosContainer(resp2);
})

const reqInstituicao = async (key) => {
    const resp = await fetch(`http://localhost:6791/instituicao/${key}`)
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => e);

    return resp;
}

const reqProjetos = async (key) => {
    const resp = await fetch(`http://localhost:6791/projetos/instituicao/${key}`)
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => e);

    return resp;
}

const containerInstituto = (instituto) => {
    const nome_Instituto = document.querySelector(".nome_Instituto");
    const imagem_instituo = document.querySelector(".imagem_instituo");
    const descricao_instituo = document.querySelector(".descricao_instituo");
    const info_instituto = document.querySelector(".info_instituto");

    nome_Instituto.innerText = instituto.nome
    imagem_instituo.src = `recursos/ong${randomNum(1, 12)}.jpg`
    descricao_instituo.innerText = instituto.descricao;
    info_instituto.children[0].innerText += " " + instituto.endereco
    info_instituto.children[1].innerText += " " + instituto.telefone
    info_instituto.children[2].innerText += " " + instituto.email

}

const projetosContainer = (projetos) => {
    for (let i of projetos) {
        const div = document.createElement("div");
        div.classList.add("projetos");

        const img = document.createElement("img");
        img.src = projetos.imagem

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
