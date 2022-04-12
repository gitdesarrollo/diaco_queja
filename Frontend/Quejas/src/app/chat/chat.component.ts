import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { Message } from '../models/message';
import { ChannelData } from '../models/channel-data';

@Component({
  selector: "app-chat",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.css"],
})
export class ChatComponent implements OnInit {
  title = "angular-chat";
  channel: boolean;
  channelList: ChannelData[];
  username = "";
  socket: WebSocket;
  messages: Message[] = [];

  constructor(
    private router: Router,
  ) {}

  ngOnInit() {
    this.channel = true;
    this.channelList = [
      { id: 1, name: 'Audiencia Diaco' },
      { id: 2, name: 'Audiencia Diaco 1 ' },
      { id: 3, name: 'Audiencia Diaco 2' }
    ];
    this.messages = [
      { id: 1, msg: 'Hola'},
      { id: 2, msg: 'Hola, Qué tal?'}
    ]

    //Abrir Socket
    if (!this.socket) {
      console.log("Creando nuevo Web Socket ");
      this.socket = new WebSocket("ws://localhost:8080/Quejas/socket");
    }

    this.socket.onmessage = this.onMessage;


    //Finaliza Socket
  }

  onMessage(event) {
      
    var btnSubmit = document.getElementById("btnSubmit");

    var progress = document.getElementById("progress");
    var data = JSON.parse(event.data);

    console.log("Recibiendo datos del servidor: ", this.channel);

    

    var lblProgress = document.getElementById("lblProgress");
    if (data.value < 100) {
      //lblProgress.innerHTML = "Progress: " + data.value + "%";
    } else {
      //lblProgress.innerHTML = "Finish";
    }
  }

  formSubmit() {
    console.log("Entrando a formSubmit");
    this.socket.send('{"start":"true"}');
    //window.open("/chat");
    //this.router.navigate(["/Chat"]);
  }

  agregarMensaje(){
    this.messages.push({id:3,msg:'Nuevo mensaje'});
  }

  joinChat() {}
}
