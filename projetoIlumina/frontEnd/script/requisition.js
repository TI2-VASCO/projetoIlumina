// DISPLAY NAME OF USER 
window.addEventListener("load", async () => {
    if (readCookie("isLogged") == "true") {
        const form = document.querySelector("#formDoador");

        let token = readCookie("token");
        if (token[token.indexOf("_") + 1] == "0") {
            const form = document.querySelector(".form-doador")
            form.classList.remove("hiddenForm");

        } else if (token[token.indexOf("_") + 1] == "1") {
            const form = document.querySelector(".form-instituicoes")
            form.classList.remove("hiddenForm");
        }

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
});

const sair = document.querySelector(".sair");
console.dir(sair);

// DISPLAY INFO OF USER IN PROFILE PAGE
window.addEventListener("load", async () => {

    if (window.location.href.indexOf("perfil.html") > -1) {
        let token = readCookie("token");
        const res = await reqInfo(token);

        if (token[token.indexOf("_") + 1] == "0") {
            const form = document.querySelector("#formDoador");

            form[1].value = res.nome
            form[2].value = res.email
            form[3].value = res.senha
            form[4].value = res.cpf
            form[5].value = res.telefone
            form[6].value = res.cep
            form[7].value = res.endereco

        } else if (token[token.indexOf("_") + 1] == "1") {
            const form = document.querySelector("#formInstituicao");
            form[1].value = res.nome
            form[2].value = res.email
            form[3].value = res.senha
            form[4].value = res.cnpj
            form[5].value = res.telefone
            form[6].value = res.cep
            form[7].value = res.endereco
            form[8].value = res.descricao
        }
    }
})


async function reqInfo(key) {

    const resp = await fetch(`http://localhost:6791/user/${key}`)
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => { return e })

    return resp;
}

//DISPLAY PROFILE DOATIONS
window.addEventListener("load", async () => {
    const doacoesContainer = document.querySelector(".container-doacoes");

    if (window.location.href.indexOf("perfil.html") > -1) {
        const token = readCookie("token");
        const res = await reqDoacoes(token);

        for (let i of res) {
            console.log(i.valor)
            const doacoes = document.createElement("div");
            doacoes.classList.add("doacoes");

            const doacoesInfo = document.createElement("p");
            doacoesInfo.classList.add("doacoesInfo");
            doacoesInfo.innerText = `Código: #${i.id} / Valor: R$${i.valor} / Data: ${i.data}`;

            doacoes.insertAdjacentElement("beforeend", doacoesInfo);
            doacoesContainer.insertAdjacentElement("beforeend", doacoes);
        }
    }
})

async function reqDoacoes(key) {
    const resp = await fetch(`http://localhost:6791/user/doacoes/${key}`)
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => { return e });

    return resp;
}


//FUNCTION TO GET COOKIES VALUE
function readCookie(name) {
    name += '=';
    for (var ca = document.cookie.split(/;\s*/), i = ca.length - 1; i >= 0; i--)
        if (!ca[i].indexOf(name))
            return ca[i].replace(name, '');
}


//FUNCTION TO DISPLAY IMAGE 
const fileBtn = document.querySelector("#img");
const img = document.querySelector("#displayImg")

fileBtn.addEventListener("change", () => {
    let reader = new FileReader();
    reader.readAsDataURL(fileBtn.files[0]);
    console.log(fileBtn.files[0])

    reader.addEventListener("load", () => {
        img.setAttribute("src", reader.result)
    })

})

//FUNCTION TO CHANGE BUTTON E TO CHANGE ACTION OF FORM
const btnEditar = document.querySelectorAll(".btnEditar");

for (let i of btnEditar) {
    i.addEventListener("click", (e) => {
        if (i.innerText == "Editar") {
            e.preventDefault();
            const allInput = document.querySelectorAll(".inputs input");

            for (let j of allInput) {
                j.removeAttribute("readonly");
                j.style.backgroundColor = "white"
                i.innerText = "Salvar";
            }

        } else {
            const token = readCookie("token");
            if (token[token.indexOf("_") + 1] == "0") {
                const formUpdate = document.querySelector("#formDoador")
                formUpdate.attributes.action.nodeValue += token;

            } else if (token[token.indexOf("_") + 1] == "1") {
                const formUpdate = document.querySelector("#formInstituicao")
                formUpdate.attributes.action.nodeValue += token;
            }
        }
    })
}

//DELETE ACCOUNT
const deleteBtn = document.querySelector("#deleteBtn");
deleteBtn.addEventListener("click", () => {
    const deleteForm = document.querySelector("#deleteForm");
    const token = readCookie("token");
    deleteForm.attributes.action.nodeValue += token;
    document.cookie = "isLogged=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
})
