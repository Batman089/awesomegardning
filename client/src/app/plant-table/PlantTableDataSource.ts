import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {Plant} from "../modules/Plant";
import {BehaviorSubject, merge, Observable, of as observableOf} from "rxjs";
import {PlantService} from "../plant-service/plant.service";
import {map} from "rxjs/operators";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

export class PlantTableDataSource extends DataSource<Plant> {
  private plantsSubject = new BehaviorSubject<Plant[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  constructor(private plantService: PlantService, private paginator: MatPaginator, private sort: MatSort) {
    super();
    this.loadPlants();
  }

  loadPlants(): void {
    this.plantService.getPlants().subscribe(plants => {
      console.log(plants);
      this.plantsSubject.next(plants);
      this.paginator.length = plants.length;
    });
  }

  connect(): Observable<Plant[]> {
    const dataMutations = [
      observableOf(this.plantsSubject.getValue()),
      observableOf(this.paginator.page),
      observableOf(this.sort.sortChange)
    ];

    this.paginator.length = this.plantsSubject.getValue().length;
    return merge(...dataMutations).pipe(map(() => {
      const data = this.getPagedData(this.getSortedData([...this.plantsSubject.getValue()]));
      console.log("connect data:" + data);
      return data;
    }));
  }

  protected getPagedData(data: Plant[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  disconnect(): void {}

  private getSortedData(plants: Plant[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return plants;
    }

    return plants.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'id': return compare(a.plantId, b.plantId, isAsc);
        case 'name': return compare(a.plantName, b.plantName, isAsc);
        case 'description': return compare(a.plantDescription, b.plantDescription, isAsc);
        default: return 0;
      }
    });
  }

  public getPlantsLength(): number {
    return this.plantsSubject.getValue().length;
  }
}

function compare(a: string, b: string , isAsc: boolean) {
  const numA = Number(a);
  const numB = Number(b);

  if (!isNaN(numA) && !isNaN(numB)) {
    return (numA < numB ? -1 : 1) * (isAsc ? 1 : -1);
  } else {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}
