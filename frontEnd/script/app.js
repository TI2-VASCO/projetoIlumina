//responsive NAVBAR

const navBars = document.querySelector(".fa-bars");
const containerLink = document.querySelector(".nav_links_left");
const nav = document.querySelector("nav");

const donateLink = document.createElement("a");
donateLink.innerText = "Quero Doar";
donateLink.href = "/donate.html";
donateLink.classList.toggle("donate_link");

navBars.addEventListener("click", () => {
    if (!navBars.classList.contains("fa-x")) {
        navBars.classList.toggle("fa-x");
        navBars.classList.toggle("fa-bars");
        containerLink.insertAdjacentElement("afterbegin", donateLink);
        containerLink.classList.toggle("nav_links_left_responsive");
        document.body.classList.toggle("stop_scroll");
        nav.style.backgroundColor = "rgb(0,0,0)"
    } else {
        containerLink.removeChild(donateLink);
        navBars.classList.toggle("fa-x");
        navBars.classList.toggle("fa-bars");
        containerLink.classList.toggle("nav_links_left_responsive");
        document.body.classList.toggle("stop_scroll");
        nav.style.backgroundColor = "rgba(0,0,0, 0.5)";
    }
})

//------------------------------------

// NAV FADE 

window.addEventListener("scroll", () => {
    if (window.scrollY > 10) {
        nav.style.backgroundColor = "rgba(0,0,0,0.9)";

    } else {
        nav.style.backgroundColor = "rgba(0,0,0,0.4)";
    }
})

//-------------------------------------
