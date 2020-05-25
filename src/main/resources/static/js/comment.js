let interval = setInterval("getMessages()", 2000);

async function getMessages(){
    let response = await fetch('http://localhost:8080/messages/get')
        .catch(function (error){
        });
    if(response.ok){
        let messages = await response.json();
        for(let i = 0; i < messages.length; i++){
            let element = document.createElement('div');
            element.id = messages[i].id;
            element.innerHTML = "<p>" + messages[i].text + messages[i].user+"</p>";
            document.getElementById("messages").appendChild(element);
        }
    }
}

async function addComment(form){
    let data = new FormData(form);
    await fetch("http://localhost:8080/messages/add", {
        method: 'POST',
        body: data
    })
};

