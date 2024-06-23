import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlantService } from '../plant-service/plant.service';
import { Plant } from '../modules/Plant';

@Component({
  selector: 'app-plant-details',
  templateUrl: './plant-details.component.html',
  styleUrls: ['./plant-details.component.css']
})
export class PlantDetailsComponent implements OnInit {

  plant: Plant = {} as Plant;

  constructor(
    private route: ActivatedRoute,
    private plantService: PlantService
  ) {
    this.plant = {} as Plant;
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== null) {
      this.plantService.getPlant(id).subscribe(plant => {
          console.info('loading data for plant:', plant);
          this.plant = plant;
        },
        error => {
          console.error('Error: ', error);
        }
      );
    } else {
      // Handle the case where id is null
      console.error('Plant id is null');
    }
  }

}
