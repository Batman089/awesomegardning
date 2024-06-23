import {Component, OnDestroy, ViewChild} from '@angular/core';
import {PlantService} from '../plant-service/plant.service';
import {ApiRoutes} from "../core/ApiRoutes";
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {RouterLink, RouterModule} from "@angular/router";
import {MatSort, MatSortHeader} from "@angular/material/sort";
import {HttpClientModule} from "@angular/common/http";
import {MatPaginator} from "@angular/material/paginator";
import {Subscription} from "rxjs";
import {Plant} from "../modules/Plant";

@Component({
  selector: 'app-plant-table',
  templateUrl: './plant-table.component.html',
  styleUrls: ['./plant-table.component.css'],
  imports: [MatTableModule, RouterModule, RouterLink, MatSort, HttpClientModule, MatPaginator, MatSortHeader],
  standalone: true
})
export class PlantTableComponent implements OnDestroy {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  plants!: MatTableDataSource<Plant>;
  private subscription!: Subscription;
  isRefreshing: boolean = false;
  length: number = 50;

  constructor(private plantService: PlantService) { }

  displayedColumns: string[] = ['id', 'name', 'description'];

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  ngOnInit() {
    this.loadPlants();
  }

  loadPlants() {
    this.isRefreshing = true;
    this.subscription = this.plantService.getPlants().subscribe((plants) => {
      this.plants = new MatTableDataSource<Plant>(plants);
      this.plants.paginator = this.paginator;
      this.isRefreshing = false;
    });
  }

  protected readonly ApiRoutes = ApiRoutes;
}
