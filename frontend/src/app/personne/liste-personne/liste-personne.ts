import { Component } from '@angular/core';

@Component({
  selector: 'app-liste-personne',
  standalone: false,
  templateUrl: './liste-personne.html',
  styleUrl: './liste-personne.css'
})
export class ListePersonne {
  products!: Product[];

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.productService.getProductsMini().then((data) => {
      this.products = data;
    });
  }
}
