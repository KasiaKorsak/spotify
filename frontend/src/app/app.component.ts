import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  pageTitle = 'Spotify frontend';

  get isLoggedIn(): boolean {
    // return this.authService.isLoggedIn;
    return false;
  }

  get userName(): string {
    // if (this.authService.currentUser) {
    //   return this.authService.currentUser.userName;
    // }
    return '';
  }

  // constructor(private authService: AuthService) { }

  logOut(): void {
    // this.authService.logout();
    console.log('Log out');
  }
}
