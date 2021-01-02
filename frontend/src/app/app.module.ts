import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {WelcomeComponent} from './home/welcome.component';
import {PageNotFoundComponent} from './page-not-found.component';
import { TrackModule } from './tracks/track.module';

@NgModule({
   imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    TrackModule
  ],
  declarations: [
    AppComponent,
    WelcomeComponent,
    PageNotFoundComponent
  ],
 
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
