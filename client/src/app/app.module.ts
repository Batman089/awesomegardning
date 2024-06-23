import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PlantDetailsComponent } from './plant-details/plant-details.component';
import {PlantTableComponent} from "./plant-table/plant-table.component";
import {MatTableModule} from "@angular/material/table";
import {RouterLink, RouterModule} from "@angular/router";
import {MatSort} from "@angular/material/sort";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    PlantDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppComponent,
    MatTableModule,
    RouterLink,
    RouterModule,
    MatSort,
    PlantTableComponent
  ],
  providers: [],
  bootstrap: [],
})
export class AppModule { }
