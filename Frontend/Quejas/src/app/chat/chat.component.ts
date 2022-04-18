import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { Message } from '../models/message';
import { ChannelData } from '../models/channel-data';

import { WebsocketService } from '../websocket.service';
import { ChatService } from '../chat.service';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: "app-chat",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.css"],
  providers: [ WebsocketService, ChatService ]
})
export class ChatComponent implements OnInit {
  idAudiencia!: string;
  title = "angular-chat";
  channel: boolean;
  channelList: ChannelData[];
  mensaje: String;
  username = "";
  socket: WebSocket;
  messages: Message[] = [];
  newMessage: '';
  idAud: number;
  

  constructor(private router: Router,private chatService: ChatService, private activatedRoute: ActivatedRoute) {
    chatService.messages.subscribe(msg => {   
      this.agregarMensaje(msg.autor,msg.msg);      
      console.log("Response from websocket: " , msg);
    });
  }

mensajeEscrito(){
  this.message.msg = 'Mensajin'
}


private message = {
    autor: 'tutorialedge',
    msg: 'Hola',
    audiencia: ''
}

private message1 = {
  msg: 'Prueba de mensaje recibido de chat'
}

sendMsg() {
    this.message.msg = this.newMessage;
    this.message.audiencia = this.idAudiencia;
    console.log('new message from client to websocket: ', this.message1);
    this.chatService.messages.next(this.message);
    this.newMessage = '';
}


  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((parametros: ParamMap) => {
      this.idAudiencia = parametros.get("idAudiencia")!;
    })


    this.channel = false;
    this.channelList = [
      { id: 1, name: 'Audiencia Diaco' }
    ];
    this.messages = [
      { id: 1, msg: 'Bienvenido a Audiencia Virtual Diaco. Esta conversación quedará grabada como evidencia de la misma. Sea breve.', autor: 'Sistema'},
    ]
    this.mensajeEscrito()

    //Abrir Socket
/*     if (!this.socket) {
      console.log("Creando nuevo Web Socket ");
      this.socket = new WebSocket("ws://localhost:8080/Quejas/socket");
      

      console.log("Socket creado: ",this.socket);

    }
    this.socket.onmessage = this.onMess;
 */
    


    //Finaliza Socket
  }

/*   onMess(event) {
    console.log("Entrando a onMessage: ",this.socket);

    if (this.socket) {
      console.log("Si hay socket(onMessage): ",this.socket);
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
      
  } */

/*   formSubmit() {
    console.log("Entrando a formSubmit");
    if (this.socket) {
    console.log("Si hay socket(formSubmit): ",this.socket);
    this.socket.send('{"start":"true"}');
    //window.open("/chat");
    //this.router.navigate(["/Chat"]);
    }
  } */

  agregarMensaje(autor,msg){
    if (autor == this.username) {
      this.messages.push({id:0,msg:msg,autor:autor});
    } else{
      this.messages.push({id:1,msg:msg,autor:autor});
    }
  }

  joinChat() {
    this.message.autor = this.username;
    this.channel = true;
  }
}
