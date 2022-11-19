import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListComponent } from './home/list/list.component';
import { AddUpdateComponent } from './home/add-update/add-update.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ListOrderComponent } from './home/list-order/list-order.component';

@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    AddUpdateComponent,
    ListOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
