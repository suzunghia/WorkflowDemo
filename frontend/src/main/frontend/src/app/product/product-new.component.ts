import { Component } from '@angular/core';
import { Product } from './product';

@Component ({
   selector: 'form-new',
   templateUrl: './product-new.component.html'
})

export class ProductNewComponent {
   model = new Product(1,'ProductA');
}