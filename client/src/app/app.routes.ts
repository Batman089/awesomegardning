import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlantTableComponent } from './plant-table/plant-table.component';
import { PlantDetailsComponent } from './plant-details/plant-details.component';

export const routes: Routes = [
  { path: '', component: PlantTableComponent },
  { path: 'plant-details/:id', component: PlantDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
