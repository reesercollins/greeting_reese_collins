import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { GreetingsComponent } from './greetings/greetings.component';
import { GreetingDetailComponent } from './greeting-detail/greeting-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    GreetingsComponent,
    GreetingDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
