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

