import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router } from "@angular/router";
import { BehaviorSubject } from "rxjs";
import { UsuarioService } from "./usuario.service";


@Injectable()
export class Authguard implements CanActivate {

    private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
    isLoggedIn$ = this._isLoggedIn$.asObservable();
    constructor(private router: Router, private usersService: UsuarioService){}


    canActivate(route: ActivatedRouteSnapshot){
        const currentUser  = this.usersService.authenticatedUser
        this._isLoggedIn$.next(!!currentUser.token)
        // if(currentUser){
        //     const roles = route;
        return true;
        // }

        // this.router.navigate(['/login']);
        // return false;
        
    }
}