import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InventoryService } from '../services/inventory.service';
import { Item } from '../models/item';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit{
  private inventoryService: InventoryService = inject(InventoryService);
  private fb: FormBuilder = inject(FormBuilder);
  file!: File;
  jsonFileForm!: FormGroup;
  errrorMessage!: string;
  inventory!: Item[];

  ngOnInit(): void {
    this.findAll();
    this.initJsonFile();
  }
  initJsonFile(){
    this.jsonFileForm = this.fb.group({
      file: ['', [Validators.required]]
    });
  }
  chooseFile(event: any){
    this.file = event.target.files[0];
  }
  upload(){
    this.inventory = [];
    console.log('File to send: ', this.file);
    const data = new FormData();
    data.append('file', this.file);

    this.inventoryService.upload(data).subscribe({
      next: res => {
        this.inventory = res;
      },
      error: err => {
        this.errrorMessage = err.error.message;
        console.log('Error occured: ', err);
        
      }
    });
  }
  findAll(){
    this.inventoryService.findAll().subscribe({
      next: res => {
        this.inventory = res;
      },
      error: err => {
        this.errrorMessage = "Something went wrong while fetching inventory";
      }
    });
  }
}
