import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AppProduct } from 'app/product/product.component'
import { ProductNewComponent } from 'app/product/product-new.component'
import { AppInventory } from 'app/inventory/inventory.component'
import { PageNotFoundComponent } from 'app/pagenotfound/pagenotfound.component'
import { MultiplierPipe } from 'app/pipe/multiplier.pipe'

const appRoutes: Routes = [
   { path: 'Product', component: AppProduct },
   { path: 'Inventory', component: AppInventory },
   { path: "ProductNew", component: ProductNewComponent},
   { path: '**', component: PageNotFoundComponent }   
];

@NgModule({
  declarations: [
    AppComponent, AppProduct, AppInventory, PageNotFoundComponent, ProductNewComponent, MultiplierPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
