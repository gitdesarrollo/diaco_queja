import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Message } from "../models/message";
import { ChannelData } from "../models/channel-data";

import { WebsocketService } from "../websocket.service";
import { ChatService } from "../chat.service";
import { ActivatedRoute, ParamMap } from "@angular/router";

@Component({
  selector: "app-chat",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.css"],
  providers: [WebsocketService, ChatService],
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
  newMessage: "";
  idAud: number;

  constructor(
    private router: Router,
    private chatService: ChatService,
    private activatedRoute: ActivatedRoute
  ) {
/*     const options = {
      authProvider,
    }; */
    chatService.messages.subscribe((msg) => {
      if (msg.conexion == "1") {
        this.channelList.push({ id: 1, name: msg.autor });
        console.log("Uniendome a la sala");
      }
      if (msg.msg) {
        if (msg.audiencia == this.idAudiencia) {
          console.log("Para la sala: " + msg.audiencia);
          this.agregarMensaje(msg.autor, msg.msg);
        }
      } else {
        console.log("Solo es un mensaje de Conexión");
      }

      console.log("Response from websocket: ", msg);
    });
  }

  mensajeEscrito() {
    this.message.msg = "Mensajin";
  }

  private message = {
    autor: "tutorialedge",
    msg: "Hola",
    audiencia: "",
    conexion: "",
  };

  private messageDeUnion = {
    autor: "",
    audiencia: "",
    conexion: "1",
  };

  sendMsg() {
    this.message.msg = this.newMessage;
    this.message.audiencia = this.idAudiencia;
    console.log("new message from client to websocket: ", this.message);
    this.chatService.messages.next(this.message);
    this.newMessage = "";
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((parametros: ParamMap) => {
      this.idAudiencia = parametros.get("idAudiencia")!;
    });

    this.channel = false;
    this.channelList = [];
    this.messages = [
      {
        id: 1,
        msg: "Bienvenido a Audiencia Virtual Diaco. Esta conversación quedará grabada como evidencia de la misma. Sea breve.",
        autor: "Sistema",
        conexion: "0",
      },
    ];
    this.mensajeEscrito();

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

  agregarMensaje(autor, msg) {
    if (autor == this.username) {
      this.messages.push({ id: 0, msg: msg, autor: autor, conexion: "0" });
    } else {
      this.messages.push({ id: 1, msg: msg, autor: autor, conexion: "0" });
    }
  }

  joinChat() {
    this.message.autor = this.username;
    this.messageDeUnion.autor = this.username;
    this.messageDeUnion.audiencia = this.idAudiencia;
    console.log("Mensaje por Unirme a la audiencia: ", this.messageDeUnion);

    this.chatService.messageDeUnion.next(this.messageDeUnion);
    this.channel = true;
  }
}
