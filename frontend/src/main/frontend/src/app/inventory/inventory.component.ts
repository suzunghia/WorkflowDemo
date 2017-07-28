import { Component } from '@angular/core';
import { Router}  from '@angular/router';

@Component ({
   selector: 'navigator',
   templateUrl: './inventory.component.html'
})
export class AppInventory  {
    title: string = "Inventory";
    constructor(private _router: Router){} 

   onBack(): void { 
      this._router.navigate(['/Product']); 
   } 
}