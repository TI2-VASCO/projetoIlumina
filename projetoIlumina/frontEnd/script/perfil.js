window.addEventListener("load", async () => {
    let token = readCookie("token");
    const resp = await reqProjetos(token[0]);
    console.log(resp);
    projetosContainer(resp);
})

const reqProjetos = async (key) => {
    const resp = await fetch(`http://localhost:6791/projetos/instituicao/${key}`)
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

//FUNCTION TO GET COOKIES VALUE
function readCookie(name) {
    name += '=';
    for (var ca = document.cookie.split(/;\s*/), i = ca.length - 1; i >= 0; i--)
        if (!ca[i].indexOf(name))
            return ca[i].replace(name, '');
}

const btnProjeto = document.querySelector(".btnProjeto");

btnProjeto.addEventListener("click", (e) => {
    const projetoForm = document.querySelector(".formProjeto");

    if (btnProjeto.innerText == "Criar Projeto") {
        e.preventDefault();
        btnProjeto.innerText = "Salvar";
        projetoForm.classList.remove("hiddenForm");
    } else {
        projetoForm.action += readCookie("token")[0];
        console.log(projetoForm.action)
    }
})

function randomNum(min, max) { // min and max included 
    return Math.floor(Math.random() * (max - min + 1) + min)
}