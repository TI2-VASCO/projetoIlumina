window.addEventListener("load", async () => {
    if (readCookie("isLogged") != "true") {
        window.location.replace("http://localhost:6790/frontEnd/login.html");

    } else {
        const resp = await reqAllInstituicao();
        createOptions(resp);

        const donateForm = document.querySelector("#donateForm");
        donateForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const date = new Date();
            const currentDate = `${date.getDate()}-${date.getMonth()}-${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;

            const token = readCookie("token");


            const valor = document.querySelector("#valor").value;
            const resp = await reqDonate(token, valor, currentDate);
            if (resp.status == "true") {
                alert("Obrigado pela doação");
                window.location.replace("http://localhost:6790/frontEnd/perfil.html")
            } else {
                alert("Erro ao realizar a doação");
            }
        })
    }
})

async function reqDonate(key, valor, date) {
    const params = new URLSearchParams({
        valor: valor,
        data: date
    })

    const resp = await fetch(`http://localhost:6791/doacao/cadastro/${key}?` + params)
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => { return e })

    return resp;
}

async function reqAllInstituicao() {
    const resp = await fetch("http://localhost:6791/instituicao/getAll")
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => { return e })


    return resp
}

function createOptions(instituicao) {
    const select = document.querySelector("#instituicao_options");

    for (let i of instituicao) {
        const option = document.createElement("option");
        option.innerText = i.nome;
        option.value = i.id;

        select.appendChild(option);
    }
}


