import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { WebsocketService } from "./websocket.service";

//const CHAT_URL = 'ws://localhost:8080/Quejas/socket';
//const CHAT_URL = 'wss://dev.mineco.gob.gt:80/Quejas/socket';
const CHAT_URL = "wss://128.5.9.83:8181/Quejas/socket";
//const CHAT_URL = 'wss://dev.mineco.gob.gt:/Quejas/socket';

export interface Message {
  autor: string;
  msg: string;
  audiencia: string;
  conexion: string;
}

export interface messageDeUnion {
  autor: String;
  audiencia: String;
}

@Injectable()
export class ChatService {
  public messages: Subject<Message>;
  public messageDeUnion: Subject<messageDeUnion>;

  constructor(wsService: WebsocketService) {
    this.messages = <Subject<Message>>(
      wsService.connect(CHAT_URL).map((response: MessageEvent): Message => {
        let data = JSON.parse(response.data);
        return {
          autor: data.autor,
          msg: data.msg,
          audiencia: data.audiencia,
          conexion: data.conexion
        };
      })
    );
    this.messageDeUnion = <Subject<messageDeUnion>>(
      wsService.connect(CHAT_URL).map((response: MessageEvent): messageDeUnion => {
        let data = JSON.parse(response.data);
        return {
          autor: data.autor,
          audiencia: data.audiencia,
        };
      })
    );
  }
}
