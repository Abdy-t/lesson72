let interval = setInterval("getMessages()", 1000);

async function getMessages(){
    let elements = document.getElementById("messages");
    let lastMessage = elements.lastElementChild;
    let last_id = lastMessage.id;
    let response = await fetch('http://localhost:8080/messages/get' + last_id)
        .catch(function (error){
        });
    if(response.ok){
        let messages = await response.json();
            for(let i = 0; i < messages.length; i++){
                if(messages[i].id > last_id){
                    console.log(messages);
                    let element = document.createElement('div');
                    element.classList.add("my-class");
                    element.id = messages[i].id;
                    element.innerHTML = "<p>" + "Email " + messages[i].user.email + " time " + messages[i].date + "SMS: " + messages[i].text + "</p>";
                    document.getElementById("messages").appendChild(element);
                }
            }

    }
}
async function addComment(form){
    let data = new FormData(form);
    await fetch("http://localhost:8080/messages/add", {
        method: 'POST',
        body: data
    })
}

