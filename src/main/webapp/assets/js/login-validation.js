let login_form = document.getElementById('login-validation');

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        let response = await fetch('api/students/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
            })
        });
        let result = await response.json();
        console.log(result);
        if(result["student_id"]){
            sessionStorage.setItem('id', result["student_id"]);
            location.href = "dashboard.html";
        }else{
            document.getElementById("login-alert").style.display = "block";
        }

    }
});