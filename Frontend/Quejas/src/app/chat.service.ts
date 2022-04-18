import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { WebsocketService } from './websocket.service';
 
const CHAT_URL = 'ws://localhost:8080/Quejas/socket';
 
export interface Message {
    autor: string,
    msg: string,
    audiencia: string
}

export interface Message1 {
    msg: String
}
 
@Injectable()
export class ChatService {
    public messages: Subject<Message>;
 
    constructor(wsService: WebsocketService) {
        this.messages = <Subject<Message>>wsService
            .connect(CHAT_URL)
            .map((response: MessageEvent): Message => {
                let data = JSON.parse(response.data);
                return {
                    autor: data.autor,
                    msg: data.msg,
                    audiencia: data.audiencia
                }
            });
    }
}