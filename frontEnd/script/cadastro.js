// CHANGE OPTION
const typeUserOptions = document.querySelectorAll(".type-user-option input");

for (let i of typeUserOptions) {
    i.addEventListener("click", changeTypeOptions);
}

function changeTypeOptions() {
    const instituicaoType = document.querySelectorAll(".option-all");
    const [option1, option2] = instituicaoType;


    if (this.value === "0") {
        if (!option2.classList.contains("hiden-form")) {
            option2.classList.add("hiden-form");
        }
        option1.classList.remove("hiden-form");
    } else {
        if (!option1.classList.contains("hiden-form")) {
            option1.classList.add("hiden-form");
        }
        option2.classList.remove("hiden-form");
    }
}

//----------------------------------------------

//CEP HYPHEN
const allCEPInput = document.querySelectorAll(".CEPInput");

for (let i of allCEPInput) {
    i.addEventListener("input", addHyphen);
}

function addHyphen() {
    if (this.value.length == 6) {
        if (this.value.indexOf("-") == -1) {
            this.value = this.value.slice(0, 5) + "-" + this.value.slice(5);
        }
    }

}

//--------------------------------------------------



//CEP API
//https://cdn.apicep.com/file/apicep/[cep].json

for (let i of allCEPInput) {
    i.addEventListener("blur", cepComplete);
}

function reqCep(cep) {
    return fetch(`https://cdn.apicep.com/file/apicep/${cep}.json`)
        .then((e) => e.json())
        .then((e) => e)
        .catch((e) => "erro: " + e)
}

async function cepComplete() {
    const cepResult = await reqCep(this.value);


    const address = `${cepResult.address}, ${cepResult.district}`;

    if (address.indexOf("undefined, undefined") == -1) {
        const enderecoContainer = document.querySelectorAll(".endereco");

        for (let i of enderecoContainer) {
            if (!i.parentElement.classList.contains("hiden-form")) {
                i.childNodes[3].value = address;
            }
        }

    }

}