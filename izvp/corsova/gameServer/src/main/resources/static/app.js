const url = "ws://80.77.43.117:8080/stomp";
const client = new StompJs.Client({
    brokerURL: url
});

let buttonConnect, buttonDisConnect, buttonSend, conversationDisplay, greetings, topicInput, messageInput, subscribeButton;
let currentSubscription = null;

client.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
};

client.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

client.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    buttonConnect.disabled = connected;
    buttonDisConnect.disabled = !connected; 
    conversationDisplay.style.display = connected ? "block" : "none";
    greetings.innerHTML = "";
}

function connect() {
    client.activate();
    console.log('Connected');
}

function disconnect() {
    client.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    const destinationTopic = topicInput.value;
    const message = messageInput.value;
    client.publish({
        destination: destinationTopic,
        body: message
    });
    console.log(`Sent message to ${destinationTopic}: ${message}`);
}

function subscribeToTopic() {
    const topic = topicInput.value;
    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }
    currentSubscription = client.subscribe(topic, (message) => {
        showGreeting(message.body);
    });
    console.log(`Subscribed to topic: ${topic}`);
}

function showGreeting(message) {
    greetings.innerHTML += `<tr><td>${message}</td></tr>`;
}

document.addEventListener("DOMContentLoaded", function() {
    buttonConnect = document.getElementById("connect");
    buttonDisConnect = document.getElementById("disconnect");
    buttonSend = document.getElementById("send");
    conversationDisplay = document.getElementById("conversation");
    greetings = document.getElementById("greetings");
    topicInput = document.getElementById("topic");
    messageInput = document.getElementById("message");
    subscribeButton = document.getElementById("subscribe");
    
    buttonConnect.addEventListener("click", (e) => {
        connect();
        e.preventDefault();
    });
    buttonDisConnect.addEventListener("click", (e) => {
        disconnect();
        e.preventDefault();
    });
    buttonSend.addEventListener("click", (e) => {
        sendMessage();
        e.preventDefault();
    });
    subscribeButton.addEventListener("click", (e) => {
        subscribeToTopic();
        e.preventDefault();
    });
    setConnected(false);
});
