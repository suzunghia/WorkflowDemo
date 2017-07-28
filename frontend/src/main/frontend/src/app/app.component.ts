import { Component } from '@angular/core';
import { IProduct } from 'app/product/product';
import { ProductService } from 'app/product/product.service';
import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  // templateUrl: './app.component.html',
  template: '<p>Multiplier: {{2 | Multiplier: 10}}</p>' ,
  styleUrls: ['./app.component.css'],
  providers: [ProductService]
})
export class AppComponent {
  title: string = 'app works!';
  status: boolean = true;
  // appList: any[] = [ {
  //     "ID": "1",
  //     "url": 'assets/baymax.png'
  //  },

  //  {
  //     "ID": "2",
  //     "url": 'assets/zac.jpg'
  //  } ];

  iproducts: IProduct[];
   constructor(private _product: ProductService) {
   }
   
   ngOnInit() : void {
      this._product.getproducts()
      .subscribe(iproducts => this.iproducts = iproducts);
   }
}
